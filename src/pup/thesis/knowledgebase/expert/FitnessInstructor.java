package pup.thesis.knowledgebase.expert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.Record3;
import org.jooq.Result;
import org.jooq.SelectConditionStep;
import org.jooq.impl.DSL;

import assets.dietfix.enums.DietfixAnsAnsexpert;

import pup.thesis.knowledgebase.AnswerData;
import pup.thesis.logging.App;
import pup.thesis.nlu.pos.TypedDep;
import pup.thesis.util.ClientData;

public class FitnessInstructor extends Expert {

	public FitnessInstructor(ClientData cdata) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		super(Experts.FITNESS_INSTRUCTOR,cdata);
	}




	@Override
	void initialize() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, SQLException {
		// TODO Auto-generated method stub
		
	}
}
