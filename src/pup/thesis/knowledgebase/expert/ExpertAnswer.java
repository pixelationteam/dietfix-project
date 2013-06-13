package pup.thesis.knowledgebase.expert;

import java.sql.SQLException;

import pup.thesis.knowledgebase.AnswerData;
import pup.thesis.nlu.pos.PhraseProcessor;
import pup.thesis.nlu.pos.TypedDep;

public class ExpertAnswer {

	private final int iD;
	private final String desc;
	private final Experts type;
	private final Expert exp;
	ExpertAnswer(int id,String desc,Experts type,Expert exp){
		this.iD = id;
		this.desc = desc;
		this.type = type;
		this.exp = exp;
	}
	
	public int getId(){
		return iD;
	}
	public String getDesc(){
		return desc;
	}
	public AnswerData getAnswerData() throws  SQLException, ClassNotFoundException{
		return exp.getAnswerData(iD);
	}
	public Experts getType(){
		return type;
	}
	
}
