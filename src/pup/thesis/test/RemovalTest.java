package pup.thesis.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import pup.thesis.logging.App;
import pup.thesis.nlu.NoiseDetector;
import pup.thesis.nlu.RelatedWord;

public class RemovalTest {
	
	NoiseDetector detect = new NoiseDetector();
	
	public static void main(String[] args) {
		RemovalTest test = new RemovalTest();
		test.dispalyNoiseWordsInDb();
		test.detectNoiseWord();
	}
	
	public ArrayList<RelatedWord> getDummySentence() {
		ArrayList<RelatedWord> nList = new ArrayList<RelatedWord>();
		
		RelatedWord n1 = new RelatedWord();
		RelatedWord n2 = new RelatedWord();
		RelatedWord n3 = new RelatedWord();
		RelatedWord n4 = new RelatedWord();
		
		n1.setLabel("I");
		n2.setLabel("am");
		n3.setLabel("not");
		n4.setLabel("sleepy");
		
		nList.add(n1);
		nList.add(n2);
		nList.add(n3);
		nList.add(n4);
		
		return nList;
	}
	
	public void dispalyNoiseWordsInDb() {
		
		try {
			ArrayList<String> noiseWords = detect.getNoiseWordsInDb();
			
			Iterator<String> i = noiseWords.iterator();
			System.out.println("=======================Noise=Words=======================");
			while(i.hasNext()) {
				System.out.println(i.next());
			}
			System.out.println("=======================Noise=Words=======================");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void detectNoiseWord() {
		
		ArrayList<RelatedWord> sentence = getDummySentence();
		
		ArrayList<RelatedWord> newSentence = detect.removeNoiseWords(sentence);
		
		Iterator<RelatedWord> i = newSentence.iterator();
		System.out.println("=======================New=Sentence=======================");
		while(i.hasNext()) {
			System.out.println(i.next().getLabel());
		}
		System.out.println("=======================New=Sentence=======================");
	}
	
}
