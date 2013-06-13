package pup.thesis.learning.reinforcement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.relationship.Relationship;
import pup.thesis.helper.JwnlHelper;
import pup.thesis.helper.MysqlHelper;
import pup.thesis.nlu.RelatedWord;

public abstract class ReinforcementLearning {
	
	private JwnlHelper helper;
	private MysqlHelper sqlHelper;
	private Reward reward;
	
	/**
	 * 
	 * 
	 * @param words
	 * @return
	 */
	public boolean learn(ArrayList<Policy> words) {
		
		helper = new JwnlHelper();
		sqlHelper = new MysqlHelper();
		
		//gets the optimized policy based on lowest depth
		Policy opti = evaluatePolicy(words);
		
		String sTemp = opti.getInitState().getLabel();
		POS pTemp = opti.getInitState().getTag();
		String aTemp = opti.getEndState().getAction();
		
		//learn the freaking word
		//still no idea about the action thingy here...
		String query = "INSERT INTO known_words VALUES(" +
				"null, '" + sTemp + "', '" + helper.getPOS(pTemp) + "', " + aTemp + ")";
		
		boolean flag = sqlHelper.updateDb(query);
		
		if(!flag) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param policies
	 * @return
	 */
	public Policy evaluatePolicy(ArrayList<Policy> policies) {
		Policy optimizedPolicy = new Policy();
		int temp = 100;
		
		Iterator<Policy> i = policies.iterator();
		
		while(i.hasNext()) {
			Policy p = i.next();
			
			if(p.getReward() == -1) {
				continue;
			}
			else {
				if(temp > p.getReward()) {
					optimizedPolicy = p;
					temp = p.getReward();
				}
			}
		}
		
		return optimizedPolicy;
	}
	
	
	
	/**
	 * 
	 * @param set
	 * @throws SQLException
	 * @return
	 */
	public ArrayList<RelatedWord> iterateInDb() throws SQLException {
		
		ArrayList<RelatedWord> listRelated = new ArrayList<RelatedWord>();
		helper = new JwnlHelper();
		sqlHelper = new MysqlHelper();
		ResultSet set = sqlHelper.executeQuery("SELECT * FROM known_words");
		
		while(set.next()) {
			RelatedWord rlWord = new RelatedWord();
			rlWord.setLabel(set.getString(2));
			rlWord.setTag(helper.getPOS(set.getString(3)));
			rlWord.setAction(set.getString(4));
			listRelated.add(rlWord);
		}
		
		sqlHelper.closeConnections();
		return listRelated;
	}
	
	
	
	/**
	 * 
	 * 
	 * @param word
	 * @return
	 */
	public Policy iterateDbToGetDepth(IndexWord word) {
		
		reward = new Reward();
		Policy p = new Policy();

		PointerType type = null;
		Relationship rel = null;
		
		int depth = 0;
		int r = 0;
		
		try {
			ArrayList<RelatedWord> words = iterateInDb();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
		
	}
	
	/**
	 * 
	 * 
	 * @param sentence
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<RelatedWord> getUnknownWords(ArrayList<RelatedWord> sentence) throws SQLException {
		
		ArrayList<RelatedWord> unknown = new ArrayList<RelatedWord>();
		
		ArrayList<RelatedWord> kWords = iterateInDb();
		
		boolean flag = false;
		
		Iterator<RelatedWord> i2 = sentence.iterator();
		
		while(i2.hasNext()) {
			RelatedWord word = i2.next();
			Iterator<RelatedWord> i = kWords.iterator();
			while(i.hasNext()) {
				RelatedWord k = i.next();
				
				if(word.equals(k)) {
					flag = false;
					break;
				}
				else {
					flag = true;
				}
			}
			
			if(flag) unknown.add(word);
			
			//reset the flag
			flag = false;
		}
		
		return unknown;
	}
	
	/**
	 * 
	 * 
	 * @param word
	 * @return
	 * @throws JWNLException
	 */
	public ArrayList<ArrayList<Policy>> getDepthOfWord(ArrayList<RelatedWord> word) throws JWNLException {
		
		helper = new JwnlHelper();
		
		Reward reward = new Reward();
		
		ArrayList<ArrayList<Policy>> listPolicy = new ArrayList<ArrayList<Policy>>();
		
		try {
			ArrayList<RelatedWord> kWords = iterateInDb();
			
			Iterator<RelatedWord> i = word.iterator();
			
			while(i.hasNext()) {
				ArrayList<Policy> p = new ArrayList<Policy>();
				Iterator<RelatedWord> i2 = kWords.iterator();
				RelatedWord w = i.next();
				while(i2.hasNext()) {
					RelatedWord w2 = i2.next();
					IndexWord _w = helper.convertToIndexWord(w);
					IndexWord _w2 = helper.convertToIndexWord(w2);
					Policy _p = new Policy();
					
					try {
						Relationship r = helper.getDepthOfRelationship(_w.getSenses(), _w2.getSenses(), PointerType.HYPERNYM);
					
						_p.setInitState(w);
						_p.setEndState(w2);
					
						_p.setReward(r.getDepth());
					} catch(Exception e) {
						RelatedWord word1 = new RelatedWord();
						RelatedWord word2 = new RelatedWord();
						word1.setLabel("");
						word1.setTag(POS.VERB);
						word2.setLabel("");
						word2.setTag(POS.VERB);
						_p.setReward(-1);
						_p.setInitState(word1);
						_p.setEndState(word2);
					}
					
					p.add(_p);
				}
				
				listPolicy.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return listPolicy;
	}
	
	/**
	 * 
	 * 
	 * @param word
	 * @param synonym
	 * @return
	 */
	public boolean isSame(String word, POS wordTag, String synonym, POS synonymTag) {
		return (word.equalsIgnoreCase(synonym) && wordTag.equals(synonymTag)) ? true : false;
	}
	
}