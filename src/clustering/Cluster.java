package clustering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class Cluster {
	private int maxClusterSize;
	
	private TreeSet<WeightedEdge> sortedEdgeList;
	private HashMap<Integer, Node> nodesInCluster ;
	
	public Cluster(int sz) {
		maxClusterSize = sz; 
		sortedEdgeList = new TreeSet<>(new WeightedEdgeWeightComparator());
	}
	
	public void addEdge(WeightedEdge e){
		sortedEdgeList.add(e);
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
		return sortedEdgeList.size();
	}
	
	public boolean isOversized(){
		return size() > maxClusterSize ; 
	}

	public void reset(){
		sortedEdgeList.clear();
		nodesInCluster.clear();
	}
	
}

