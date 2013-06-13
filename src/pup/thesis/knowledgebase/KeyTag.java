package pup.thesis.knowledgebase;

import antlr.StringUtils;

public class KeyTag {

	public final int depth;
	public final String tag;
	/*
	 * A keyword tag
	 * 
	 * 
	 */
	public KeyTag(String text) throws Exception{
		String txt = StringUtils.stripFrontBack(text,"(",")");
		String[] spl = txt.split(":");
		if(spl.length==2){
			this.depth = Integer.parseInt(spl[0]);
			this.tag = StringUtils.stripFrontBack(spl[1], "[", "]");
		}
		else{
			throw new Exception("Error parsing keytagtext:"+text);
		}
	}
	public KeyTag(int depth,String tag){
		this.depth = depth;
		this.tag = tag;
	}
	
	public String toString(){
		return String.format("(%s:[%s])", depth,tag);
	}
}
