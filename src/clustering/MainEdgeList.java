package clustering;

import java.util.Comparator;
import java.util.HashMap;

public class MainEdgeList extends AbstractEdgeList{
	HashMap<Pair<Integer, Integer > , Edge> map ;
	
	public MainEdgeList(Comparator <Pair <Integer, Integer> > cmp){
		map = new HashMap<>();
	}
	
	
}
