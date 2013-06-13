package pup.thesis.expert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jooq.Field;
import org.jooq.Record;
import org.jooq.TableField;

import assets.sr25food.Tables;
import assets.sr25food.tables.records.AbbrevRecord;

import pup.thesis.logging.App;
import pup.thesis.util.mysql.DBObject;

public class Food implements DBObject{
			
	final String id;
	final Record record;
	final String shortDescription;
	public HashMap<Nutrients,Double> nutrients = new HashMap<Nutrients, Double>();
	
	Food(Record resultrec){
		record = resultrec;
		String idd = "invalid",desc = "None";
		for(Field<?> field: record.fields()){
			Nutrients nut = Nutrients.valueOf(field);
			if(nut!=null){
				nutrients.put(nut, (Double) record.getValue(field));
			}
			else if(field.equals(Tables.ABBREV.NDB_NO)){
				idd = record.getValue(Tables.ABBREV.NDB_NO);
			}
			else if(field.equals(Tables.ABBREV.SHRT_DESC)){
				desc = record.getValue(Tables.ABBREV.SHRT_DESC);
			}
		}
		
		id = idd;
		shortDescription = desc;
		
		
	}

	public String getID(){
		return id;
	}
	public String getDescription(){
		return shortDescription;
	}
	public Nutrient getNutrient(Nutrients nut){
		return new Nutrient(nut,nutrients.get(nut));
	}
	public List<Nutrient> getNutrients(){
		ArrayList<Nutrient> n = new ArrayList<Nutrient>();
		
		for(Nutrients nutkey : nutrients.keySet()){
			n.add(new Nutrient(nutkey,nutrients.get(nutkey)));
		}
		
		return n;
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "FOOD";
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
		return "";
	}


	@Override
	public void insert() {
		// TODO Auto-generated method stub
		
	}
}
