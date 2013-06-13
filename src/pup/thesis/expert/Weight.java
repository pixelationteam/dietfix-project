package pup.thesis.expert;

public class Weight {

	final String unit;
	final double val;
	
	Weight(String text) throws Exception{
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
}
