package pup.thesis.learning.reinforcement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.relationship.Relationship;
import pup.thesis.helper.JwnlHelper;
import pup.thesis.helper.MysqlHelper;
import pup.thesis.nlu.RelatedWord;
import edu.cmu.lti.lexical_db.ILexicalDatabase;
import edu.cmu.lti.lexical_db.NictWordNet;
import edu.cmu.lti.lexical_db.data.Concept;
import edu.cmu.lti.ws4j.RelatednessCalculator;
import edu.cmu.lti.ws4j.impl.HirstStOnge;
import edu.cmu.lti.ws4j.impl.JiangConrath;
import edu.cmu.lti.ws4j.impl.LeacockChodorow;
import edu.cmu.lti.ws4j.impl.Lesk;
import edu.cmu.lti.ws4j.impl.Lin;
import edu.cmu.lti.ws4j.impl.Path;
import edu.cmu.lti.ws4j.impl.Resnik;
import edu.cmu.lti.ws4j.impl.WuPalmer;

public abstract class ReinforcementLearning {
	
	private JwnlHelper helper;
	private MysqlHelper sqlHelper;
	private Reward reward;
	
	private ILexicalDatabase db = new NictWordNet();
	
	private RelatednessCalculator[] _rc = {
			 new HirstStOnge(db), new LeacockChodorow(db), new Lesk(db),  new WuPalmer(db), 
             new Resnik(db), new JiangConrath(db), new Lin(db), new Path(db)
	};
	
