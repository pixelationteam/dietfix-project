package pup.thesis.nlu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.data.relationship.Relationship;
import pup.thesis.helper.JwnlHelper;
import pup.thesis.helper.MysqlHelper;
import pup.thesis.learning.reinforcement.ReinforcementLearning;
import pup.thesis.logging.App;

import com.mysql.jdbc.log.Log;

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
public class WordSynonym extends ReinforcementLearning {
	
	private JwnlHelper helper;
	private MysqlHelper sqlHelper;
	private Log log;
	
	
	public boolean startTranslationOfWords(ArrayList<RelatedWord> sentence) {
		
		
		
		return true;
	}
	
	public boolean learnWord(ArrayList<RelatedWord> word) {
		
		
		
		return true;
	}
	
	/**
	 * 
	 * 
	 * @param word
	 * @return
	 * @throws SQLException
	 */
	public boolean isWordExistInDb(RelatedWord word) throws SQLException {
		
		ArrayList<RelatedWord> kWords = new ArrayList<RelatedWord>();
		
		kWords = iterateInDb();
		
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
	
	/**
	 * 
	 * 
	 * @param word
	 * @return
	 * @throws SQLException
	 */
	public boolean updateKnownWords(RelatedWord word) throws SQLException {
		
		sqlHelper = new MysqlHelper();
		
		String query = "INSERT INTO known_words VALUES(" +
				"null, " + word.getLabel() + ", " + word.getTag() + ", " + word.getAction() + ")" ;
		
		return (sqlHelper.updateDb(query)) ? true : false;
	}
	
	
	
}
