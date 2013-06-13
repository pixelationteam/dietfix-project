package pup.thesis.knowledgebase.expert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pup.thesis.logging.App;
import pup.thesis.util.mysql.DBObject;

public class FoodCategory implements DBObject {

	public final String id, description;


	FoodCategory(String id,String desc){
		this.id = id;
		this.description = desc;
	}
	
	
	

	@Override
	public String toString(){
		return String.format("[FoodCategory:id=\"%s\",value=\"%s\"]", id,description);
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

}
