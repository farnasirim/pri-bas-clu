package clustering;

import java.util.ArrayList;

public class ClusterRepresentation<T> {
	private ArrayList<T> nodes;
	private ArrayList<EdgeRepresentation<T>> edges ;
	
	public ClusterRepresentation() {
		nodes= new ArrayList<>() ;
		edges = new ArrayList<>() ;
	}
	
	public void addNode(T n){
		nodes.add(n);
	}
	
	public void addEdge(EdgeRepresentation<T> e){
		edges.add(e);
	}
	
	public ArrayList<T> getNodes(){
		return nodes ;
	}
	
	public ArrayList<EdgeRepresentation<T>> getEdges(){
		return edges ;
	}
}
