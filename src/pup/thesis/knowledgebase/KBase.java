package pup.thesis.knowledgebase;

import java.util.HashMap;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;

import pup.thesis.logging.App;
import pup.thesis.nlu.pos.Word;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.trees.Tree;

public class KBase {

	private final KnowledgeBase FOLKbase;
	private final KnowledgeBase NLGKbase;
	
	public KBase(){
		FOLKbase = createKBase("FOL.drl");
		NLGKbase = createKBase("NLG.drl");
	}
	
	public KnowledgeBase getFOLKBase(){
		return FOLKbase;
	}
	public KnowledgeBase getNLGKBase(){
		return NLGKbase;
	}
	
	private KnowledgeBase createKBase(String drl) {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource(drl),
				ResourceType.DRL);
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBase kb = KnowledgeBaseFactory.newKnowledgeBase();
		kb.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kb;
	}
	
	public static AnswerData createAnswerData(Tree parsetree,HashMap<Integer,String> map){
		List<CoreLabel> wlabel = parsetree.taggedLabeledYield();
		for(CoreLabel lbl:wlabel){
			if(map.containsKey(lbl.index())){
				App.log(lbl.tag());
			}
		}
		return null;
	}
}
