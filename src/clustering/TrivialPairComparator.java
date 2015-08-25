package clustering;

import java.util.Comparator;

public class TrivialPairComparator <T1 extends Comparable<T1>, T2 extends Comparable<T2> > implements Comparator<Pair <T1 , T2>>{
	@Override
	public int compare(Pair<T1, T2> arg0, Pair<T1, T2> arg1) {
		int ret0 = arg0.first.compareTo(arg1.first);
		if(ret0 != 0){
			return ret0 ;
		}
		return arg0.second.compareTo(arg1.second);
	}

}
