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
    public static void log(String source,Object e){
        System.out.println(String.format("%s => %s", source,e));
    }
    public static void log(Object e){
    	System.out.println(e);
    }
}
