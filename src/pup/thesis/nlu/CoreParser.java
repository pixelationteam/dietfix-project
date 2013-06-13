package pup.thesis.nlu;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import pup.thesis.helper.StanfordHelper;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;

/**
 * 
 * 
 * 
 * @author paulzed
 * @since 4-27-2013
 */
public class CoreParser {
	
	/*
	 * This method will be responsible of parsing the input
	 * which includes the sentence parsing,
	 * tokenizing, and part-of-speech tagging.
	 * 
	 */
	
	private final LexicalizedParser lp;
	
	public CoreParser() {
		// TODO Auto-generated constructor stub
		lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
	}
	
	
	public LexicalizedParser getParser(){
		return lp;
	}
	/**
	 * 
	 * Get the tokens out of the parsed tree
	 * 
	 * @param input
	 * @return
	 */
	public ArrayList<String> getTokens(String input)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		
		tokens = StanfordHelper.getTokens(input, lp);
		
		return tokens;
	}
	
	/**
	 * 
	 * Gets the Part-of-speech tag of each word
	 * 
	 * @param input
	 * @return
	 */
	public ArrayList<String> getTags(String input) {
		
		ArrayList<String> tags = new ArrayList<String>();
		
		tags = StanfordHelper.getTags(input, lp);
		
		return tags;
	}	
	
	/**
	 * 
	 * Gets the parse tree generated in the given String
	 * 
	 * @param input
	 * @return Tree
	 */
	public Tree getParseTree(String input) {
		Tree parseTree = null;
		
		parseTree = StanfordHelper.getParseTree(input, lp);
		
		return parseTree;
	}
	
	/**
	 * 
	 * Gets the lemma(base form) of each word in the string given
	 * 
	 * @param input
	 * @return
	 */
	public ArrayList<String> getLemma(String input) {
		ArrayList<String> lemma = new ArrayList<String>();
		
		ArrayList<String> word = StanfordHelper.getTokens(input, lp);
		
		ArrayList<String> tag = StanfordHelper.getTags(input, lp);
		
		lemma = StanfordHelper.getLemmas(word, tag);
		
		return lemma;
	}
	
	public List<TypedDependency> getDependencies(Tree parseTree){
		TreebankLanguagePack tlp = new PennTreebankLanguagePack();
		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
		GrammaticalStructure gs = gsf.newGrammaticalStructure(parseTree);
		return gs.typedDependenciesCCprocessed();
	}
	
}
