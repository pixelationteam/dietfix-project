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

import org.eclipse.jdt.internal.compiler.apt.model.Factory;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record3;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.SelectConditionStep;
import org.jooq.impl.DSL;

import assets.dietfix.Dietfix;
import assets.dietfix.enums.DietfixAnsAnsexpert;
import assets.sr25food.Sr25food;
import assets.sr25food.Tables;
import assets.sr25food.tables.FdGroup;
import assets.sr25food.tables.records.FdGroupRecord;

import pup.thesis.helper.MysqlHelper;
import pup.thesis.knowledgebase.AnswerData;
import pup.thesis.logging.App;
import pup.thesis.nlu.pos.TypedDep;
import pup.thesis.nlu.pos.PhraseProcessor;
import pup.thesis.util.ClientData;
import pup.thesis.util.mysql.DBObject;

public class Dietitian extends Expert{

	DSLContext foodquerier,dietfixquerier;
	Connection dietfixcon;
	public Dietitian( ClientData cdata) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		super(Experts.DIETITIAN, cdata);
		// TODO Auto-generated constructor stub
	}

	@Override
	void initialize() throws ClassNotFoundException,
		InstantiationException, IllegalAccessException, SQLException {
		// TODO Auto-generated method stub
		Connection foodcon = MysqlHelper.createFoodDBConnection(this.getClientData());
		dietfixcon = MysqlHelper.createDietfixConnection(this.getClientData());
		foodquerier = DSL.using(foodcon, SQLDialect.MYSQL);
		dietfixquerier = DSL.using(dietfixcon, SQLDialect.MYSQL);
	}
	
	public List<FoodCategory> getFoodCategories() throws SQLException{
		this.log("Fetching categories");
		ArrayList<FoodCategory> ret = new ArrayList<FoodCategory>();
		Result<Record> result = foodquerier.select().from(Tables.FD_GROUP).fetch();
		for(Record rec: result){
				
				ret.add(new FoodCategory(rec.getValue(Tables.FD_GROUP.FDGRP_CD),rec.getValue(Tables.FD_GROUP.FDGRP_DESC)));
			}
		
		return ret;	
		
	}
	public List<Factor> getFoodFactors() throws SQLException{
		this.log("Fetching factors");
		ArrayList<Factor> ret = new ArrayList<Factor>();
		//App.log(foodquerier.select().from(Tables.LANGDESC).getSQL());
		
		Result<Record> result = foodquerier.select().from(Tables.LANGDESC).fetch();
		
		for(Record rec: result){
				
				ret.add(new Factor(rec.getValue(Tables.LANGDESC.FACTOR),rec.getValue(Tables.LANGDESC.DESCRIPTION)));
			}
		return ret;
	}
	
	public Food getFood(String id){
		this.log("Fetching food:"+id);
		Record result = foodquerier.select().from(Tables.ABBREV).where(Tables.ABBREV.NDB_NO.equal(id)).fetchOne();
		if(result!=null){
			return new Food(result);
		}
		return null;
	}
	
	public List<Food> queryFood(Result<Record> result) throws SQLException{
		this.log("Fetching factors");
		ArrayList<Food> ret = new ArrayList<Food>();
		for(Record rec: result){
			ret.add(new Food(rec));
		}
		return ret;		
	}
	
	@Override
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
	
	@Override
	public List<ExpertAnswer> getAnswers(List<TypedDep> tds){
		ArrayList<ExpertAnswer> tdset = new ArrayList<ExpertAnswer>();
		assets.dietfix.Tables DIETFIX = new assets.dietfix.Tables(); 
		//Result<Record> rec =  dietfixquerier.fetch("select AnsID,AnsExpert,AnsDesc from dietfix_ans");
		StringBuilder sb = new StringBuilder("");
		for(TypedDep ea : tds){
			
			if(ea.getActions()!=null)
			for(String str : ea.getActions()){
				sb.append("\\\\[").append(str).append("\\\\]").append("|");
			}
		}
		if(sb.length()>0){
			sb.deleteCharAt(sb.length()-1);
		}
		Condition regex = DSL.condition("{0} REGEXP {1}", 
                DIETFIX.DIETFIX_ANS.ANSDESC,
                DSL.val(sb.toString()));
		SelectConditionStep<Record3<Integer, DietfixAnsAnsexpert, String>> rq = dietfixquerier.select(DIETFIX.DIETFIX_ANS.ANSID,DIETFIX.DIETFIX_ANS.ANSEXPERT,DIETFIX.DIETFIX_ANS.ANSDESC)
				.from(DIETFIX.DIETFIX_ANS)
				.where(regex);
	
		Result<Record> rec1 =  dietfixquerier.fetch(rq.getSQL(true));
		for(Record resrec : rec1){
			String expertstr = (String) resrec.getValue(DIETFIX.DIETFIX_ANS.ANSEXPERT.getName());
			tdset.add(new ExpertAnswer(resrec.getValue(DIETFIX.DIETFIX_ANS.ANSID),resrec.getValue(DIETFIX.DIETFIX_ANS.ANSDESC),Experts.valueOf(expertstr),this));
		}
		App.log("Dietitian[Query]:",rq.getSQL(true));
		return tdset;
	}

	
	
	
	
	
	
	
	

}
