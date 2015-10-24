package clustering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class Cluster {
	private int maxClusterSize;
	
	private TreeSet<WeightedEdge> sortedEdgeList;
	private HashMap<Integer, Node> nodesInCluster ;
	
	public Cluster(int sz) {
		maxClusterSize = sz; 
		sortedEdgeList = new TreeSet<>(new WeightedEdgeWeightComparator());
		nodesInCluster = new HashMap<>();
	}
	
	public void addEdge(WeightedEdge e){
//		System.out.println(e);
//		System.out.println("size bef : " + sortedEdgeList.size());
		sortedEdgeList.add(e);
//		System.out.println("size aft : " + sortedEdgeList.size());
	}
	
	public WeightedEdge getLowestEdge(){
		return sortedEdgeList.first();
	}
	
	public void updateEdge(WeightedEdge old, WeightedEdge neww){
		sortedEdgeList.remove(old);
		sortedEdgeList.add(neww);
	}
	
	public void deleteEdge(WeightedEdge e){
		sortedEdgeList.remove(e);
	}
	
	public void reInsert(GraphClusteringManager graphManager){
		ArrayList<WeightedEdge> edges = new ArrayList<>();
		for(Object ob : sortedEdgeList.toArray()){
			WeightedEdge e = (WeightedEdge)ob ;
			edges.add(e);
		}
		ArrayList<Node> nodes = new ArrayList<>();
		for(Integer v : nodesInCluster.keySet()){
			nodes.add(nodesInCluster.get(v));
		}
		reset() ;
		for(Node node : nodes){
			node.reset();
			node.getAssociatedCluster().addNode(node);
		}
		for(WeightedEdge e : edges){
			graphManager.addEdge(e);
		}
	}
	
	public void mergeInto(Cluster other){
		for(WeightedEdge e : sortedEdgeList){
			other.addEdge(e);
		}
		for(Integer node : nodesInCluster.keySet()){
			other.addNode(nodesInCluster.get(node));
			nodesInCluster.get(node).setAssociatedCluster(other);
		}
		reset() ;
	}
	
	public void addNode(Node node){
		nodesInCluster.put(node.getNodeId(), node);
	}
	
	public int size(){
		return nodesInCluster.size();
	}
	
	public boolean isOversized(){
		return size() > maxClusterSize ; 
	}

	public void reset(){
		sortedEdgeList.clear();
		nodesInCluster.clear();
	}
	
	public <T> ClusterRepresentation<T> getRepresentation(HashMap <Integer , T> map , HashSet<Integer> done){
		ClusterRepresentation<T> ret= new ClusterRepresentation<>();
		for(Integer nd : nodesInCluster.keySet()){
			ret.addNode(map.get(nd));
			done.add(nd);
		}
		ArrayList<EdgeRepresentation<T>> tmp = new ArrayList<>() ;
		for(WeightedEdge e : sortedEdgeList){
			tmp.add(new EdgeRepresentation<T>(map.get(e.first), map.get(e.second), e.weight, e.meanOfInteractions));
		}
		for(int i = tmp.size() - 1 ; i>= 0 ; i --){
			ret.addEdge(tmp.get(i));
		}
		return ret ;
	}
	
}

