package pup.thesis.console;

import java.util.Date;

public class Application {

    private Date dateApplied;

    private boolean valid;

    public Date dateApplied(){
    	return dateApplied;
    }
    
    public void setValid(boolean b){
    	valid = b;
    }
    
    public boolean isValid(){
    	return valid;
    }
    // getter and setter methods here

}