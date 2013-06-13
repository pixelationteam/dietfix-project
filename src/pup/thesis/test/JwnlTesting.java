package pup.thesis.test;

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
import pup.thesis.logging.App;
import pup.thesis.nlu.RelatedWord;
import pup.thesis.nlu.WordSynonym;

public class JwnlTesting {
	
	JwnlHelper helper = new JwnlHelper();
	MysqlHelper sqlHelper = new MysqlHelper();
	WordSynonym synonym = new WordSynonym();
	
	public static void main(String[] args) {
		JwnlTesting test = new JwnlTesting();
		
		test.getDepthToWords();
	}
	
	public ArrayList<RelatedWord> getDummySentence() {
		
		ArrayList<RelatedWord> demo = new ArrayList<RelatedWord>();
		
		RelatedWord w1 = new RelatedWord();
		w1.setLabel("run");
		w1.setTag(POS.VERB);
		
		RelatedWord w2 = new RelatedWord();
		w2.setLabel("talk");
		w2.setTag(POS.VERB);
		
		demo.add(w1);
		demo.add(w2);
		
		return demo;
	}
	
	public void getHyponym() {
		
		try {
			IndexWord w = helper.getWord(POS.VERB, "talk");
			IndexWord w2 = helper.getWord(POS.VERB, "feel");
			
			
		} catch (JWNLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void getDepthToWords() {
		
		ArrayList<RelatedWord> word = getDummySentence();
		
		try {
			ArrayList<RelatedWord> kWords = synonym.iterateInDb();			
			
			ArrayList<IndexWord> iWord = helper.convertToIndexWord(word);
			
			ArrayList<IndexWord> iKnow = helper.convertToIndexWord(kWords);
			
			Iterator<IndexWord> i = iWord.iterator();
			
			while(i.hasNext()) {
				IndexWord w = i.next();
				
				Iterator<IndexWord> i2 = iKnow.iterator();
				
				while(i2.hasNext()) {
					IndexWord w2 = i2.next();
					
					try {
						Relationship r = helper.getDepthOfRelationship(w.getSenses(), w2.getSenses(), PointerType.HYPERNYM);
						
						System.out.println("Relationship: " +
								w.getLemma() + " " + w.getPOS() + " - " +
										w2.getLemma() + " " + w2.getPOS() + " = " +
												r.getDepth());
						
						ArrayList<Synset> chain = helper.getRelationshipChain(r);
						
						for(int x = 0; x < chain.size(); x++) {
							Synset s = chain.get(x);
							Word[] wordz = s.getWords();
							System.out.print("\n" + x + " : ");
							for(Word _wordz : wordz) {
								System.out.print( _wordz.getLemma() + ", ");
							}
						}
						
						System.out.println("");
						
					} catch (Exception e) {
						App.log("Gotcha!");
						App.log(w2.getLemma());
					}
					
				}
				
			}
			
		} catch (JWNLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
