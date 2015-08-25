package clustering;

import java.util.ArrayList;

public class IncrementalGraph {
	private ArrayList<Pair <Integer , Integer> > al ;
	IncrementalGraph(){
		al = new ArrayList<>() ;
	}
	
	public ArrayList<Pair <Integer ,Integer>> getList(){
		return al ;
	}
	
	public void add(Pair <Integer ,Integer> p){
		al.add(p);
	}
	
	public void clear(){
		al.clear();
	}
	
	
}
