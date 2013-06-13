package pup.thesis.querygeneration;

import java.sql.SQLException;
import java.util.List;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.ResultQuery;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import assets.sr25food.Tables;

import pup.thesis.helper.MysqlHelper;
import pup.thesis.nlu.pos.TypedDep;
import pup.thesis.util.ClientData;

public class FoodQG implements QueryGenerator {

	DSLContext dc;
	public FoodQG(ClientData cdata) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{

			dc = DSL.using(MysqlHelper.createFoodDBConnection(cdata),SQLDialect.MYSQL);
		
	}
	
	@Override
	public void addKeyword(Condition cond) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Condition> getConditions() {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultQuery<Record> generateQuery() {
		// TODO Auto-generated method stub
		return dc.select().from(Tables.LANGDESC.leftOuterJoin(Tables.LANGUAL).using(Tables.LANGUAL.FACTOR).leftOuterJoin(Tables.ABBREV).using(Tables.LANGUAL.NDB_NO)).limit(100,50);
	}

	@Override
	public ResultQuery<Record> generateQuery(List<TypedDep> tp) {
		// TODO Auto-generated method stub
		return null;
	}

}
