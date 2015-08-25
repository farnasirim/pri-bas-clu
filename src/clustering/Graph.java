package clustering;

import help.NotImplementedYetException;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph<T>{
	private HashMap<T , Integer> hashedId ;
	private HashMap<Integer , T> reverseHash ;
		
	private IncrementalGraph graphToAdd ;
	
	Integer nextVertexId  ;
	
	Graph(){
		hashedId = new HashMap<>() ;
		reverseHash = new HashMap<>() ;
		graphToAdd = new IncrementalGraph() ;
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
	
	public void addEdge(T a  , T b){
		checkVertice(a);
		checkVertice(b);
		graphToAdd.add(new Pair <Integer , Integer> (getLabel(a) , getLabel(b)));
	}
	
	private void clear(){
		graphToAdd.clear(); 
	}
	
	public void evaluate() throws NotImplementedYetException{
		clear() ;
		throw new NotImplementedYetException() ;
	}
	
}
