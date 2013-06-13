package pup.thesis.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.stanford.nlp.trees.TypedDependency;

import pup.thesis.nlu.pos.TypedDep;

public class DFUtils {

	
	public static List<TypedDep> toTdepList(Collection<TypedDependency> tdep){
		ArrayList<TypedDep> tdeplist = new ArrayList<TypedDep>();
		for(TypedDependency tdepcy : tdep){
			tdeplist.add(new TypedDep(tdepcy));
		}		
		return tdeplist;
	}
	public static TypedDep[] toTdepArray(List<TypedDependency> tdep){
		TypedDep[] tdeplist = new TypedDep[tdep.size()];
		for(int i = 0;i<tdeplist.length;i++){
			tdeplist[i] = new TypedDep(tdep.get(i));
		}		
		return tdeplist;
	}
}
