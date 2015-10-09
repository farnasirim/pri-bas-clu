package clustering;

import java.util.ArrayList;

public class GraphRepresentation <T>{
	private ArrayList<ClusterRepresentation<T> > clusters; 
	public GraphRepresentation() {
		clusters = new ArrayList<>();
	}
	public void add(ClusterRepresentation<T> cl){
		clusters.add(cl);
	}
	
	public ArrayList<ClusterRepresentation<T>> getClusters(){
		return clusters; 
	}
}
