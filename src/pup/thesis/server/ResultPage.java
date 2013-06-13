/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pup.thesis.server;

import edu.stanford.nlp.trees.Tree;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pup.thesis.helper.MysqlHelper;
import pup.thesis.util.ServerPage;
import pup.thesis.util.mysql.SafeConnection;
import pup.thesis.logging.App;
import pup.thesis.nlu.CoreParser;
import simplenlg.aggregation.AggregationRule;
import simplenlg.aggregation.Aggregator;
import simplenlg.aggregation.ClauseCoordinationRule;
import simplenlg.features.Feature;
import simplenlg.framework.CoordinatedPhraseElement;
import simplenlg.framework.NLGElement;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;

/**
 *
 * @author Dell
 */
public class ResultPage extends ServerPage {

    private Connection con;
    private String input;
    private ArrayList<String> token;
    private ArrayList<String> tag;
    private ArrayList<String> lemma;
    private Tree parseTree;

    public ResultPage(HttpServletRequest request, HttpServletResponse response) {
        super(request,response);
    }
    
    @Override
    public void initialize(){
    	 
    }

    public String getInput(){
        return input;
    }
   
    
    
}
