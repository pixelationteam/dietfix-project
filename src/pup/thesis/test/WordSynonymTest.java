package pup.thesis.test;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.POS;

import pup.thesis.learning.reinforcement.Policy;

import pup.thesis.logging.App;
import pup.thesis.nlu.RelatedWord;
import pup.thesis.nlu.WordSynonym;
/**
 * 
 * Provides functions to test the WordSynonym class
 * 
 * @author paulzed
 *
 */
public class WordSynonymTest {
	
	WordSynonym testWord;
	
	public static void main(String[] args) {
		WordSynonymTest test = new WordSynonymTest();
		
		
		//test.getWordsInDb();
		//test.checkIfExist();
		//test.getUnknownWords();
		test.getDepthOfWord();
		test.getOptimizedPolicy();
		//test.learnUnknownWord();
		test.learnning2();
	}
	
	public void getOptimizedPolicy() {
		
		ArrayList<Policy> pList = getDummyPolicies();
		
		Policy opti = testWord.evaluatePolicy(pList);
		
		System.out.println("===================Optimized=Policy===================");
		RelatedWord start = opti.getInitState();
		RelatedWord end = opti.getEndState();
		
		int rew = opti.getReward();
		
		System.out.println("Start State: " + start.getLabel() + " - " + start.getTag().toString());
		System.out.println("End State: " + end.getLabel() + " - " + end.getTag().toString());
		System.out.println("Reward: " + rew);
	}
	
	public ArrayList<Policy> getDummyPolicies() {
		ArrayList<Policy> pList = new ArrayList<Policy>();
		RelatedWord w1 = new RelatedWord();
		RelatedWord _w1 = new RelatedWord();
		RelatedWord w2 = new RelatedWord();
		RelatedWord _w2 = new RelatedWord();
		RelatedWord w3 = new RelatedWord();
		RelatedWord _w3 = new RelatedWord();
		RelatedWord w4 = new RelatedWord();
		RelatedWord _w4 = new RelatedWord();
		RelatedWord w5 = new RelatedWord();
		RelatedWord _w5 = new RelatedWord();
		testWord = new WordSynonym();
		
		
		Policy p1 = new Policy();
		Policy p2 = new Policy();
		Policy p3 = new Policy();
		Policy p4 = new Policy();
		Policy p5 = new Policy();
		
		w1.setLabel("testLabel1");
		w1.setTag(POS.ADJECTIVE);
		w1.setAction("1");
		p1.setInitState(w1);
		p1.setReward(7);
		_w1.setLabel("testEnd1");
		_w1.setTag(POS.ADJECTIVE);
		p1.setEndState(_w1);
		
		w2.setLabel("testLabel1");
		w2.setTag(POS.ADJECTIVE);
		w2.setAction("2");
		p2.setInitState(w2);
		p2.setReward(5);
		_w2.setLabel("testEnd2");
		_w2.setTag(POS.ADJECTIVE);
		p2.setEndState(_w2);
		
		w3.setLabel("testLabel1");
		w3.setTag(POS.ADJECTIVE);
		w3.setAction("3");
		p3.setInitState(w3);
		p3.setReward(3);
		_w3.setLabel("testEnd3");
		_w3.setTag(POS.ADJECTIVE);
		p3.setEndState(_w3);
		
		w4.setLabel("testLabel1");
		w4.setTag(POS.ADJECTIVE);
		w4.setAction("4");
		p4.setInitState(w4);
		p4.setReward(2);
		_w4.setLabel("testEnd4");
		_w4.setTag(POS.ADJECTIVE);
		p4.setEndState(_w4);
		
		w5.setLabel("testLabel1");
		w5.setTag(POS.ADJECTIVE);
		w5.setAction("5");
		p5.setInitState(w5);
		p5.setReward(3);
		_w5.setLabel("testEnd5");
		_w5.setTag(POS.ADJECTIVE);
		p5.setEndState(_w5);
		
		pList.add(p1);
		pList.add(p2);
		pList.add(p3);
		pList.add(p4);
		pList.add(p5);
		
		return pList;
	}
	
