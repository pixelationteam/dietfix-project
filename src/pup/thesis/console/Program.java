package pup.thesis.console;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;
import org.jooq.Record;
import org.jooq.ResultQuery;

import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Pointer;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.dictionary.Dictionary;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.Label;
import edu.stanford.nlp.ling.LabeledWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TypedDependency;
import pup.thesis.helper.MysqlHelper;

import pup.thesis.util.ClientData;
import pup.thesis.util.mysql.DBQuerier;
import pup.thesis.helper.StanfordHelper;
import pup.thesis.knowledgebase.AnswerData;
import pup.thesis.knowledgebase.KBase;
import pup.thesis.knowledgebase.KeyTag;
import pup.thesis.knowledgebase.KeyTagSet;
import pup.thesis.knowledgebase.QueryFilter;
import pup.thesis.knowledgebase.expert.Dietitian;
import pup.thesis.knowledgebase.expert.ExpertAnswer;
import pup.thesis.knowledgebase.expert.Experts;
import pup.thesis.knowledgebase.expert.FitnessInstructor;
import pup.thesis.knowledgebase.expert.Food;
import pup.thesis.knowledgebase.expert.Nutrients;
import pup.thesis.logging.App;
import pup.thesis.nlu.CoreParser;
import pup.thesis.nlu.pos.TypedDep;
import pup.thesis.nlu.pos.PhraseProcessor;
import pup.thesis.nlu.pos.Word;
import pup.thesis.querygeneration.FoodQG;
import pup.thesis.server.DietfixServer;
import pup.thesis.server.DietfixServer;

import simplenlg.aggregation.AggregationRule;
import simplenlg.aggregation.Aggregator;
import simplenlg.aggregation.BackwardConjunctionReductionRule;
import simplenlg.aggregation.ClauseCoordinationRule;
import simplenlg.aggregation.ForwardConjunctionReductionRule;
import simplenlg.features.Feature;
import simplenlg.features.Form;
import simplenlg.features.InterrogativeType;
import simplenlg.features.Tense;
import simplenlg.framework.CoordinatedPhraseElement;
import simplenlg.framework.ElementCategory;
import simplenlg.framework.LexicalCategory;
import simplenlg.framework.NLGElement;
import simplenlg.framework.NLGFactory;
import simplenlg.framework.PhraseCategory;
import simplenlg.framework.WordElement;
import simplenlg.lexicon.Lexicon;
import simplenlg.lexicon.NIHDBLexicon;
import simplenlg.lexicon.XMLLexicon;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.PPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.phrasespec.VPPhraseSpec;
import simplenlg.realiser.english.Realiser;

public class Program {

