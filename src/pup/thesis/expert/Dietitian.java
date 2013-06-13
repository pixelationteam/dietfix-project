package pup.thesis.expert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import assets.sr25food.Sr25food;
import assets.sr25food.Tables;
import assets.sr25food.tables.FdGroup;
import assets.sr25food.tables.records.FdGroupRecord;

import pup.thesis.helper.MysqlHelper;
import pup.thesis.logging.App;
import pup.thesis.util.ClientData;
import pup.thesis.util.mysql.DBObject;

public class Dietitian extends Expert{

	DSLContext foodquerier;
	public Dietitian( ClientData cdata) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		super(Experts.DIETITIAN, cdata);
		// TODO Auto-generated constructor stub
		initialize();
	}

	void initialize() throws ClassNotFoundException,
		InstantiationException, IllegalAccessException, SQLException {
		// TODO Auto-generated method stub
		Connection foodcon = MysqlHelper.createFoodDBConnection(this.getClientData());
		foodquerier = DSL.using(foodcon, SQLDialect.MYSQL);
	}
	
	public List<Category> getFoodCategories() throws SQLException{
		this.log("Fetching categories");
		ArrayList<Category> ret = new ArrayList<Category>();
		Result<Record> result = foodquerier.select().from(Tables.FD_GROUP).fetch();
		for(Record rec: result){
				
				ret.add(new Category(rec.getValue(Tables.FD_GROUP.FDGRP_CD),rec.getValue(Tables.FD_GROUP.FDGRP_DESC)));
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
	
	public List<Food> queryFood(Result<Record> result) throws SQLException{
		this.log("Fetching factors");
		ArrayList<Food> ret = new ArrayList<Food>();
		for(Record rec: result){
			ret.add(new Food(rec));
		}
		return ret;
		
	}
	
	
	
	
	

}
