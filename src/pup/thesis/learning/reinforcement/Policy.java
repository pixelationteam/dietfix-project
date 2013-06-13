package pup.thesis.learning.reinforcement;

import pup.thesis.nlu.RelatedWord;

public class Policy {
	
	private RelatedWord initState;
	private RelatedWord endState;
	private int action;
	private int reward;
	
	
	
	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public RelatedWord getInitState() {
		return initState;
	}
	
	public void setInitState(RelatedWord initState) {
		this.initState = initState;
	}
	
	public RelatedWord getEndState() {
		return endState;
	}
	
	public void setEndState(RelatedWord endState) {
		this.endState = endState;
	}
	
	public int getAction() {
		return action;
	}
	
	public void setAction(int action) {
		this.action = action;
	}	
	
}
