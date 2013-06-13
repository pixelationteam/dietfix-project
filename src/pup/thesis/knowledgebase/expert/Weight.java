package pup.thesis.knowledgebase.expert;

public class Weight {

	String unit;
	double val;
	
	Weight(){
		
	}
	
	Weight(String text) throws Exception{
		
	}
	
	public String getUnit(){
		return unit;
	}
	public double getValue(){
		return val;
	}
	
	void setUnit(String text) throws Exception{
		text = text.trim();
		int ind = text.indexOf(' ');
		if(ind>0){
			
			val = Double.parseDouble(text.substring(0,ind)); // "72"
			
		unit = text.substring(ind+1);
		}
		else{
			throw new Exception();
		}
	}
	void setVal(double val){
		this.val = val;
	}
}
