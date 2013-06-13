package pup.thesis.nlu.pos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.trees.TypedDependency;

import pup.thesis.knowledgebase.expert.Experts;
import pup.thesis.logging.App;

public class TypedDep implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8849594065776734733L;
	private final String reln;
	private final Word dep,gov;
	private String[] actions;
	private String exp;
	
	public TypedDep(TypedDependency tdep){
		this.reln = tdep.reln().toString();
		this.dep = new Word(tdep.dep().index(),tdep.dep().label().tag(),tdep.dep().value());
		this.gov = new Word(tdep.gov().index(),tdep.gov().label().tag(),tdep.gov().value());
	}
	public TypedDep(String reln,Word gov,Word dep){
		this.reln = reln;
		this.dep  = dep;
		this.gov = gov;
	}
	
	public String reln(){
		return reln;
	}
	
	public Word dep(){
		return dep;
	}
	
	public Word gov(){
		return gov;
	}
	
	public void setActions(String...keywords){
		actions = keywords;
	}
	
	public String[] getActions(){
		return actions;
	}
	
	public void setExpert(String exp){
		this.exp = exp;
	}
	public List<Experts> getExperts(){
		ArrayList<Experts> exps = new ArrayList<Experts>();
		if(exp!=null)
		for(char c:exp.toCharArray()){
			Experts exp = Experts.isEnum(c);
			if(exp !=null&& !exps.contains(exp)){
				exps.add(exp);
			}
		}
		return exps;
	}
	
	public String toString(){
		return reln+"("+gov.getLemma()+"-"+gov.getIndex()+","+dep.getLemma()+"-"+dep.getIndex()+")";
	}
}
