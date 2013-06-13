package pup.thesis.expert;

import java.sql.SQLException;

import pup.thesis.logging.App;
import pup.thesis.util.ClientData;



public abstract class Expert {

	private final Experts expert;
	private final ClientData clientdata;
	
	public Expert(Experts name,ClientData cdata){
		expert = name;
		this.clientdata = cdata;
		
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
	
	
}
