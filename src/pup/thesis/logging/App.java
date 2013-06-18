/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pup.thesis.logging;


/**
 *
 * @author Dell
 */
public class App {
	StringBuilder NLU,IR,NLG;
	String input;
    public static void log(Modules mod, String source,Object e){
    	String ptext = String.format("%s => %s", source,e);
        System.out.println(String.format("[%s] : %s",mod, ptext));
    }
    
    public static void log(Modules all,Object e){
    	System.out.println(String.format("[%s] : %s", all,e));
    }
    
    public static void log(Object s){
    	System.out.println(String.format("[%s] : %s", Modules.SYSTEM,s));
    }
}
