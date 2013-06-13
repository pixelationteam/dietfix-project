package pup.thesis.util;

import pup.thesis.util.mysql.DBQuerier;

public class ClientData {

	final DBQuerier querier;
	
	public ClientData(){
		querier = new DBQuerier();
	}
	
	public DBQuerier getQuerier(){
		return querier;
	}
}
