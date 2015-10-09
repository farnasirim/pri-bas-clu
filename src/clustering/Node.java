package clustering;

public class Node {
	private Integer nodeId ;
	private Cluster associatedCluster;
	private Cluster originalCluster;
	
	public Node(Integer nodeId, Cluster orig){
		this.nodeId = nodeId ;
		originalCluster = associatedCluster = orig ;
//		associatedCluster.addNode(this);
	}
	
	public int getNodeId(){
		return nodeId ;
	}
	
	public Cluster getAssociatedCluster(){
		return associatedCluster;
	}
	
	public Cluster getOriginalCluster(){
		return originalCluster;
	}
	
	public void reset(){
		associatedCluster=  originalCluster; 
	}
	
	public void setAssociatedCluster(Cluster cl){
		associatedCluster = cl ;
	}
	
	public void registerInCluster(){
		associatedCluster.addNode(this);
	}
	
}
