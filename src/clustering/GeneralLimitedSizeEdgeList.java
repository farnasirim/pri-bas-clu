package clustering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class GeneralLimitedSizeEdgeList {

	private TreeSet<WeightedEdge> sortedSetOfEdges ;
	private HashMap< Integer , HashMap <Integer , WeightedEdge > > pairToEdgeMap ; 
	private int maxSize;
	
	public ArrayList<WeightedEdge> getEdges(){
		ArrayList<WeightedEdge> ret = new ArrayList<>();
		for(WeightedEdge e : sortedSetOfEdges){
			ret.add(e);
		}
		return ret ;
	}

	public GeneralLimitedSizeEdgeList(int sz) {
		maxSize = sz;
		pairToEdgeMap = new HashMap<>() ;
		sortedSetOfEdges = new TreeSet<WeightedEdge>(new WeightedEdgeWeightComparator()) ;
	}

	public WeightedEdge findEdge(WeightedEdge e){
		if(!hasEdge(e)){
			return null ;
		}
		return pairToEdgeMap.get(e.first).get(e.second);
	}

	public boolean hasEdge(WeightedEdge e){
		if(pairToEdgeMap.containsKey(e.first)){
			return pairToEdgeMap.get(e.first).containsKey(e.second);
		}
		return false; 
	}
	
	public void putEdge(WeightedEdge e){
		if(!pairToEdgeMap.containsKey(e.first)){
			pairToEdgeMap.put(e.first, new HashMap<>());
		}
		pairToEdgeMap.get(e.first).put(e.second, e);
//		System.out.println("putting " + e  + "in ");
		sortedSetOfEdges.add(e);
	}

	public void deleteEdge(WeightedEdge e){
		sortedSetOfEdges.remove(pairToEdgeMap.get(e.first).get(e.second));
		pairToEdgeMap.get(e.first).remove(e.second);
	}

	public int size(){
		return sortedSetOfEdges.size();
	}
	
	public void replaceEdge(WeightedEdge old, WeightedEdge neww){
		pairToEdgeMap.get(old.first).replace(old.second, neww);
		sortedSetOfEdges.remove(old);
		sortedSetOfEdges.add(neww);
	}
	
	public WeightedEdge getLowestWeight(){
		return sortedSetOfEdges.first();
	}
	
	public int getMaxSize(){
		return maxSize ;
	}
	
	public boolean sizeConstraintViolated(){
		return size() > getMaxSize();
	}
	
	
}
