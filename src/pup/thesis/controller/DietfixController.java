package pup.thesis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.ResponseWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import pup.thesis.helper.GeneralHelper;
import pup.thesis.helper.JwnlHelper;
import pup.thesis.helper.MysqlHelper;
import pup.thesis.knowledgebase.AnswerData;
import pup.thesis.knowledgebase.expert.Experts;
import pup.thesis.logging.App;
import pup.thesis.logging.Modules;
import pup.thesis.nlu.CoreParser;
import pup.thesis.nlu.WordSynonym;
import pup.thesis.nlu.pos.TypedDep;
import pup.thesis.server.DietfixServer;
import pup.thesis.test.WordSynonymTest;
import pup.thesis.util.ClientData;
import edu.stanford.nlp.io.EncodingPrintWriter.out;
import edu.stanford.nlp.trees.Tree;

/**
 * Servlet implementation class DietfixController
 */
@WebServlet("/DietfixController")
public class DietfixController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//--Declared global variables
	private WordSynonym synonym;
	private MysqlHelper sql;
	//--
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DietfixController() {
        super();
        // TODO Auto-generated constructor stub
        
        
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String reqUri = GeneralHelper.getLastBitFromUrl(request.getRequestURI());
		ClientData cdata = (ClientData) request.getAttribute("clientdata");
		PrintWriter ops = response.getWriter(); 
		if(cdata==null){
			ops.print("Error: No data for client found");
			return;
		}
		if(reqUri.equalsIgnoreCase("api")){
			String input = request.getParameter("input");
			if(input==null||input.length()==0){
				ops.print("Error: Invalid input string");
				return;
			}
			if(!DietfixServer.isRunning()){
				DietfixServer.start();
			}
			//Tree inputpt = DietfixServer.getParser().getParseTree("What should I do to lose weight fast?");
			Tree inputpt = DietfixServer.getNLUStarter().startNLUModule(input);
			/*
			try {
				App.log("");
				App.log("Press any key to proceed..");
				System.in.read();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			
			List<TypedDep> xdeplist = DietfixServer.getFOPL().parseDeps(DietfixServer.getParser().getDependencies(inputpt));
			HashMap<Experts, List<AnswerData>> sh = null;
			try {
				sh = DietfixServer.getKBase().process(cdata, xdeplist);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String txt = DietfixServer.getTextGenerator().generateText(cdata, sh);
			App.log(Modules.SYSTEM,"");
			App.log(Modules.SYSTEM,"");
			App.log(Modules.SYSTEM,"");
			App.log(Modules.SYSTEM,"RESPONSE",txt);
			ops.print(txt);
			return;
		}
		
		/*
		CoreParser parser = new CoreParser();
		synonym = new WordSynonym();
		JwnlHelper help = new JwnlHelper();
		IndexWord word;
		WordSynonymTest test = new WordSynonymTest();
		
		try {
			word = help.getWord(POS.ADJECTIVE, "pretty");
			ArrayList<Synset> sets = help.getSynonyms(word);
			//ArrayList<String> synonyms = synonym.synset2String(sets);
			request.setAttribute("word", sets);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		String input = (String)request.getParameter("input");
		
		insertData();
		ArrayList<String> result = parser.getTokens(input);
		ArrayList<String> result2 = parser.getTags(input);
		ArrayList<String> result3 = parser.getLemma(input);
		ArrayList<String> result4 = getDataFromSql();
		Tree parseTree = parser.getParseTree(input);
		int depth = 0;
		depth = test.getDepth();
		
		request.setAttribute("tokens", result);
		request.setAttribute("tags", result2);
		request.setAttribute("lemma", result3);
		request.setAttribute("tree", parseTree);
		request.setAttribute("sqlResult", result4);
		request.setAttribute("path", getServletContext().getRealPath("/"));
		request.setAttribute("depth", depth);
		
		
		*/
		//ArrayList<ArrayList<String>> synonyms = new ArrayList<ArrayList<String>>();
		//synonyms = getSynonyms(result3, result2);
		//request.setAttribute("synonyms", synonyms);
		//RequestDispatcher view = request.getRequestDispatcher("result.jsp");
		
		//view.forward(request, response);
	}
	
	private ArrayList<ArrayList<String>> getSynonyms(ArrayList<String> lemma, ArrayList<String> posTags) {
		ArrayList<ArrayList<String>> synonyms = new ArrayList<ArrayList<String>>();
		ArrayList<Synset> synsets = new ArrayList<Synset>();
		
		for(int i = 0; i < lemma.size(); i++) {
			
			try {
				//synsets = synonym.getRelatedWords(lemma.get(i), posTags.get(i));
				if(!synsets.isEmpty()) {
					synonyms.add(synonym.synset2String(synsets));
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return synonyms;
	}
	
	private ArrayList<String> getDataFromSql() {
		ArrayList<String> table = new ArrayList<String>();
		ArrayList<String> tableName = new ArrayList<String>();
		
		sql = new MysqlHelper();
	
		try {
			ResultSet set = sql.executeQuery("select * from user");
			ResultSetMetaData metadata = set.getMetaData();
			
			for(int i = 1; i <= metadata.getColumnCount(); i++) {
				tableName.add(metadata.getColumnLabel(i));
			}
			
			while(set.next()) {
				Iterator<String> i = tableName.iterator();
				
				while(i.hasNext()) {
					table.add(set.getString(i.next()));
				}
				
			}
		} catch(Exception e) {
			
		}
			
		return table;
	}
	
	private void insertData() {
		sql = new MysqlHelper();
		String insertStatement = "INSERT INTO user VALUES(" +
				"null, 'paulzed', 'kakashi17', '12345678', " +
				"'paul', 'v', 'artigo', NOW(), NOW())";
		
		String deleteStatement = "DELETE FROM user WHERE userID = 2";
		
		sql.updateDb(insertStatement);
		sql.updateDb(insertStatement);
		
		//sql.updateDb(deleteStatement);
	}
	
	protected String processToNLU(String input) {
		String query = "";
		
		return query;
	}
}
