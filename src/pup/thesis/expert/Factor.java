package pup.thesis.expert;

import pup.thesis.util.mysql.DBObject;

public class Factor implements DBObject{
	public final String factorId;
	public String description;
	Factor(String factorID,String description){
		this.factorId = factorID;
		this.description = description;
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toJSON() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString(){
		return String.format("[FoodFactor:id=\"%s\",value=\"%s\"]", factorId,description);
	}

	
}
