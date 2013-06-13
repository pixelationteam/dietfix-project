/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pup.thesis.server;

import javax.security.auth.message.callback.PrivateKeyCallback;
import javax.servlet.http.HttpServletRequest;
import pup.thesis.logging.App;

/**
 *
 * @author Dell
 */
public class AdminManager {
    private final String user;
    
    public AdminManager(String user){
        
        this.user = user;
    }
    
    public String getUser(){
        return user;
    }
    
    private static boolean checkAccount(String username,String password){
        App.log("HELLO "+username+":"+password);
        return (username.equals("TEST")&&password.equals(""));
    }
    
    public static AdminManager validate(HttpServletRequest request){
      
        Object username = request.getSession().getAttribute("dietfixuser");
        if(username==null){
            Object ruser = request.getParameter("username");
            if(ruser !=null&&checkAccount(ruser.toString(), request.getParameter("password").toString())){  
                request.getSession().setAttribute("dietfixuser", ruser.toString());
                
                return new AdminManager(ruser.toString());
            }
            else{
                return null;
            }
        }
        else{
            return new AdminManager(username.toString());
        }
        
    }
    
    
    
    
    
    
}
