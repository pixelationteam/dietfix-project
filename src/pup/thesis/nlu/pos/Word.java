package pup.thesis.nlu.pos;

import java.io.Serializable;

public class Word implements Pos,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String tag;
	private final String lemma;
	private final int index;
	
	public Word(int ind,String tag,String lemma){
		this.index = ind;
		this.tag = tag;
		this.lemma = lemma;
	}
	
	
	public int getIndex(){
		return index;
	}
	public String getTag(){
		return tag;
	}
	public String getLemma(){
		return lemma;
	}
	
	public String toString(){
		return String.format("[%s,%s,\"%s\"]", index,tag,lemma);
	}
}
