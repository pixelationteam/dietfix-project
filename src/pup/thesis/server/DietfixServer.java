/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pup.thesis.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import pup.thesis.knowledgebase.KBase;
import pup.thesis.knowledgebase.QueryFilter;
import pup.thesis.logging.App;
import pup.thesis.logging.Modules;
import pup.thesis.nlg.TextGenerator;
import pup.thesis.nlu.CoreParser;
import pup.thesis.nlu.FirstOrderPredicateLogic;
import pup.thesis.nlu.NLUStarter;
import pup.thesis.nlu.NoiseDetector;
import pup.thesis.nlu.WordSynonym;

/**
 *
 * @author Dell
 */
public class DietfixServer {
    private static CoreParser parser = null;
    private static NLUStarter nluStarter = null;
    private static FirstOrderPredicateLogic fopl = null;
    private static NoiseDetector noiseDetect = null;
    private static WordSynonym wordSynonym = null;
    private static TextGenerator textgen = null;
    private static KBase kbase = null;

    
    private static void init(){
        App.log(Modules.SYSTEM,"Dietfixser:21", "Starting server.");
        App.log(Modules.SYSTEM,"Loading Modules Needed for NLU..");
        parser=new CoreParser();
        nluStarter = new NLUStarter();
        noiseDetect = new NoiseDetector();
        wordSynonym = new WordSynonym();
        App.log(Modules.SYSTEM,"Loading Text Generator..");
        textgen = new TextGenerator();
        App.log(Modules.SYSTEM,"Loading Knowledge Bases..");
        kbase = new KBase();
        fopl = new FirstOrderPredicateLogic();
        App.log(Modules.SYSTEM,"Dietfixser:", "Server Started");
    }
    public static void start(){
        init();
    }
    public static void stop(){
        parser = null;
        
    }
    
    public static boolean isRunning(){
        return parser!=null;
    	//return true;
    }
    
    public static NLUStarter getNLUStarter(){
    	return nluStarter;
    }
    
    public static WordSynonym getWordSynonym() {
    	return wordSynonym;
    }
    
    public static NoiseDetector getNoiseDetector() {
    	return noiseDetect;
    }
    
    public static CoreParser getParser(){
        return parser;
    }
    
    public static TextGenerator getTextGenerator(){
    	return textgen;
    }
    
    public static KBase getKBase(){
    	return kbase;
    }
    
    public static FirstOrderPredicateLogic getFOPL(){
    	return fopl;
    }
  
   
}
