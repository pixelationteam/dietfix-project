package pup.thesis.querygeneration;


import java.util.List;

import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.ResultQuery;

import pup.thesis.nlu.pos.TypedDep;

public interface QueryGenerator {

	public void addKeyword(Condition cond);
	public List<Condition> getConditions();
	public ResultQuery<Record> generateQuery(List<TypedDep> tp);
	
	
}
