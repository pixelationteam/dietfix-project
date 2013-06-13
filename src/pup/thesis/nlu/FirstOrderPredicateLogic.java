package pup.thesis.nlu;

import java.util.ArrayList;
import java.util.List;

import org.drools.runtime.StatefulKnowledgeSession;

import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TypedDependency;

import pup.thesis.logging.App;
import pup.thesis.nlu.pos.TypedDep;
import pup.thesis.nlu.pos.Word;
import pup.thesis.server.DietfixServer;

public class FirstOrderPredicateLogic {

	
	public List<TypedDep> parseDeps(List<TypedDependency> tdeps ){
		if(!DietfixServer.isRunning()){
			App.log("FOPL:","Server is not running");
			return null;
		}
		StatefulKnowledgeSession ksession = DietfixServer.getKBase().getFOLKBase().newStatefulKnowledgeSession();
		ArrayList<TypedDep> retdeps = new ArrayList<TypedDep>();
			for(TypedDependency tddd:tdeps){
				//TypedDep stdep = new TypedDep(tddd.reln().toString(),new Word(tddd.dep().index(),tddd.dep().label().tag(),tddd.dep().value()),new Word(tddd.gov().index(),tddd.gov().label().tag(),tddd.gov().value()));
				TypedDep stdep = new TypedDep(tddd);
				retdeps.add(stdep);
				ksession.insert(stdep);
				App.log(tddd+":"+stdep.toString());
				
			}
			App.log("FOL::");
			ksession.fireAllRules();
			
			/*for(Object o:ksession.getObjects){
				if(o instanceof TypedDep){
					TypedDep td = ((TypedDep)o);
					String[] acts = td.getActions();
					if(acts!=null)
					for(String act: acts){
					App.log(act);
					}
					if(td.getExperts().size()>0)
					App.log(td.getExperts());
				}
			}*/
			return retdeps;
	}
}
