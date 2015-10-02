package clustering;

import java.util.ArrayList;

public class IncrementalGraph {
	private ArrayList<WeightedEdge> al ;
	IncrementalGraph(){
		al = new ArrayList<>() ;
	}
	
	public ArrayList<WeightedEdge> getList(){
		return al ;
	}
	
	public void add(WeightedEdge e){
		al.add(e);
	}
	
	public void clear(){
		al.clear();
	}
	
	
}
