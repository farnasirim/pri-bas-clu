package clustering;

import java.util.ArrayList;

public class GraphRepresentation <T>{
	private ArrayList<ClusterRepresentation<T> > clusters; 
	
	private ArrayList<EdgeRepresentation<T>> mainList , reserveList;
	
	public void addToMainList(EdgeRepresentation<T> e){
		mainList.add(e);
	}
	public void addToReserveList(EdgeRepresentation<T> e){
		reserveList.add(e);
	}
	
	public ArrayList<EdgeRepresentation<T>> getMainList(){
		return mainList ;
	}
	
	public ArrayList<EdgeRepresentation<T>> getReserveList(){
		return reserveList ;
	}
	
	public GraphRepresentation() {
		mainList = new ArrayList<>();
		reserveList = new ArrayList<>();
		clusters = new ArrayList<>();
	}
	public void add(ClusterRepresentation<T> cl){
		clusters.add(cl);
	}
	
	public ArrayList<ClusterRepresentation<T>> getClusters(){
		return clusters; 
	}
}
