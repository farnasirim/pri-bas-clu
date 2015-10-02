package clustering;

import java.util.Comparator;

public class WeightedEdgeWeightComparator implements Comparator<WeightedEdge>{

	@Override
	public int compare(WeightedEdge arg0, WeightedEdge arg1) {
		if(arg0.weight == arg1.weight){
			if(arg0.first == arg1.first){
				return Integer.compare(arg0.second, arg1.second);
			}
			return Integer.compare(arg0.first, arg1.first);
		}
		return Double.compare(arg0.weight, arg1.weight);
	}

}
