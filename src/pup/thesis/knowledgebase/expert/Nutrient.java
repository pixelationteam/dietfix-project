package pup.thesis.knowledgebase.expert;

import pup.thesis.util.mysql.DBObject;

public class Nutrient implements DBObject {


	final Nutrients nutname;
	final double val;
	
	public Nutrient(Nutrients name,double value){
		this.nutname = name;
		val = value;
	}
	
	public Nutrients getName(){
		return nutname;
	}
	public double getValue(){
		return val;
	}
	
	@Override
	public String toString(){
		return String.format("Nutrient:[name:\"%s\",value:\"%s\"]",nutname,val);
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "NUTRITION";
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
}