	/**
	 * 
	 * Learn the unknown word
	 * 
	 * @param words
	 * @return
	 */
	public boolean learn(ArrayList<Policy> words) {
		
		helper = new JwnlHelper();
		sqlHelper = new MysqlHelper();
		
		//gets the optimized policy based on lowest depth
		Policy opti = evaluatePolicy(words);
		//App.log(opti.getInitState() == null);
		//System.exit(0);
		if(opti.getInitState()==null){
			return false;
		}
		String sTemp = opti.getInitState().getLabel();
		POS pTemp = opti.getInitState().getTag();
		String aTemp = opti.getInitState().getId();
		
		//learn the freaking word
		//still no idea about the action thingy here...
		String query = "INSERT INTO known_words VALUES(" +
				"null, '" + sTemp + "', '" + helper.getPOS(pTemp) + "', " + aTemp + ")";
		
		boolean flag = sqlHelper.updateDb(query);
		
		if(!flag) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * returns the policy with the lowest value of reward
	 * 
	 * @param policies
	 * @return
	 */
	public Policy evaluatePolicy(ArrayList<Policy> policies) {
		Policy optimizedPolicy = new Policy();
		int ctr = 0;
		double temp = 0;
		double max = 0;
		Iterator<Policy> i = policies.iterator();
		
		while(i.hasNext()) {
			Policy p = i.next();
			
			if(ctr == 0) {
				max = p.getReward();
				ctr++;
			}
			else {
				if(max < temp) {
					max = temp;
					optimizedPolicy = p;
				}
			}
			
			
		}
		
		return optimizedPolicy;
	}
	
	
	
	/**
	 * 
	 * gets all the known_words in the database
	 * and return it as a Arraylist of RelatedWord
	 * 
	 * @param set
	 * @throws SQLException
	 * @return
	 */
	public ArrayList<RelatedWord> iterateInDb(String type) throws SQLException {
		
		ArrayList<RelatedWord> listRelated = new ArrayList<RelatedWord>();
		ResultSet set = null;
		helper = new JwnlHelper();
		sqlHelper = new MysqlHelper();
		if(type.isEmpty()) 
			set = sqlHelper.executeQuery("SELECT * FROM known_words");
		else
			set = sqlHelper.executeQuery("SELECT * FROM known_words WHERE pos_tag like '" + type + "'");
		
		
		while(set.next()) {
			RelatedWord rlWord = new RelatedWord();
			rlWord.setId(set.getString(1));
			rlWord.setLabel(set.getString(2));
			rlWord.setTag(helper.getPOS(set.getString(3)));
			rlWord.setAction(set.getString(4));
			listRelated.add(rlWord);
		}
		
		sqlHelper.closeConnections();
		return listRelated;
	}
	
	
	
	/**
	 * 
	 * Gets the depth of the relationship between
	 * the IndexWord to the words in the list
	 * of known_words
	 * 
	 * @param word
	 * @return
	 */
	public Policy iterateDbToGetDepth(IndexWord word) {
		
		reward = new Reward();
		Policy p = new Policy();

		PointerType type = null;
		Relationship rel = null;
		
		int depth = 0;
		int r = 0;
		
		try {
			ArrayList<RelatedWord> words = iterateInDb("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
		
	}
	
	/**
	 * 
	 * Returns the Arraylist of unknown words
	 * out of the ArrayList<RelatedWord> sentence
	 * 
	 * @param sentence
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<RelatedWord> getUnknownWords(ArrayList<RelatedWord> sentence) throws SQLException {
		
		ArrayList<RelatedWord> unknown = new ArrayList<RelatedWord>();
		
		ArrayList<RelatedWord> kWords = iterateInDb("");
		
		boolean flag = false;
		
		Iterator<RelatedWord> i2 = sentence.iterator();
		
		while(i2.hasNext()) {
			RelatedWord word = i2.next();
			Iterator<RelatedWord> i = kWords.iterator();
			while(i.hasNext()) {
				RelatedWord k = i.next();
				
				if(word.equals(k)) {
					flag = false;
					break;
				}
				else {
					flag = true;
				}
			}
			
			if(flag) unknown.add(word);
			
			//reset the flag
			flag = false;
		}
		
		return unknown;
	}
	
	/**
	 * 
	 * 
	 * @param a
	 * @return
	 */
	public double getAverage(double[] a) {
		double percent = 0;
		for(int i = 0; i < a.length; i++) {
			percent += a[i];
		}
		return (percent /= a.length);
	}
	
	/**
	 * 
	 * 
	 * @param word1
	 * @param word2
	 * @return
	 */
	public double getPercentageRelativity(String word1, String word2) {

		double percent = 0;
		double score[] = new double[8];
		int ctr = 0;
		
		for(RelatednessCalculator rc : _rc) {
			double s = rc.calcRelatednessOfWords(word1, word2);
			score[ctr] = s;
			ctr++;
		}
		
		percent = getAverage(score);
		return percent;
	}
	
	public void printPercentRelatedNess(String word1, String id, String word2, double percent) {		
		System.out.println(word1 + " - " + id + " " + word2 + " = " + percent);
	}
	
	/**
	 * 
	 * gets the depth of relationship between the ArrayList<RelatedWord> word
	 * to the list of known_words in the database
	 * 
	 * @param word
	 * @return
	 * @throws JWNLException
	 */
	public ArrayList<ArrayList<Policy>> getDepthOfWord(ArrayList<RelatedWord> word) throws JWNLException {
		
		
		helper = new JwnlHelper();
		
		Reward reward = new Reward();
		
		ArrayList<ArrayList<Policy>> listPolicy = new ArrayList<ArrayList<Policy>>();
		
		PointerType type;
		
		try {
			Iterator<RelatedWord> i = word.iterator();
			
			while(i.hasNext()) {
				ArrayList<Policy> p = new ArrayList<Policy>();
				RelatedWord w = i.next();
				type = helper.identifyPointerType(w.getTag());
				ArrayList<RelatedWord> kWords = iterateInDb(helper.getPOS(w.getTag()));
				Iterator<RelatedWord> i2 = kWords.iterator();
				
				System.out.println("================Relatedness====================");
				
				while(i2.hasNext()) {
					RelatedWord w2 = i2.next();
					//IndexWord _w = helper.convertToIndexWord(w);
					//IndexWord _w2 = helper.convertToIndexWord(w2);
					Policy _p = new Policy();
					
					try {
						//Relationship r = helper.getDepthOfRelationship(_w.getSenses(), _w2.getSenses(), type);
						
						double r = getPercentageRelativity(w.getLabel(),w2.getLabel());
						printPercentRelatedNess(w.getLabel(), w2.getId(), w2.getLabel(), r);
						_p.setInitState(w);
						_p.setEndState(w2);
					
						_p.setReward(r);
					} catch(Exception e) {
						RelatedWord word1 = new RelatedWord();
						RelatedWord word2 = new RelatedWord();
						word1.setId("");
						word1.setLabel("");
						word1.setTag(POS.VERB);
						word2.setId("");
						word2.setLabel("");
						word2.setTag(POS.VERB);
						_p.setReward(-1);
						_p.setInitState(word1);
						_p.setEndState(word2);
					}
					
					p.add(_p);
				}
				
				System.out.println("=================End=====================");
				listPolicy.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return listPolicy;
	}
	
	/**
	 * 
	 * Self-explanatory
	 * 
	 * @param word
	 * @param synonym
	 * @return
	 */
	public boolean isSame(String word, POS wordTag, String synonym, POS synonymTag) {
		return (word.equalsIgnoreCase(synonym) && wordTag.equals(synonymTag)) ? true : false;
	}
	
}