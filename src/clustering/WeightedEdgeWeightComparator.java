package clustering;

import java.util.Comparator;

public class WeightedEdgeWeightComparator implements Comparator<WeightedEdge>{

	@Override
	public int compare(WeightedEdge arg0, WeightedEdge arg1) {
//		System.out.println("comping : " + arg0 + " , " + arg1);
		Integer result ;
		if(Double.compare(arg0.weight , arg1.weight)== 0){
			if(arg0.first == arg1.first){
				result = Integer.compare(arg0.second, arg1.second);
//				System.out.println("sec " + result);
				return result ;
			}
			result = Integer.compare(arg0.first, arg1.first);
//			System.out.println("fi " + result);
			return result ;
		}
		result = Double.compare(arg0.weight, arg1.weight);
//		System.out.println("we " + result);
		return result;
	}
}
