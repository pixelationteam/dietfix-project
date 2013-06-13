package pup.thesis.knowledgebase.expert;

import java.sql.SQLException;
import java.util.List;

import pup.thesis.knowledgebase.AnswerData;
import pup.thesis.nlu.pos.TypedDep;
import pup.thesis.util.ClientData;

public class FitnessInstructor extends Expert {

	public FitnessInstructor(ClientData cdata) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		super(Experts.FITNESS_INSTRUCTOR,cdata);
	}

	@Override
	public List<ExpertAnswer> getAnswers(List<TypedDep> tds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	AnswerData getAnswerData(int id) throws SQLException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void initialize() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, SQLException {
		// TODO Auto-generated method stub
		
	}
}
