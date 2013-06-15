package pup.thesis.knowledgebase.expert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pup.thesis.helper.MysqlHelper;
import pup.thesis.knowledgebase.AnswerData;
import pup.thesis.nlu.pos.PhraseProcessor;
import pup.thesis.nlu.pos.TypedDep;
import pup.thesis.util.ClientData;

public class Nutritionist extends Expert{

	Connection foodcon;
	
	public Nutritionist( ClientData cdata) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		super(Experts.NUTRITIONIST, cdata);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	void initialize() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, SQLException {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	

	
}
