package pup.thesis.console;

public class RoolVO {

	public String stringValue;
	public Boolean booleanValue;
	
	public RoolVO(String stringValue){
		this.stringValue = stringValue;
	}
	public RoolVO(String stringValue,Boolean booleanValue){
		this.stringValue = stringValue;
		this.booleanValue = booleanValue;
	}
	public void setStringValue(String stringValue) {
		// TODO Auto-generated method stub
		this.stringValue = stringValue;
	}

	public void setBooleanValue(Boolean b) {
		// TODO Auto-generated method stub
		booleanValue = b;
	}

	public String getStringValue() {
		// TODO Auto-generated method stub
		return stringValue;
	}

}
