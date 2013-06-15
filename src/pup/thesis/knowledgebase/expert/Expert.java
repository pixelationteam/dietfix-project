package pup.thesis.knowledgebase.expert;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record3;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.SelectConditionStep;
import org.jooq.impl.DSL;

import assets.dietfix.enums.DietfixAnsAnsexpert;

import pup.thesis.helper.MysqlHelper;
import pup.thesis.knowledgebase.AnswerData;
import pup.thesis.logging.App;
import pup.thesis.nlu.pos.PhraseProcessor;
import pup.thesis.nlu.pos.TypedDep;
import pup.thesis.util.ClientData;



public abstract class Expert {

	private final Experts expert;
	private final ClientData clientdata;
	protected 	Connection dietfixcon;
	protected DSLContext dietfixquerier;
	public Expert(Experts name,ClientData cdata) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		expert = name;
		this.clientdata = cdata;
		dietfixcon = MysqlHelper.createDietfixConnection(clientdata);
		dietfixquerier = DSL.using(dietfixcon, SQLDialect.MYSQL);
		initialize();
	}
	
	
	public Experts getType(){
		return expert;
	}
	
	public ClientData getClientData(){
		return clientdata;
	}
	
	protected void log(String msg){
		App.log(String.format("Expert.%s", expert),msg);
	}
	
	
	AnswerData getAnswerData(int id) throws SQLException, ClassNotFoundException{
		String query1 = "select AnsObj from dietfix_ans where AnsID = "+id;
		AnswerData ret = null;
		PreparedStatement statement1 = dietfixcon.prepareStatement(query1);
		ResultSet resultSet = statement1.executeQuery();
		if (resultSet.next()) {
			// Object x=resultSet.getObject("AnsDesc");
			try {
				InputStream is = resultSet.getBlob("AnsObj")
						.getBinaryStream();
				ObjectInputStream ois;

				ois = new ObjectInputStream(is);

				Object x = ois.readObject();
				ret = (AnswerData) x;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			throw new IllegalArgumentException();
		}
		return ret;
	}

	public List<ExpertAnswer> getAnswers(List<String> tds) {
		// TODO Auto-generated method stub
		ArrayList<ExpertAnswer> tdset = new ArrayList<ExpertAnswer>();
		assets.dietfix.Tables DIETFIX = new assets.dietfix.Tables(); 
		//Result<Record> rec =  dietfixquerier.fetch("select AnsID,AnsExpert,AnsDesc from dietfix_ans");
		StringBuilder sb = new StringBuilder("");
		for(String ea : tds){
			
				sb.append("\\\\[").append(ea).append("\\\\]").append("|");
			
		}
		if(sb.length()>0){
			sb.deleteCharAt(sb.length()-1);
		}
		Condition regex = DSL.condition("{0} REGEXP {1}", 
                DIETFIX.DIETFIX_ANS.ANSDESC,
                DSL.val(sb.toString()));
		SelectConditionStep<Record3<Integer, DietfixAnsAnsexpert, String>> rq = dietfixquerier.select(DIETFIX.DIETFIX_ANS.ANSID,DIETFIX.DIETFIX_ANS.ANSEXPERT,DIETFIX.DIETFIX_ANS.ANSDESC)
				.from(DIETFIX.DIETFIX_ANS)
				.where(regex.and(DIETFIX.DIETFIX_ANS.ANSEXPERT.eq(DietfixAnsAnsexpert.valueOf(expert.name()))));
	
		Result<Record> rec1 =  dietfixquerier.fetch(rq.getSQL(true));
		for(Record resrec : rec1){
			String expertstr = (String) resrec.getValue(DIETFIX.DIETFIX_ANS.ANSEXPERT.getName());
			tdset.add(new ExpertAnswer(resrec.getValue(DIETFIX.DIETFIX_ANS.ANSID),resrec.getValue(DIETFIX.DIETFIX_ANS.ANSDESC),Experts.valueOf(expertstr),this));
		}
		App.log(expert.name()+"[Query]:",rq.getSQL(true));
		return tdset;
	}
	
	abstract void initialize() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, SQLException ;
	
}
