package pup.thesis.knowledgebase.expert;

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
import pup.thesis.logging.Modules;
import pup.thesis.util.mysql.DBObject;

public class Food implements DBObject{
			
	final String id;
	final Record record;
	final String shortDescription;
	private HashMap<Nutrients,Double> nutrients = new HashMap<Nutrients, Double>();
	private Weight weight1 = new Weight(),weight2 = new Weight(); 
	private final FoodCategory foodcat;
	Food(Record resultrec){
		record = resultrec;
		String idd = "invalid",desc = "None";
		String catid = "invalid",catdesc="invalid";
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
			else if(field.equals(Tables.ABBREV.GMWT_1)){
				try{
				double wv1 = record.getValue(Tables.ABBREV.GMWT_1);
				weight1.setVal(wv1);
				}
				catch(NullPointerException ex){
					weight1 = null;
				}
			}
			else if(field.equals(Tables.ABBREV.GMWT_2)){
				try{
				double wv2 = record.getValue(Tables.ABBREV.GMWT_2);
				weight2.setVal(wv2);
				}
				catch(NullPointerException ex){
					weight2 = null;
				}
			}
			else if(field.equals(Tables.ABBREV.GMWT_DESC1)){
				try {
					String wd1 = record.getValue(Tables.ABBREV.GMWT_DESC1);
					weight1.setUnit(wd1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					App.log(Modules.INFORMATIONI_RETRIEVAL,"Food:",e.getMessage());
					weight1 = null;
				}
			}
			else if(field.equals(Tables.ABBREV.GMWT_DESC2)){
				try {
					String wd2 = record.getValue(Tables.ABBREV.GMWT_DESC2);
					weight2.setUnit(wd2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					App.log(Modules.INFORMATIONI_RETRIEVAL,"Food:",e.getMessage());
					weight2 = null;
				}
			}
			else if(field.equals(Tables.LANGUAL.FACTOR)){
				
				catid = record.getValue(Tables.LANGUAL.FACTOR);
				
			}
			else if(field.equals(Tables.LANGDESC.DESCRIPTION)){
				catdesc = record.getValue(Tables.LANGDESC.DESCRIPTION);
			}
			
			
		}
		
		id = idd;
		shortDescription = desc;
		foodcat = new FoodCategory(catid, catdesc);
	}

	public FoodCategory getFoodCategory(){
		return foodcat;
	}
	public Weight getWeight1(){
		return weight1;
	}
	public Weight getWeight2(){
		return weight2;
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
