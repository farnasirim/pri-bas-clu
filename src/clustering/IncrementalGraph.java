package clustering;

import java.util.ArrayList;

public class IncrementalGraph {
	
	private int timeStamp;
	
	public void setTimeStamp(int val){
		timeStamp = val ;
	}
	
	public int getTimeStamp(){
		return timeStamp ;
	}
	
	private ArrayList<WeightedEdge> al ;
	IncrementalGraph(){
		al = new ArrayList<>() ;
		clear();
	}
	
	public ArrayList<WeightedEdge> getList(){
		return al ;
	}
	
	public void add(WeightedEdge e){
		al.add(e);
	}
	
	public void clear(){
		setTimeStamp(-1);
		al.clear();
	}
	
	
}
