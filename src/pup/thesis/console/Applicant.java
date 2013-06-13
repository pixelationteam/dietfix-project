package pup.thesis.console;

public class Applicant {

    public Applicant(String string, int i) {
		// TODO Auto-generated constructor stub
    	age = i;
    	name = string;
	}
    
    public boolean isValid(){
    	return valid;
    }

    public int age(){
    	return age;
    }
    public void setValid(boolean b){
    	valid = b;
    }
	private String name;

    private int age;

    private boolean valid;

    // getter and setter methods here

}
