package clustering;

import help.Constants;

import java.util.HashMap;

public class PriorityBasedEdgeManager {
	private GeneralLimitedSizeEdgeList mainList ;
	private GeneralLimitedSizeEdgeList reserveList ;
	
	private GraphClusteringManager graphManager; 
	
	private int currentTime ;

	public PriorityBasedEdgeManager() {
		currentTime = 1	 ;
		mainList = new GeneralLimitedSizeEdgeList(help.Constants.Parameters.MAIN_PRIORITY_LIST_SIZE);
		reserveList = new GeneralLimitedSizeEdgeList(help.Constants.Parameters.RESERVE_PRIORITY_LIST_SIZE);
		graphManager = new GraphClusteringManager(this);
	}
	
	//-------------------------------------
	//insert node into cluster
	private void checkNode(Integer v){
		graphManager.checkNode(v);
	}	
	private Node getNode(Integer v){
		return graphManager.getNode(v);
	}
	
	private void updateEdgeInCluster(WeightedEdge old , WeightedEdge neww){
		graphManager.updateEdgeInCluster(old , neww);
	}
	
	private void edgeArrival(WeightedEdge e){
		graphManager.addEdge(e);
	}

	private void delete(WeightedEdge e){
		graphManager.deleteEdge(e);
	}
	
	private void handle(WeightedEdge e){
		if(mainList.hasEdge(e)){
			WeightedEdge previous = mainList.findEdge(e).getClone();
			mainList.deleteEdge(e);
			
			WeightedEdge updated = new WeightedEdge(e.first, e.second, previous.weight , previous.meanOfInteractions);
			updated.weight = 
					Constants.Formulas.calWeight(updated.weight, getCurrentTimestamp(), e.weight, updated.meanOfInteractions);
			updated.meanOfInteractions = 
					Constants.Formulas.meanOfIntrac(getCurrentTimestamp(), e.weight, updated.meanOfInteractions);

			mainList.putEdge(updated);
			updateEdgeInCluster(previous , updated);
		}
		else if(reserveList.hasEdge(e)){
			WeightedEdge previous = reserveList.findEdge(e).getClone();
			reserveList.deleteEdge(e);
			
			WeightedEdge updated = new WeightedEdge(e.first, e.second, previous.weight , previous.meanOfInteractions);
			updated.weight = 
					Constants.Formulas.calWeight(updated.weight, getCurrentTimestamp(), e.weight, updated.meanOfInteractions);
			updated.meanOfInteractions = 
					Constants.Formulas.meanOfIntrac(getCurrentTimestamp(), e.weight, updated.meanOfInteractions);
			
			mainList.putEdge(updated);
			edgeArrival(updated);
		}
		else{
			WeightedEdge edge = new WeightedEdge(e.first, e.second, 0.0 , 0.0);
			edge.weight = 
					Constants.Formulas.calWeight(edge.weight, getCurrentTimestamp(), e.weight, edge.meanOfInteractions);
			edge.meanOfInteractions = 
					Constants.Formulas.meanOfIntrac(getCurrentTimestamp(), e.weight, edge.meanOfInteractions);

			mainList.putEdge(edge);
			edgeArrival(edge);
		}
	}
	
	public void moveEdgeToReserve(WeightedEdge e){
		mainList.deleteEdge(e);
		reserveList.putEdge(e);
	}
	
	private void handlePriorityLists(){
		while(mainList.sizeConstraintViolated()){
			WeightedEdge toDelete = mainList.getLowestWeight().getClone();
			mainList.deleteEdge(toDelete);
			delete(toDelete);
			reserveList.putEdge(toDelete);
		}
		while(reserveList.sizeConstraintViolated()){
			WeightedEdge w = reserveList.getLowestWeight();
			reserveList.deleteEdge(w);
		}
	}
	
	public void setCurrentTimestamp(int t){
		currentTime = t; 
	}
	
	public int getCurrentTimestamp(){
		return currentTime ;
	}
	
	public void evaluate(IncrementalGraph g){
		for(WeightedEdge e : g.getList()){
			checkNode(e.first);
			checkNode(e.second);
			handle(e);
			handlePriorityLists();
		}
		currentTime++ ;
	}

	public GraphClusteringManager getGraphManager(){
		return graphManager;
	}
	
	public <T> GraphRepresentation<T> getRepresentation(HashMap<Integer, T> map) {
		return getGraphManager().getRepresentation(map);
	}
	
}
