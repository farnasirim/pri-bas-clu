package clustering;

import java.util.Comparator;
import java.util.HashMap;

public class MainEdgeList {
	HashMap<Pair<Integer, Integer > , Edge> map ;
	
	public MainEdgeList(Comparator <Pair <Integer, Integer> > cmp){
		map = new HashMap<>();
	}
	
}
