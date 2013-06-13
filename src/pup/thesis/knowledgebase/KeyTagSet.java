package pup.thesis.knowledgebase;

import java.util.ArrayList;
import java.util.List;


public class KeyTagSet {

	ArrayList<KeyTag> keytags = new ArrayList<KeyTag>();
	public KeyTagSet(){
		
	}
	public KeyTagSet(String desc) throws Exception{
		String[] keytagtxt = desc.trim().split(",");
		for(String txt:keytagtxt){
			keytags.add(new KeyTag(txt));
		}
	}
	
	public void addKeyTag(KeyTag ktag){
		keytags.add(ktag);
	}
	
	public List<KeyTag> getKeyTags(){
		return keytags;
	}
	
	@Override
	public String toString(){
		StringBuilder sbuilder = new StringBuilder();
		for(int i  = keytags.size()-1;i>=0;i--){
			sbuilder.append(keytags.get(i).toString());
			if(i>0){
				sbuilder.append(',');
			}
		}
		return sbuilder.toString();
	}
}
