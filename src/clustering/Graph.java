package clustering;

import help.NotImplementedYetException;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph<T>{
	private HashMap<T , Integer> hashedId ;
	private HashMap<Integer , T> reverseHash ;
	private PriorityBasedEdgeManager edgeManager ;
	private IncrementalGraph graphToAdd ;
	
	Integer nextVertexId  ;
	
	public Graph(){
		hashedId = new HashMap<>() ;
		reverseHash = new HashMap<>() ;
		graphToAdd = new IncrementalGraph() ;
		edgeManager = new PriorityBasedEdgeManager();
		nextVertexId  = 1;
	}
	
	public Integer getLabel(T a){
		return hashedId.get(a);
	}
	
	public void checkVertice(T a){
		if(!hashedId.containsKey(a)){
			hashedId.put(a , nextVertexId );
			reverseHash.put(nextVertexId ++, a);
		}
	}
	
	public void setCurrentTimestamp(int t ){
		edgeManager.setCurrentTimestamp(t) ;
	}
	
	public void addEdge(T a  , T b, Double w){
		checkVertice(a);
		checkVertice(b);
		graphToAdd.add(new WeightedEdge(getLabel(a) , getLabel(b) ,w));
	}
	
	private void clear(){
		graphToAdd.clear(); 
	}
	
	public void evaluate(){
		edgeManager.evaluate(graphToAdd);
		clear() ;
	}

	public GraphRepresentation<T> getRepresentation(){
		return edgeManager.getRepresentation(reverseHash) ;
	}
	
}
