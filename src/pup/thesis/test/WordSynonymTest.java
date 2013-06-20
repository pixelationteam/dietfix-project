package pup.thesis.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import pup.thesis.helper.JwnlHelper;
import pup.thesis.helper.MysqlHelper;
import pup.thesis.learning.reinforcement.ReinforcementLearning;
import pup.thesis.nlu.RelatedWord;
import pup.thesis.nlu.WordSynonym;

import com.mysql.jdbc.log.Log;

import edu.cmu.lti.lexical_db.ILexicalDatabase;
import edu.cmu.lti.lexical_db.NictWordNet;
import edu.cmu.lti.ws4j.RelatednessCalculator;
import edu.cmu.lti.ws4j.impl.HirstStOnge;
import edu.cmu.lti.ws4j.impl.JiangConrath;
import edu.cmu.lti.ws4j.impl.LeacockChodorow;
import edu.cmu.lti.ws4j.impl.Lesk;
import edu.cmu.lti.ws4j.impl.Lin;
import edu.cmu.lti.ws4j.impl.Path;
import edu.cmu.lti.ws4j.impl.Resnik;
import edu.cmu.lti.ws4j.impl.WuPalmer;

/**
 * Class that will determine whether
 * a word exist on the database. If the
 * word doesn't exist in the database, it will
 * find the synonyms of that word which could
 * possibly be on the database.
 * 
 * This class also utilizes Reinforcement Learning
 * 
 * 
 * @since 05-20-2013
 * @author paulzed
 *
 */
public class WordSynonymTest extends ReinforcementLearning {
	
	private JwnlHelper helper;
	private MysqlHelper sqlHelper;
	private Log log;
	private WordSynonym synonym;
	 private static ILexicalDatabase db = new NictWordNet();
     private static RelatednessCalculator[] rcs = {
                     new HirstStOnge(db), new LeacockChodorow(db), new Lesk(db),  new WuPalmer(db), 
                     new Resnik(db), new JiangConrath(db), new Lin(db), new Path(db)
                     };
     
     private static void run( String word1, String word2 ) {
             //WS4JConfiguration.getInstance().setMFS(true);
             for ( RelatednessCalculator rc : rcs ) {
                     double s = rc.calcRelatednessOfWords(word1, word2);
                     System.out.println( rc.getClass().getName()+"\t"+s );
             }
     }
     public static void main(String[] args) {
    	 	WordSynonymTest test = new WordSynonymTest();
    	 	ArrayList<RelatedWord> wrd = new ArrayList<RelatedWord>();
    	 	System.out.println(System.currentTimeMillis());
			try {
				wrd = test.iterateInDb("VB");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(System.currentTimeMillis());
			long t0 = System.currentTimeMillis();
    	 	String c = "could";
    	 	run("eat", c);
    	 	Iterator<RelatedWord> i = wrd.iterator();
             
             while(i.hasNext()) {
            	 RelatedWord s = i.next();
            	 System.out.println("eat -> " + s.getLabel());
          	   	run( "eat", s.getLabel());
             }
             long t1 = System.currentTimeMillis();
             System.out.println( "Done in "+(t1-t0)+" msec." );
     }
	
	/**
	 * 
	 * Checks whether the word exist in the list of known_words
	 * in the database
	 * 
	 * @param word
	 * @return
	 * @throws SQLException
	 */
	public boolean isWordExistInDb(RelatedWord word) throws SQLException {
		
		ArrayList<RelatedWord> kWords = new ArrayList<RelatedWord>();
		
		kWords = iterateInDb("");
		
		Iterator<RelatedWord> i = kWords.iterator();
		
			
		RelatedWord _word = word;
			
		while(i.hasNext()) {
				
			RelatedWord kWord = i.next();
				
			if(isSame(_word.getLabel(), _word.getTag(), kWord.getLabel(), kWord.getTag())) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * Converts the Arraylist of synset to Arraylist of String
	 * 
	 * @param sets
	 * @return
	 */
	public ArrayList<String> synset2String(ArrayList<Synset> sets) {
		ArrayList<String> synsets = new ArrayList<String>();
		
		Iterator<Synset> i = sets.iterator();
		
		while(i.hasNext()) {
			Synset synset = i.next();
			Word[] words = synset.getWords();
			
			for(int x = 0; x < words.length; x++) {
				synsets.add(words[x].getLemma());
			}
		}
		
		return synsets;
	}
	
}