package pup.thesis.knowledgebase.expert;

import java.sql.SQLException;
import java.util.List;

import pup.thesis.knowledgebase.AnswerData;
import pup.thesis.logging.App;
import pup.thesis.nlu.pos.PhraseProcessor;
import pup.thesis.nlu.pos.TypedDep;
import pup.thesis.util.ClientData;



public abstract class Expert {

	private final Experts expert;
	private final ClientData clientdata;
	
	public Expert(Experts name,ClientData cdata) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		expert = name;
		this.clientdata = cdata;
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
	
	public abstract List<ExpertAnswer> getAnswers(List<TypedDep> tds);
	
	abstract AnswerData getAnswerData(int id) throws SQLException, ClassNotFoundException;


	abstract void initialize() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, SQLException ;
	
}
