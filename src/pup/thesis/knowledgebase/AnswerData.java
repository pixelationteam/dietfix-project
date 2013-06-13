package pup.thesis.knowledgebase;

import java.io.Serializable;
import java.util.HashMap;

import pup.thesis.nlu.pos.TypedDep;

public class AnswerData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final TypedDep[] dependencies;
	public final HashMap<Integer,String> mappings;
	
	public AnswerData(TypedDep[] deps,HashMap<Integer,String> maps){
		this.dependencies = deps;
		this.mappings = maps;
	}
	 
}
