package pup.thesis.learning.reinforcement;

public class Reward {
	
	/**
	 * 
	 * 
	 * @param depth
	 * @return
	 */
	public int getReward(int depth) {
		
		int reward = getCriteriaEquivalent(depth);
		
		return reward;
	}
	
	/**
	 * 
	 * 
	 * @param depth
	 * @return
	 */
	private int getCriteriaEquivalent(int depth) {
		
		//criteria for the depth
		//gives the equivalent reward based on the depth
		switch(depth) {
		
			case 0:
				return 0;
				
			case 1:
				return 1;
				
			case 2:
				return 2;
				
			case 3:
				return 3;
				
			case 4:
				return 4;
				
			case 5:
				return 5;
				
			case 6:
				return 6;
				
			case 7:
				return 7;
				
			case 8:
				return 8;
				
			case 9:
				return 9;
				
			case 10:
				return 10;
				
			default:
				return 11;
		}
		
	}
	
	
	
}
