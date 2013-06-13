package pup.thesis.knowledgebase.expert;

public enum Experts {
	DIETITIAN('1'),FITNESS_INSTRUCTOR('2'),NUTRITIONIST('3');
	
	private final char ch;
	private Experts(char c){
		ch = c;
	}
	public char getCharValue(){
		return ch;
	}
	
	public static Experts isEnum(char c){
		for(Experts exp:Experts.values()){
			if(c == exp.getCharValue()){
				return exp;
			}
		}
		return null;
	}

}