	private static void insertAnswer(Experts x, String sentence,KeyTagSet keytags,HashMap<Integer,String> hm) {
		
		ClientData cdata = new ClientData();
		Connection conn;

		CoreParser parser;
		if (!DietfixServer.isRunning()) {
			
			DietfixServer.start();
		}
		parser = DietfixServer.getParser();
		Tree pt = parser.getParseTree(sentence);

		
		
		List<CoreLabel> wlabel = pt.taggedLabeledYield();
		Word[] words = new Word[wlabel.size()];
		for(int i = 0;i<words.length;i++){
			words[i] = new Word(wlabel.get(i).index(),wlabel.get(i).tag(),wlabel.get(i).word());
		}
		List<TypedDependency> tdep = parser.getDependencies(pt);
		TypedDep[] tda = new TypedDep[tdep.size()];
		for (int i = 0; i < tdep.size(); i++) {
			tda[i] = new TypedDep(tdep.get(i));
			App.log(tda[i].toString());
		}

		AnswerData ad= new AnswerData(tda,hm);

		/*
		StatefulKnowledgeSession ksession  = DietfixServer.getKBase().getNLGKBase().newStatefulKnowledgeSession();
		PhraseProcessor asnt = new PhraseProcessor(cdata,tda,hm); 
		ksession.insert(asnt);
		ksession.fireAllRules();
		for(SPhraseSpec specsnt : asnt.getSPhrases()){
		App.log(DietfixServer.getTextGenerator().getRealiser().realiseSentence(specsnt));
		}
			*/	
		String query = "insert into dietfix_ans(AnsObj,AnsExpert,AnsDesc) values (?,?,?)";

		try {
			conn = MysqlHelper.createDietfixConnection(cdata);
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setObject(1, (Object) ad);
			statement.setString(2, x.name());
			statement.setString(3, keytags.toString());
			statement.execute();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		App.log("Done");
	}
	
	private static void testInsert() {
		/*KeyTagSet kts = new KeyTagSet();
		kts.addKeyTag(new KeyTag(1,"FOOD"));
		kts.addKeyTag(new KeyTag(2,"BLOOD"));
		kts.addKeyTag(new KeyTag(2,"PROTEIN"));
		kts.addKeyTag(new KeyTag(2,"RICH"));
		HashMap<Integer,String> hm = new HashMap<Integer,String>();
		//hm.put(4, "FOOD:01084");
		insertAnswer(Experts.DIETITIAN,"Food rich in protein",kts,hm);*/
		
		KeyTagSet kts = new KeyTagSet();
		kts.addKeyTag(new KeyTag(1,"FOOD"));
		kts.addKeyTag(new KeyTag(1,"FRUIT"));
		kts.addKeyTag(new KeyTag(2,"BLOOD"));
		kts.addKeyTag(new KeyTag(2,"PROTEIN"));
		HashMap<Integer,String> hm = new HashMap<Integer,String>();
		hm.put(3, "FOOD:11530");
		insertAnswer(Experts.NUTRITIONIST,"You eat food",kts,hm);
/*		
		KeyTagSet kts = new KeyTagSet();
		kts.addKeyTag(new KeyTag(1,"FOOD"));
		kts.addKeyTag(new KeyTag(2,"BLOOD"));
		kts.addKeyTag(new KeyTag(3,"SUGAR"));
		HashMap<Integer,String> hm = new HashMap<Integer,String>();
		insertAnswer(Experts.DIETITIAN,"You avoid chocolates",kts,hm);*/
	}

	public static void main(String... args) throws JWNLException {

		testNLG3();System.exit(0);
		//testInsert();System.exit(0);
		ClientData cdata = new ClientData();
		
	
		if(!DietfixServer.isRunning()){
			DietfixServer.start();
		}
		//Tree inputpt = DietfixServer.getParser().getParseTree("What should I do to lose weight fast?");
		Tree inputpt = DietfixServer.getNLUStarter().startNLUModule("What to eat if I am losing blood?");
		
		try {
			App.log("");
			App.log("Press any key to proceed..");
			System.in.read();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		List<TypedDep> xdeplist = DietfixServer.getFOPL().parseDeps(DietfixServer.getParser().getDependencies(inputpt));
		HashMap<Experts, List<AnswerData>> sh = null;
		try {
			sh = DietfixServer.getKBase().process(cdata, xdeplist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String txt = DietfixServer.getTextGenerator().generateText(cdata, sh);
		App.log("");
		App.log("");
		App.log("");
		App.log("RESPONSE",txt);
		
		
		
	

		System.exit(0);
		dummy1(cdata);

		// testJWNL();
		// simpleNLG2();
		// testwnhelper();
		// testSimpleNLG();
	}


	private static void testNLG3(){
		
		String input = "Strawberry is sweet."	;
		if(!DietfixServer.isRunning()){
			DietfixServer.start();
		}
		List<TypedDependency> dtp = DietfixServer.getParser().getDependencies(DietfixServer.getParser().getParseTree(input));
		TypedDep[] alist = new TypedDep[dtp.size()];
		int i = 0;
		for(TypedDependency tdp : dtp){
			alist[i] = new TypedDep(tdp);
			App.log(alist[i]);
			i++;
		}
		
		StatefulKnowledgeSession ksession = DietfixServer.getKBase().getNLGKBase().newStatefulKnowledgeSession();
		ClientData cdata = new ClientData();
				PhraseProcessor asnt = new PhraseProcessor(cdata,
						alist, new HashMap<Integer,String>());
				
	
		
		//   TEXT AGGREGATION HERE
		
		ksession.insert(asnt);
		ksession.fireAllRules();
		for (Object pproc : ksession.getObjects())
			if (pproc instanceof PhraseProcessor) {
				PhraseProcessor ph = (PhraseProcessor) pproc;
				for (SPhraseSpec specsnt : ph.getSPhrases()) {
					String res = DietfixServer.getTextGenerator().getRealiser()
							.realiseSentence(specsnt);
					App.log(res);
				}
			}

	}

	private static void dummy1(ClientData cdata) {
		TestBasicRules tbr = new TestBasicRules();
		tbr.setup();
		tbr.testBasic();
		System.exit(0);
		tbr.setup();
		tbr.testBasic();
		System.exit(0);
		try {
			DBQuerier.start();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			Connection cd = MysqlHelper.createFoodDBConnection(cdata);
			Statement st = cd.createStatement();
			ResultSet rs = st
					.executeQuery("SHOW FULL TABLES IN `sr25food` WHERE TABLE_TYPE LIKE 'VIEW';");
			while (rs.next()) {
				App.log("p:", rs.getString("Tables_in_sr25food"));
			}
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.exit(0);
		try {

			Dietitian dt = new Dietitian(cdata);
			FoodQG f = new FoodQG(cdata);

			ResultQuery<Record> rq = f.generateQuery();
			String s = rq.getSQL(true);
			App.log("SQL", s);
			List<Food> fd = dt.queryFood(rq.fetch());

			for (Food fdd : fd) {
				App.log("FOOD:",
						fdd.getNutrients().size() + ":"
								+ fdd.getNutrient(Nutrients.LIPID) + ":"
								+ fdd.getID() + ":" + fdd.getDescription());
				if (fdd.getWeight1() != null) {
					App.log("WEIGHT1", fdd.getWeight1().getUnit() + ":"
							+ fdd.getWeight1().getValue());
				}
				if (fdd.getWeight2() != null) {
					App.log("WEIGHT2", fdd.getWeight2().getUnit() + ":"
							+ fdd.getWeight2().getValue());
				}
				App.log("CAT:",
						fdd.getFoodCategory().id + ":"
								+ fdd.getFoodCategory().description);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			DBQuerier.stop();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}







	

	public static void configureJWordNet() {
		// WARNING: This still does not work in Java 5!!!
		try {
			// initialize JWNL (this must be done before JWNL can be used)
			// See the JWordnet documentation for details on the properties file
			JWNL.initialize(new FileInputStream("resources/file_properties.xml"));
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}

	private static void testJWNL() throws JWNLException {
		configureJWordNet();
		Dictionary dictionary = Dictionary.getInstance();
		IndexWord word = dictionary.lookupIndexWord(POS.NOUN, "duck");
		System.out.println("Senses of the word 'duck':");
		Synset[] senses = word.getSenses();
		for (int i = 0; i < senses.length; i++) {
			Synset sense = senses[i];
			System.out.println((i + 1) + ". " + sense.getGloss());
			Pointer[] holo = sense.getPointers(PointerType.PART_HOLONYM);
			for (int j = 0; j < holo.length; j++) {
				Synset synset = (Synset) (holo[j].getTarget());
				net.didion.jwnl.data.Word synsetWord = synset.getWord(0);
				System.out.print("  -part-of-> " + synsetWord.getLemma());
				System.out.println(" = " + synset.getGloss());
			}
		}

		System.exit(1);
	}

	private static void simpleNLG2() {
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec p = nlgFactory.createClause();

		CoordinatedPhraseElement subjects = nlgFactory.createCoordinatedPhrase();
		CoordinatedPhraseElement verbs = nlgFactory.createCoordinatedPhrase();
		CoordinatedPhraseElement objects = nlgFactory.createCoordinatedPhrase();
		subjects.addCoordinate("He");
		verbs.addCoordinate("is");
		objects.addCoordinate("happy");

		p.setSubject(subjects);
		p.setVerb(verbs);
		p.setObject(objects);
		//p.setObject("everyday");
		//p.addComplement("for 30 seconds");
		
		//p.addPostModifier("fast");

		//verbs.setFeature(Feature.PERSON, Person.YOU);
		//p.setFeature(Feature.MODAL			,"should"  );
		// p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.HOW);
		// p.setFeature(Feature.INTERROGATIVE_TYPE, InterrogativeType.HOW_MANY);
		// p.setFeature(Feature.INTERROGATIVE_TYPE,
		// InterrogativeType.WHO_SUBJECT);
		// p.setFeature(Feature.INTERROGATIVE_TYPE,
		// InterrogativeType.WHO_SUBJECT);

		//Create an instance of Aggregator
		
		//now pass a list of NLGElements (usually sentences) to the aggregator, which runs the rules and returns the outcome
		
		App.log(realiser.realiseSentence(p));

		System.exit(1);
	}

	private static void test() {
		/*
		 * VPPhraseSpec verb = null;
		 * 
		 * CoordinatedPhraseElement subj = nlgFactory.createCoordinatedPhrase();
		 * CoordinatedPhraseElement obj = nlgFactory.createCoordinatedPhrase();
		 * ArrayList<PPPhraseSpec> ppreps = new ArrayList<PPPhraseSpec>(); for
		 * (Tree tree : parsetree.subTreeList()) { SFTag tag =
		 * SFTag.getEnum(tree.label().value()); if(tag == SFTag.S){ for (Tree
		 * tree2 : tree.getChildrenAsList()) { App.log(tag+":::"+tree2.label());
		 * SFTag childtag = SFTag.getEnum(tree2.value()); ElementCategory ec =
		 * childtag.toElementCategory();
		 * 
		 * App.log(tree2.yieldWords().toString());
		 * if(ec.equalTo(PhraseCategory.NOUN_PHRASE)){ for(Tree nptree:
		 * tree2.getChildrenAsList()){ ElementCategory npchildtag =
		 * SFTag.getEnum(nptree.value()).toElementCategory(); if(npchildtag ==
		 * LexicalCategory.NOUN||npchildtag == LexicalCategory.PRONOUN){
		 * App.log("SUbject is "+nptree.yieldWords().get(0));
		 * subj.addCoordinate(nptree.yieldWords().get(0)); } } } else
		 * if(ec.equalTo(PhraseCategory.VERB_PHRASE)){ String v =
		 * tree2.firstChild().yieldWords().get(0).toString()+" "; for(Tree
		 * nptree: tree2.getChildrenAsList()){ ElementCategory npchildtag =
		 * SFTag.getEnum(nptree.value()).toElementCategory(); if(npchildtag ==
		 * PhraseCategory.NOUN_PHRASE){
		 * App.log("SUbject is "+toStringWord(nptree));
		 * subj.addCoordinate(nptree.yieldWords().get(0)); } else if(npchildtag
		 * == PhraseCategory.VERB_PHRASE){ SFTag childtag2 =
		 * SFTag.getEnum(nptree.value()); ElementCategory ec2 =
		 * childtag2.toElementCategory(); if(ec.equals(LexicalCategory.NOUN)){
		 * obj.addCoordinate(toStringWord(nptree)); } else
		 * if(ec.equals(LexicalCategory.VERB)){ v+= toStringWord(nptree); } else
		 * if(ec.equals(LexicalCategory.PREPOSITION)){ PPPhraseSpec spp =
		 * nlgFactory.createPrepositionPhrase(toStringWord(nptree));
		 * ppreps.add(spp ); } App.log("SUbject is "+toStringWord(nptree)); verb
		 * = nlgFactory.createVerbPhrase(v); } else if(npchildtag ==
		 * PhraseCategory.PREPOSITIONAL_PHRASE){
		 * App.log("SUbject is "+toStringWord(nptree));
		 * subj.addCoordinate(nptree.yieldWords().get(0)); PPPhraseSpec spp =
		 * nlgFactory.createPrepositionPhrase(toStringWord(nptree));
		 * ppreps.add(spp ); } } } else
		 * if(ec.equalTo(PhraseCategory.PREPOSITIONAL_PHRASE)){
		 * 
		 * } else if(ec.equalTo(PhraseCategory.ADVERB_PHRASE)){
		 * 
		 * } else if(ec.equalTo(PhraseCategory.ADJECTIVE_PHRASE)){
		 * 
		 * }
		 * 
		 * } p.setSubject(subj); p.setVerb(verb); p.setObject(obj); for
		 * (PPPhraseSpec prs3 : ppreps) { p.addComplement(prs3); } } else{ //
		 * App.log(tree.label()+":"+tree.pennString()); }
		 * 
		 * } App.log("d"); App.log(realiser.realiseSentence(p));
		 */
	}

	private static String toStringWord(Tree t) {
		StringBuilder sb = new StringBuilder();

		for (HasWord tree : t.yieldHasWord()) {
			sb.append(tree.word()).append(" ");
		}

		return sb.toString();
	}

	private static void testwnhelper() {
		Connection con;

	}

	private static void testSimpleNLG() {
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);
		VPPhraseSpec v = nlgFactory.createVerbPhrase("catch");
		v.setFeature(Feature.TENSE, Tense.PAST);
		// App.log("", realiser.realise(v));

		SPhraseSpec s2 = nlgFactory.createClause("John", "sing", "a song");
		SPhraseSpec s3 = nlgFactory.createClause("John", "kick", "a song");
		// CoordinatedPhraseElement c = nlgFactory.createCoordinatedPhrase();
		// may revert to nlgFactory.createCoordinatedPhrase( ) ;
		Aggregator agr = new Aggregator();

		agr.initialise();
		agr.addRule(new ClauseCoordinationRule());
		List<NLGElement> enl = agr.realise(Arrays.asList(new NLGElement[] { s2,
				s3 }));
		for (NLGElement nlgElement : enl) {
			nlgElement.setFeature(Feature.TENSE, Tense.PAST);
			App.log("", realiser.realiseSentence(nlgElement));

		}
	}
}