	public void learnUnknownWord() {
		ArrayList<Policy> p = getDummyPolicies();
		
		testWord = new WordSynonym();
		
		boolean flag = testWord.learn(p);
		
		if(flag) {
			Policy _p = testWord.evaluatePolicy(p);
			
			System.out.println("Learned: ");
			System.out.println("Init State: " +
					_p.getInitState().getLabel() + " " +
							"" + _p.getInitState().getTag().toString());
			System.out.println("End State: " +
					_p.getEndState().getLabel() + " " +
							"" + _p.getEndState().getTag().toString());
			System.out.println("Reward: " + _p.getReward());
		}
		
	}
	
	public void checkIfExist() {
		
		RelatedWord word = new RelatedWord();
		testWord = new WordSynonym();
		
		word.setLabel("work");
		word.setTag(POS.VERB);
		
		try {
			System.out.println(testWord.isWordExistInDb(word));
			System.out.println(word.getTag().equals(POS.VERB));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void printLearned(Policy p) {
		
		RelatedWord init = p.getInitState();
		RelatedWord end = p.getEndState();
		int reward = p.getReward();
		
		System.out.println("Successfully Learned: ");
		System.out.println(" Init State -> " + init.getLabel() + " " + init.getTag());
		System.out.println(" End State -> " + end.getLabel() + " " + end.getTag());
		System.out.println(" Action -> " + end.getAction());
		
	}
	
	/*
	public void iii() {
		String e = "adorable," +
				"adventurous," +
				"aggressive," +
				"agreeable," +
				"alert," +
				"alive," +
				"amused," +
				"angry," +
				"annoyed," +
				"annoying," +
				"anxious," +
				"arrogant," +
				"ashamed," +
				"attractive," +
				"average," +
				"awful," +
				"bad," +
				"beautiful," +
				"better," +
				"bewildered," +
				"black," +
				"bloody," +
				"blue," +
				"blue-eyed," +
				"blushing," +
				"bored," +
				"brainy," +
				"brave," +
				"breakable," +
				"bright," +
				"busy," +
				"calm," +
				"careful," +
				"cautious," +
				"charming," +
				"cheerful," +
				"clean," +
				"clear," +
				"clever," +
				"cloudy," +
				"clumsy," +
				"colorful," +
				"combative," +
				"comfortable," +
				"concerned," +
				"condemned," +
				"confused," +
				"cooperative," +
				"courageous," +
				"crazy," +
				"creepy," +
				"crowded," +
				"cruel," +
				"curious," +
				"cute," +
				"dangerous," +
				"dark," +
				"dead," +
				"defeated," +
				"defiant," +
				"delightful," +
				"depressed," +
				"determined," +
				"different," +
				"difficult," +
				"disgusted," +
				"distinct," +
				"disturbed," +
				"dizzy," +
				"doubtful," +
				"drab," +
				"dull," +
				"eager," +
				"easy," +
				"elated," +
				"elegant," +
				"embarrassed," +
				"enchanting," +
				"encouraging
		energetic
		enthusiastic
		envious
		evil
		excited
		expensive
		exuberant
		fair
		faithful
		famous
		fancy
		fantastic
		fierce
		filthy
		fine
		foolish
		fragile
		frail
		frantic
		friendly
		frightened
		funny
		gentle
		gifted
		glamorous
		gleaming
		glorious
		good
		gorgeous
		graceful
		grieving
		grotesque
		grumpy
		handsome
		happy
		healthy
		helpful
		helpless
		hilarious
		homeless
		homely
		horrible
		hungry
		hurt
		ill
		important
		impossible
		inexpensive
		innocent
		inquisitive
		itchy
		jealous
		jittery
		jolly
		joyous
		kind
		lazy
		light
		lively
		lonely
		long
		lovely
		lucky
		magnificent
		misty
		modern
		motionless
		muddy
		mushy
		mysterious
		nasty
		naughty
		nervous
		nice
		nutty
		obedient
		obnoxious
		odd
		old-fashioned
		open
		outrageous
		outstanding
		panicky
		perfect
		plain
		pleasant
		poised
		poor
		powerful
		precious
		prickly
		proud
		puzzled
		quaint
		real
		relieved
		repulsive
		rich
		scary
		selfish
		shiny
		shy
		silly
		sleepy
		smiling
		smoggy
		sore
		sparkling
		splendid
		spotless
		stormy
		strange
		stupid
		successful
		super
		talented
		tame
		tender
		tense
		terrible
		testy
		thankful
		thoughtful
		thoughtless
		tired
		tough
		troubled
		ugliest
		ugly
		uninterested
		unsightly
		unusual
		upset
		uptight
		vast
		victorious
		vivacious
		wandering
		weary
		wicked
		wide-eyed
		wild
		witty
		worrisome
		worried
		wrong
		zany
		zealous";
	}
	*/
	public void learnning2() {
		
		testWord = new WordSynonym();
		
		ArrayList<RelatedWord> dummy = getDummySentence();
		
		try {
			ArrayList<RelatedWord> uWords = testWord.getUnknownWords(dummy);
			
			if (!uWords.isEmpty()) {
				ArrayList<ArrayList<Policy>> policies = testWord
						.getDepthOfWord(uWords);
				Iterator<ArrayList<Policy>> i = policies.iterator();
				while (i.hasNext()) {
					ArrayList<Policy> p = i.next();

					boolean flag = false;
					flag = testWord.learn(p);

					if (flag) {
						Policy _p = testWord.evaluatePolicy(p);
						printLearned(_p);
					}

				}
			}
			else {
				System.out.println("No unknown word found");
			}
			
		} catch(JWNLException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<RelatedWord> getDummySentence() {
		
		ArrayList<RelatedWord> demo = new ArrayList<RelatedWord>();
		
		RelatedWord w1 = new RelatedWord();
		w1.setLabel("jog");
		w1.setTag(POS.VERB);
		w1.setAction("3");
		
		RelatedWord w2 = new RelatedWord();
		w2.setLabel("tell");
		w2.setTag(POS.VERB);
		w2.setAction("1");
		
		demo.add(w1);
		demo.add(w2);
		
		return demo;
	}
	
	public void getDepthOfWord() {
		
		int temp = 0;
		
		testWord = new WordSynonym();
		
		ArrayList<RelatedWord> dummy = getDummySentence();
		
		ArrayList<ArrayList<Policy>> p;
		
		try {
			
			p = testWord.getDepthOfWord(dummy);
			
			System.out.println("=================Depth of Words====================");
			
			Iterator<ArrayList<Policy>> i = p.iterator();
			
			while(i.hasNext()) {
				ArrayList<Policy> pList = i.next();
				
				Iterator<Policy> i2 = pList.iterator();
				
				System.out.println(temp + " : ");
				
				while(i2.hasNext()) {
					Policy _p = i2.next();
					
					RelatedWord init = _p.getInitState();
					RelatedWord end = _p.getEndState();
					
					String _init = init.getLabel() + " - " + init.getTag().toString();
					String _end = end.getLabel() + " - " + end.getTag().toString();
					
					System.out.println("Init State: " + _init);
					System.out.println("End State: " + _end);
					System.out.println("Reward: " + _p.getReward());
					
					System.out.println("------------------------------");
				}
				
				temp++; 
			}
			
		} catch (JWNLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void getUnknownWords() {
		
		testWord = new WordSynonym();
		
		try {
			ArrayList<RelatedWord> uWords = testWord.getUnknownWords(getDummySentence());
			
			Iterator<RelatedWord> i = uWords.iterator();
			
			System.out.println("=====================Unknown Words=============================");
			
			while(i.hasNext()) {
				RelatedWord w = i.next();
				
				System.out.println(w.getLabel() + " " + w.getTag().toString());
			}
			
			System.out.println("=====================Unknown Words=============================");
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void getWordsInDb() {
		
		testWord = new WordSynonym();
		
		try {
			ArrayList<RelatedWord> word = testWord.iterateInDb();
			
			Iterator<RelatedWord> i = word.iterator();
			
			while(i.hasNext()) {
				
				RelatedWord w = i.next();
				
				App.log(w.getLabel() + " " + w.getTag() + " " + w.getAction());
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int getDepth() {
		
		int depth = 0;
		
		testWord = new WordSynonym();
		
		return depth;
	}
	
}
