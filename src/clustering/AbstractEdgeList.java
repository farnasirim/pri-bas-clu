package clustering;

public abstract class AbstractEdgeList {
	
	public abstract Edge findEdge(Pair< Integer , Integer> p);
	public abstract boolean hasEdge(Pair < Integer , Integer > p);
}
