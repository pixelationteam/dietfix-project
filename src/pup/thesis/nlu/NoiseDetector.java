package pup.thesis.nlu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import pup.thesis.helper.MysqlHelper;

public class NoiseDetector {
	
	MysqlHelper helper;
	
	/**
	 * 
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
	
	/**
	 * 
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
