package pup.thesis.expert;

import pup.thesis.util.mysql.DBObject;

public class NutriValue implements DBObject{

	public static final String NDB_No = "NDB_No",
			Nutr_No = "Nutr_No",
			Nutr_Val = "Nutr_Val",
			Num_Data_Pts = "Num_Data_Pts",
			Std_Error = "Std_Error",
			Src_Cd = "Src_Cd",
			Deriv_Cd = "Deriv_Cd",
			Ref_NDB_No = "Ref_NDB_No",
			Add_Nutr_Mark = "Add_Nutr_Mark",
			Num_Studies = "Num_Studies",
			Min = "Min",
			Max = "Max",
			DF = "DF",
			Low_EB = "Low_EB",
			Up_EB = "Up_EB",
			Stat_Cmt = "Stat_Cmt",
			AddMod_Date = "AddMod_Date";
	
	public final String ndbNo,nutrNo;
	public double nutrVal,stdError,min,max,dF,lowEb,upEb;
	public String addModDate,statCmt,addNutrMark,refNdbNo,derivCd,srcCd;
	public int numStudies,refDataPts;
			
	NutriValue(String ndbNo,String nutrNo){
		this.ndbNo = ndbNo;
		this.nutrNo = nutrNo;
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
