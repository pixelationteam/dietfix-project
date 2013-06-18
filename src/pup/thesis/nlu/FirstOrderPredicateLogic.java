package pup.thesis.nlu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.drools.runtime.StatefulKnowledgeSession;

import edu.stanford.nlp.process.Morphology;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TypedDependency;

import pup.thesis.logging.App;
import pup.thesis.logging.Modules;
import pup.thesis.nlu.pos.TypedDep;
import pup.thesis.nlu.pos.Word;
import pup.thesis.server.DietfixServer;

public class FirstOrderPredicateLogic {

	public List<TypedDep> parseDeps(List<TypedDependency> tdeps) {
		if (!DietfixServer.isRunning()) {
			App.log(Modules.TEXT_UNDERSTANDING,"FOPL:", "Server is not running");
			return null;
		}
		App.log(Modules.TEXT_UNDERSTANDING,"");
		App.log(Modules.TEXT_UNDERSTANDING,"");
		App.log(Modules.TEXT_UNDERSTANDING,"");
		App.log(Modules.TEXT_UNDERSTANDING,"<<<===================== FIRST ORDER LOGIC =========================>>>");
		StatefulKnowledgeSession ksession = DietfixServer.getKBase()
				.getFOLKBase().newStatefulKnowledgeSession();
		ArrayList<TypedDep> retdeps = new ArrayList<TypedDep>();
		Morphology morp = new Morphology();
		for (TypedDependency tddd : tdeps) {
			// TypedDep stdep = new TypedDep(tddd.reln().toString(),new
			// Word(tddd.dep().index(),tddd.dep().label().tag(),tddd.dep().value()),new
			// Word(tddd.gov().index(),tddd.gov().label().tag(),tddd.gov().value()));
			String reln = tddd.reln().toString();
			Word dep = new Word(tddd.dep().index(),tddd.dep().label().tag(),morp.lemma(tddd.dep().value(), tddd.dep().label().tag()));
			Word gov = new Word(tddd.gov().index(),tddd.gov().label().tag(),morp.lemma(tddd.gov().value(), tddd.gov().label().tag()));
			TypedDep stdep = new TypedDep(reln,gov,dep);
			retdeps.add(stdep);
			ksession.insert(stdep);
			// App.log(tddd+":"+stdep.toString());

		}
		ksession.fireAllRules();
		
		App.log(Modules.TEXT_UNDERSTANDING,"Total Dependencies",retdeps.size());
		App.log(Modules.TEXT_UNDERSTANDING,"");
		for(TypedDep xdep: retdeps){
			if(xdep.getActions()!=null){
				App.log(Modules.TEXT_UNDERSTANDING,xdep.toString(),Arrays.toString(xdep.getActions()));
			}
			else{
				App.log(Modules.TEXT_UNDERSTANDING,xdep.toString(),"No Action");
			}
		}
		return retdeps;
	}
}
