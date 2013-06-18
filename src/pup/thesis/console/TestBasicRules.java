package pup.thesis.console;

import java.util.Arrays;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.command.CommandFactory;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import edu.stanford.nlp.ling.CoreAnnotations.IndexAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Label;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.Dependency;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;

import pup.thesis.logging.App;
import pup.thesis.logging.Modules;
import pup.thesis.nlu.CoreParser;
import pup.thesis.nlu.pos.TypedDep;
import pup.thesis.nlu.pos.Word;

public class TestBasicRules {
	 
	 private KnowledgeBase kbase;
	  
	 public void setup() {
	  KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
	  kbuilder.add(ResourceFactory.newClassPathResource("FOL.drl"), ResourceType.DRL);
	  KnowledgeBuilderErrors errors = kbuilder.getErrors();
	  if (errors.size() > 0) {
	   for (KnowledgeBuilderError error: errors) {
	    System.err.println(error);
	   }
	   throw new IllegalArgumentException("Could not parse knowledge.");
	  }
	  kbase = KnowledgeBaseFactory.newKnowledgeBase();
	  kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
	      
	   
	 }
	  
	 public void testBasic() {
	 
	  StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
	  /*Applicant applicant = new Applicant( "Mr John Smith", 20);

	  Application application = new Application();

	  App.log( applicant.isValid() );

	   ksession.insert(applicant);
	  ksession.insert(application);
	 ksession.fireAllRules();
	  for (Object o: ksession.getObjects()) {
		     if(o instanceof Applicant) {
		      App.log("Done.", ((Applicant) o).isValid());
		     }
		    }
	  

	  App.log( applicant.isValid() );;
	   */
	  CoreParser parser = new CoreParser();
		Tree pt =  parser.getParseTree("I am fast");
		
		List<TypedDependency> tdl = parser.getDependencies(pt);
		for(TypedDependency tddd:tdl){
			App.log(Modules.SYSTEM,tddd);
			/*App.log(tddd.dep().value());
			App.log(tddd.gov().value());
			App.log(tddd.reln());*/
			ksession.insert(new TypedDep(tddd.reln().toString(),new Word(tddd.gov().index(),tddd.gov().label().tag(),tddd.gov().value()),new Word(tddd.dep().index(),tddd.dep().label().tag(),tddd.dep().value())));

		}
		App.log(Modules.SYSTEM,"FOL::");
		ksession.fireAllRules();
		
		for(Object o:ksession.getObjects()){
			if(o instanceof TypedDep){
				TypedDep td = ((TypedDep)o);
				String[] acts = td.getActions();
				if(acts!=null)
				for(String act: acts){
				App.log(Modules.SYSTEM,act);
				}
				if(td.getExperts().size()>0)
				App.log(Modules.SYSTEM,td.getExperts());
			}
		}
	 }
	 
	 public void parseTree(){

			CoreParser parser = new CoreParser();
			Tree pt =  parser.getParseTree("I am fast");
			
			List<TypedDependency> tdl = parser.getDependencies(pt);
			for(TypedDependency tddd:tdl){
				App.log(Modules.SYSTEM,tddd);
				App.log(Modules.SYSTEM,tddd.dep().value());
				App.log(Modules.SYSTEM,tddd.gov().value());
				App.log(Modules.SYSTEM,tddd.reln());

			}
			
	 }
	 
	}
