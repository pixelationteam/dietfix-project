package pup.thesis.nlu;

import java.util.ArrayList;

import net.didion.jwnl.data.POS;

public class RelatedWord {
	
	private int rank = 0;
	private String label = "";
	private POS tag;
	private String action = "";
	private String Id = "";
	
	public boolean equals(RelatedWord word) {
		return (word.getLabel().equals(label) && word.getTag().equals(tag)) ? true : false;
	}
	
	public void setId(String Id) {
		this.Id = Id;
	}
	
	public String getId() {
		return Id;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public void setTag(POS tag) {
		this.tag = tag;
	}
	
	public POS getTag() {
		return tag;
	}
	
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
}
