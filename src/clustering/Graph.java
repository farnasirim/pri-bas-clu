package clustering;

import help.NotImplementedYetException;
import io.GenericDataStash;
import io.GenericEdge;
import io.NormalInput;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph<T>{
	private HashMap<T , Integer> hashedId ;
	private HashMap<Integer , T> reverseHash ;
	private PriorityBasedEdgeManager edgeManager ;
	private IncrementalGraph graphToAdd ;
	private NormalInput inp ;
	Integer nextVertexId  ;
	
	public void setInputter(NormalInput input){
		inp = input ;
	}
	
	public boolean hasNextInput(){
		return inp.hasNext();
	}
	
	public void readAndEvaluateInput(){
		GenericDataStash<T> data = inp.getData();
		graphToAdd.clear();
//		System.out.println("query : ");		
		for(GenericEdge<T> e : data.al){
//			System.out.println(e.first + " " + e.second + " " + e.weight);
			addEdge(e.first, e.second, e.weight);
		}
//		System.out.println(data.timeStamp);
//		System.out.println("\n\n\n");
		setCurrentTimestamp(data.timeStamp);
		evaluate();
	}
	
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
		graphToAdd.setTimeStamp(t);
	}
	
	public void addEdge(T a  , T b, Double w){
		checkVertice(a);
		checkVertice(b);
		graphToAdd.add(new WeightedEdge(getLabel(a) , getLabel(b) ,w));
	}
	
	private void clear(){
		graphToAdd.clear(); 
	}
	
	private int evalcnt = 1 ;
	
	public void evaluate(){
		edgeManager.evaluate(graphToAdd);
		clear() ;
		if(evalcnt % 50 == 0)
			System.out.println(evalcnt + " evals happened");
		evalcnt++;

	}

	public GraphRepresentation<T> getRepresentation(){
		return edgeManager.getRepresentation(reverseHash) ;
	}

	public HashMap<Integer, T> getInverseMap() {
		return reverseHash;
	}
	
}
