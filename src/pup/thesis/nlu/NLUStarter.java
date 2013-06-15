package pup.thesis.nlu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.mvel2.sh.command.basic.Help;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.POS;

import pup.thesis.learning.reinforcement.Policy;
import pup.thesis.server.DietfixServer;
import edu.stanford.nlp.trees.Tree;

public class NLUStarter {
	
	CoreParser parser;
	NoiseDetector noise;
	private Tree parseTree;
	
	public static void main(String args[]) {
		NLUStarter start = new NLUStarter();
		start.startNLUModule("What to eat if I am losing blood?");
	}
	
	/**
	 * Prints the learned Policy
	 * 
	 * @param p
	 */
	public void printLearnedPolicy(Policy p) {
		System.out.println("==================Learned===================");
		System.out.println("Word: " + p.getInitState().getLabel());
		System.out.println("Action: " + p.getEndState().getAction());
		System.out.println("===========================================");
	}
	
	/**
	 * Prints the unknown word/s
	 * 
	 * @param uWords
	 */
	public void printUnknownWords(ArrayList<RelatedWord> uWords) {
		System.out.println("==================Unknown=Words==================");
		Iterator<RelatedWord> i = uWords.iterator();
		while(i.hasNext()) {
			RelatedWord w = i.next();
			System.out.println(w.getLabel() + " : " + w.getTag());
		}
		System.out.println("===========================================");
	}
	
	/**
	 * Executes the WordSynonym process
	 * which includes the learning process
	 * 
	 * @param cleanSentence
	 * @return
	 */
	public Tree startWordSynonym(ArrayList<RelatedWord> cleanSentence) {
		ArrayList<RelatedWord> uWords = new ArrayList<RelatedWord>();
		
		try {
			uWords = DietfixServer.getWordSynonym().getUnknownWords(cleanSentence);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ArrayList<ArrayList<Policy>> pWords = new ArrayList<ArrayList<Policy>>();
		
		if(uWords.isEmpty()) {
			System.out.println("No unknown words to be learned...");
			return  parseTree;
		}
		else {
			printUnknownWords(uWords);
			try {
				pWords = DietfixServer.getWordSynonym().getDepthOfWord(uWords);
				
				Iterator<ArrayList<Policy>> i = pWords.iterator();
				
				while(i.hasNext()) {
					boolean flag = false;
					ArrayList<Policy> p = i.next();
					flag = DietfixServer.getWordSynonym().learn(p);
					
					if(flag) {
						Policy _p = DietfixServer.getWordSynonym().evaluatePolicy(p);
						printLearnedPolicy(_p);
					}
					else {
						System.out.println("FAIL!");
					}
				}
				
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return parseTree;
	}
	
	/**
	 * Retains all the verbs, adjectives, nouns and adverbs only
	 * 
	 * @param lemma
	 * @param tag
	 * @return
	 */
	public ArrayList<RelatedWord> startNoiseDetection(ArrayList<String> lemma, ArrayList<String> tag) {
		
		System.out.println("========================Removal=of=Noise=Words=========================\n\n");
		System.out.println("Removing noise words...");
		System.out.println("Removal of noise words: ");
		
		System.out.print("From: ");
		ArrayList<RelatedWord> sentence = DietfixServer.getNoiseDetector().convertString2RelatedWord(lemma, tag);
		
		for(int i = 0; i < lemma.size(); i++) {
			System.out.print(" " + lemma.get(i) + ":" + tag.get(i));
		}
		
		System.out.print("\nTo: ");
		ArrayList<RelatedWord> nSentence = DietfixServer.getNoiseDetector().removeNoiseWords(sentence);
		Iterator<RelatedWord> i2 = nSentence.iterator();
		
		while(i2.hasNext()) {
			RelatedWord w = i2.next();
			System.out.print(" " + w.getLabel() + ":" + w.getTag().toString());
		}
		System.out.println();
		
		return nSentence;
		
	}
	
	/**
	 * 
	 * Integrates all of the NLU Processes under NLU 
	 * Doesn't include the FOL process
	 * 
	 * @param question
	 * @return
	 */
	public Tree startNLUModule(String question) {
		
		question = question.replace("?", "");
		question = question.replace(".", "");
		question = question.replace(",", "");
		question = question.replace("!", "");
		question = question.replace("'n", " not");
		
		System.out.println(question);
		if(!DietfixServer.isRunning()) {
			DietfixServer.start();
		}
		
		System.out.println("Starts the NLU Module...\n");
		System.out.println("Tokenizing the input...");
		
		System.out.println("Tokenized Input: ");
		ArrayList<String> tokens = DietfixServer.getParser().getTokens(question);
		
		Iterator<String> tokenIterator = tokens.iterator();
		
		while(tokenIterator.hasNext()) {
			System.out.println(" -> " + tokenIterator.next());
		}
		
		System.out.println("\nGetting the Part of Speech Tag...");
		
		System.out.println("POS Tag of each Word: ");
		ArrayList<String> tags = DietfixServer.getParser().getTags(question);
		
		int temp = 0;
		Iterator<String> tagsIterator = tags.iterator();
		while(tagsIterator.hasNext()) {
			System.out.println(tokens.get(temp) + " : " + tagsIterator.next());
			temp++;
		}
		
		System.out.println("\nGetting the parse tree...");
		
		System.out.println("Parse Tree of the input question: ");
		parseTree = DietfixServer.getParser().getParseTree(question);
		System.out.println(parseTree.toString());
		
		System.out.println("\nGetting lemma of each word...");
		System.out.println("Lemma of each word: ");
		
		ArrayList<String> lemma = DietfixServer.getParser().getLemma(question);
		temp = 0;
		Iterator<String> lemmaIterator = lemma.iterator();
		while(lemmaIterator.hasNext()) {
			System.out.println( tokens.get(temp) + " : " + lemmaIterator.next());
			temp++;
		}
		
		ArrayList<RelatedWord> removedNoise = startNoiseDetection(lemma, tags);
		
		Tree tree = startWordSynonym(removedNoise);
		
		return tree;
	}

}
