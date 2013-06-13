package pup.thesis.nlu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import net.didion.jwnl.data.POS;
import pup.thesis.helper.JwnlHelper;
import pup.thesis.helper.MysqlHelper;

public class NoiseDetector {
	
	MysqlHelper helper;
	JwnlHelper jwnlHelper;
	
	/**
	 * 
	 * removes the noise word and returns a Arraylist of RelatedWord which
	 * doesn't include the noise words.
	 * 
	 * @param sentence
	 * @return
	 */
	public ArrayList<RelatedWord> removeNoiseWords(ArrayList<RelatedWord> sentence) {
		
		ArrayList<RelatedWord> nSentence = new ArrayList<RelatedWord>();
		boolean flag = true;
		
		try {
			ArrayList<String> noiseWords = getNoiseWordsInDb();
			
			Iterator<RelatedWord> i = sentence.iterator();
			
			while(i.hasNext()) {
				RelatedWord word = i.next();
				
				Iterator<String> i2 = noiseWords.iterator();
				
				while(i2.hasNext()) {
					
					String noise = i2.next();
					
					if(word.getLabel().equalsIgnoreCase(noise)) {
						
						flag = false;
						
					}
					
				}
				
				if(flag) {
					nSentence.add(word);
				}
				
				flag = true;
			}
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return nSentence;
	}
	
	public ArrayList<RelatedWord> convertString2RelatedWord(ArrayList<String> lemma, ArrayList<String> tag) {
		
		jwnlHelper = new JwnlHelper();
		
		ArrayList<RelatedWord> relatedWords = new ArrayList<RelatedWord>();
		
		for(int i = 0; i < lemma.size(); i++) {
			try {
				RelatedWord w = new RelatedWord();
				POS tagz = jwnlHelper.getPOS(tag.get(i));
				if(tagz.equals(null)) {
					continue;
				}
				w.setLabel(lemma.get(i));
				w.setTag(tagz);
				relatedWords.add(w);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				continue;
			}
		}
		
		return relatedWords;
	}
	
	/**
	 * 
	 * Get all the noise words enlisted in the database
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<String> getNoiseWordsInDb() throws SQLException {
		
		ArrayList<String> noiseList = new ArrayList<String>();
		
		String query = "SELECT * FROM noise_words";
		
		helper = new MysqlHelper();
		
		ResultSet set = helper.executeQuery(query);
		
		while(set.next()) {
			noiseList.add(set.getString(2));
		}
		
		return noiseList;
	}
	
}
