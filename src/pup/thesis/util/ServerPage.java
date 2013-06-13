/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pup.thesis.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.instrument.IllegalClassFormatException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pup.thesis.logging.App;
import pup.thesis.server.DietfixServer;

/**
 *
 * @author Dell
 */
public abstract class ServerPage {
    private ArrayList<String> errors = new ArrayList<String>();
    private final long pageStart;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final ClientData clientdata;
    public ServerPage(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
        ClientData cd;
        try {
            cd = (ClientData) request.getAttribute("clientdata");
            if(cd==null){
            	throw new IllegalClassFormatException();
            }
        }catch (IllegalClassFormatException e) {
			errors.add("ServerPage: No client data.");
			cd = null;
		}
        clientdata = cd;
        pageStart = new Date().getTime();
        if(DietfixServer.isRunning()){
        	initialize();
        }
        else{
        	errors.add("ServerPage: Server is not running");
        }
    }
    
    protected abstract void initialize();
    
    public HttpServletRequest getRequest(){
    	return request;
    }
    public HttpServletResponse getResponse(){
    	return response;
    }
    public ClientData getClientData(){
    	return clientdata;
    }
   
    public boolean hasErrors(){
        return errors.size()>0;
    }
    public List<String> getErrors(){
        return errors;
    }
    protected void addError(String error){
        errors.add(error);
    }
    
    public String displayRuntime(){
        String ipAddress = request.getHeader("X-FORWARDED-FOR");  
   if (ipAddress == null) {  
	   ipAddress = request.getRemoteAddr();  
   }
        return String.format("Server runtime for Client[%s]:%sms", ipAddress,(new Date().getTime() - pageStart));
    }
}
