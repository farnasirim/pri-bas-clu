package clustering;

import help.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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
	
	private void hardUpdate(WeightedEdge e){
		if(mainList.hasEdge(e)){
			WeightedEdge previous = mainList.findEdge(e).getClone();
			mainList.deleteEdge(e);
			
			WeightedEdge updated = 
					new WeightedEdge(e.getFirst(), e.getSecond(), previous.getWeight(), previous.getMean() , previous.getTime());
			
			updated.setWeight(
					Constants.Formulas.calWeight(updated.getWeight(), getCurrentTimestamp(), e.getWeight(), updated.getMean())
					);
			
			updated.setMean( 
					Constants.Formulas.meanOfIntrac(getCurrentTimestamp(), e.getWeight(), updated.getMean())
					);
			
			updated.setTime(getCurrentTimestamp());
			
			mainList.putEdge(updated);
			updateEdgeInCluster(previous, updated);
		}
		else{ // in reserve
			WeightedEdge previous = reserveList.findEdge(e).getClone();
			reserveList.deleteEdge(e);
			
			WeightedEdge updated = new WeightedEdge(e.getFirst(), e.getSecond(), previous.getWeight(), previous.getMean());
			updated.setWeight(
					Constants.Formulas.calWeight(updated.getWeight(), getCurrentTimestamp(), e.getWeight(), updated.getMean())
					);
			updated.setMean(
					Constants.Formulas.meanOfIntrac(getCurrentTimestamp(), e.getWeight(), updated.getMean())
					);
			updated.setTime(getCurrentTimestamp());
			
			reserveList.putEdge(updated);
		}
	}
	
	private void handle(WeightedEdge e){
//		System.out.println("handling : " + e);
		if(mainList.hasEdge(e)){
//			System.out.println("in main");
			WeightedEdge previous = mainList.findEdge(e).getClone();
			mainList.deleteEdge(e);
			
			WeightedEdge updated = new WeightedEdge(e.getFirst(), e.getSecond(), previous.getWeight(), previous.getMean());
			updated.setWeight(
					Constants.Formulas.calWeight(updated.getWeight(), getCurrentTimestamp(), e.getWeight(), updated.getMean())
					);
			updated.setMean(
					Constants.Formulas.meanOfIntrac(getCurrentTimestamp(), e.getWeight(), updated.getMean())
					);
			updated.setTime(getCurrentTimestamp());
			
			
			mainList.putEdge(updated);
			updateEdgeInCluster(previous , updated);
		}
		else if(reserveList.hasEdge(e)){
//			System.out.println("in reserve");
			WeightedEdge previous = reserveList.findEdge(e).getClone();
			reserveList.deleteEdge(e);
			
			WeightedEdge updated = new WeightedEdge(e.getFirst(), e.getSecond(), previous.getWeight(), previous.getMean());
			updated.setWeight(
					Constants.Formulas.calWeight(updated.getWeight(), getCurrentTimestamp(), e.getWeight(), updated.getMean())
					);
			updated.setMean(
					Constants.Formulas.meanOfIntrac(getCurrentTimestamp(), e.getWeight(), updated.getMean())
					);
			updated.setTime(getCurrentTimestamp());
			
			mainList.putEdge(updated);
			edgeArrival(updated);
		}
		else{
//			System.out.println("in nothing");
			WeightedEdge edge = new WeightedEdge(e.getFirst(), e.getSecond(), 0.0 , 0.0);
			edge.setWeight(
					Constants.Formulas.calWeight(edge.getWeight(), getCurrentTimestamp(), e.getWeight(), edge.getMean())
					);
			edge.setMean(
					Constants.Formulas.meanOfIntrac(getCurrentTimestamp(), e.getWeight(), edge.getMean())
					);
			edge.setTime(getCurrentTimestamp());
			
			mainList.putEdge(edge);
			edgeArrival(edge);
		}
//		System.out.println();
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
	
	public ArrayList<WeightedEdge> getEdges(){
		ArrayList<WeightedEdge> ret = new ArrayList<>();
		
		for(WeightedEdge e : mainList.getEdges()){
			ret.add(e);
		}
		for(WeightedEdge e: reserveList.getEdges()){
			ret.add(e);
		}
		return ret ;
	}
	
	public void evaluate(IncrementalGraph g){
//		System.out.println("time : " + getCurrentTimestamp());
		if(g.getTimeStamp() != -1){
//			System.out.println("time stamp set from " + getCurrentTimestamp() + " to " + g.getTimeStamp());
			setCurrentTimestamp(g.getTimeStamp());
		}
//		System.out.println("time : " + getCurrentTimestamp());
//		System.out.println("query : ");
//		for(WeightedEdge e : g.getList()){
//			System.out.println(e);
//		}
//		System.out.println();
		HashMap<Integer , HashSet<Integer> > hs = new HashMap<>();
		for(WeightedEdge e : g.getList()){
			e.setTime(getCurrentTimestamp());
			if(!hs.containsKey(e.getFirst())){
				hs.put(e.getFirst(), new HashSet<>());
			}
			hs.get(e.getFirst()).add(e.getSecond());
			checkNode(e.getFirst());
			checkNode(e.getSecond());
			handle(e);
			handlePriorityLists();
		}
		for(WeightedEdge e : getEdges()){
			boolean already = hs.containsKey(e.getFirst()) && hs.get(e.getFirst()).contains(e.getSecond()) ;
			if(!already){
				hardUpdate(new WeightedEdge(e.getFirst(), e.getSecond(), 0.0));
			}
		}
		currentTime++ ;
	}
	
	public GraphClusteringManager getGraphManager(){
		return graphManager;
	}
	
	public <T> GraphRepresentation<T> getRepresentation(HashMap<Integer, T> map) {
		GraphRepresentation<T> ret = getGraphManager().getRepresentation(map);
		ret.setInverseMap(map);
		for(WeightedEdge e : mainList.getEdges()){
			ret.addToMainList(new EdgeRepresentation<T>(map.get(e.getFirst()), map.get(e.getSecond()), e.getWeight(), e.getMean() , e.getTime() , e.getNewWeight()));
		}
		for(WeightedEdge e : reserveList.getEdges()){
			ret.addToReserveList(new EdgeRepresentation<T>(map.get(e.getFirst()), map.get(e.getSecond()), e.getWeight(), e.getMean() , e.getTime() , e.getNewWeight()));
		}
		return ret ;
	}
	
	public int getNumDel(){
		return reserveList.getNumDel();
	}
	
}
