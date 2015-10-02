package clustering;

import java.util.HashMap;

public class GraphClusteringManager {
	private PriorityBasedEdgeManager edgeManager;
	private HashMap<Integer, Node> nodeMap;
	
	public GraphClusteringManager(PriorityBasedEdgeManager em) {
		edgeManager = em;
		nodeMap = new HashMap<>();
	}
	
	private void moveEdgeToReserve(WeightedEdge e){
		edgeManager.moveEdgeToReserve(e);
	}
	
	private Cluster getAssociatedCluster(WeightedEdge e){
		return getNode(e.first).getAssociatedCluster();
	}
	
	private void merge(Node a, Node b){
		b.getAssociatedCluster().mergeInto(a.getAssociatedCluster());
		if(a.getAssociatedCluster().isOversized()){
			WeightedEdge lowest = a.getAssociatedCluster().getLowestEdge().getClone() ;
			moveEdgeToReserve(lowest);
			a.getAssociatedCluster().deleteEdge(lowest);
			a.getAssociatedCluster().reInsert(this) ;
		}
	}

	public void updateEdgeInCluster(WeightedEdge old, WeightedEdge neww) {
		getAssociatedCluster(old).updateEdge(old , neww);
	}

	public void deleteEdge(WeightedEdge e) {
		Cluster location = getAssociatedCluster(e);
		location.deleteEdge(e);
		location.reInsert(this);
	}

	public void addEdge(WeightedEdge e) {
		Node a = getNode(e.first);
		Node b = getNode(e.second);
		
		if(a.getAssociatedCluster() != b.getAssociatedCluster()){
			merge(a , b);
		}
		a.getAssociatedCluster().addEdge(e);
	}

	public void checkNode(Integer v) {
		if (!nodeMap.containsKey(v)) {
			nodeMap.put(v, new Node(v, new Cluster(
					help.Constants.Parameters.CLUSTER_SIZE)));
		}
	}

	public Node getNode(Integer v) {
		checkNode(v);
		return nodeMap.get(v);
	}

}
