package clustering;

import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.HashSet;

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
		a.getAssociatedCluster().addEdge(e);
//		if(e.first == 5 && e.second == 6){
//			System.out.println("For " + e + " merging " + a.getNodeId() + " " + b.getNodeId());
//		}
		if(a.getAssociatedCluster() != b.getAssociatedCluster()){
			merge(a , b);
		}
	}

	public void checkNode(Integer v) {
		if (!nodeMap.containsKey(v)) {
			nodeMap.put(v, new Node(v, new Cluster(
					help.Constants.Parameters.CLUSTER_SIZE)));
			nodeMap.get(v).registerInCluster();
		}
	}

	public Node getNode(Integer v) {
		checkNode(v);
		return nodeMap.get(v);
	}
	
	public <T> GraphRepresentation<T> getRepresentation(HashMap<Integer , T> map){
		GraphRepresentation<T> ret = new GraphRepresentation<>();
		HashSet<Integer> done  = new HashSet<>();
		for(Integer num : nodeMap.keySet()){
			if(!done.contains(num)){
				done.add(num);
				ret.add(nodeMap.get(num).getAssociatedCluster().getRepresentation(map , done));
			}
		}
		return ret ;
	}
	
}
