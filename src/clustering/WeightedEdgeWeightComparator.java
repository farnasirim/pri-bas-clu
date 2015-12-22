package clustering;

import java.util.Comparator;

public class WeightedEdgeWeightComparator implements Comparator<WeightedEdge> {

	@Override
	public int compare(WeightedEdge arg0, WeightedEdge arg1) {
		// System.out.println("comping : " + arg0 + " , " + arg1);
		Integer result;
		if (Double.compare(arg0.getNewWeight(), arg1.getNewWeight()) == 0) {
			if (Double.compare(arg0.getWeight(), arg1.getWeight()) == 0) {
				// if(Double.compare(arg0.getMean(), arg1.getMean()) == 0){
				if (Integer.compare(arg0.getFirst() , arg1.getFirst()) == 0) {
					result = Integer.compare(arg0.getSecond(), arg1.getSecond());
					// System.out.println("sec " + result);
					return result;
				}
				result = Integer.compare(arg0.getFirst(), arg1.getFirst());
				// System.out.println("fi " + result);
				return result;
				// }
				// return Double.compare(arg0.getMean(), arg1.getMean());
			}
			return Double.compare(arg0.getWeight(), arg1.getWeight());
		}
		result = Double.compare(arg0.getNewWeight(), arg1.getNewWeight());
		// System.out.println("we " + result);
		return result;
	}
}
