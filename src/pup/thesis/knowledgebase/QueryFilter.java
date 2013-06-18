package pup.thesis.knowledgebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pup.thesis.knowledgebase.expert.Expert;
import pup.thesis.knowledgebase.expert.Experts;
import pup.thesis.logging.App;
import pup.thesis.logging.Modules;
import pup.thesis.nlu.pos.TypedDep;

public class QueryFilter {

	public HashMap<Experts,List<String>> filter(List<TypedDep> tdepset) throws Exception{
		HashMap<Experts,List<String>> hm = new HashMap<Experts,List<String>>();
		int size = 0;
		ArrayList<String> dietcounter = new ArrayList<String>(),fitcounter = new ArrayList<String>(),nutcounter = new ArrayList<String>();
		for(TypedDep tdep: tdepset){
			if(tdep.getActions()!=null)
			for(String exp : tdep.getActions()){
				size++;
				String[] spl = exp.split(":");
				for(char c : spl[1].toCharArray()){
					switch(c){
					case '1':
						dietcounter.add(spl[0]);
						break;
					case '2':
						fitcounter.add(spl[0]);
						break;
					case '3':
						nutcounter.add(spl[0]);
						break;
					default:
						throw new Exception("Invalid expert type.");
					}
				}
				}
			}
		double dx = ((float)(dietcounter.size())/size)*100;
		double fx = ((float)(fitcounter.size())/size)*100;
		double nx = ((float)(nutcounter.size())/size)*100;
		App.log(Modules.INFORMATIONI_RETRIEVAL,"QueryFilter:",String.format("Data:%s [Dietitian:%s%%][FitnessInstructor:%s%%][Nutritionist:%s%%]",size, dx,fx,nx));
		hm.put(Experts.DIETITIAN, dietcounter);
		hm.put(Experts.FITNESS_INSTRUCTOR, nutcounter);
		hm.put(Experts.NUTRITIONIST, nutcounter);
		return hm;
	}
}
