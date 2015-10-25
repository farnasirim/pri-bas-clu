package help;

public class Constants {
	public static class Parameters{
		public static int MAIN_PRIORITY_LIST_SIZE ;
		public static int RESERVE_PRIORITY_LIST_SIZE ;
		public static int CLUSTER_SIZE;
		static{
			MAIN_PRIORITY_LIST_SIZE = 10;
			RESERVE_PRIORITY_LIST_SIZE = 10;
			CLUSTER_SIZE = 20;
		}
	}
	public static class Formulas{
		public static double calWeight(double PastW, int TmSp , double NumOfIntrac , double MeanNofInterac){
			double n = TmSp ;                       // Current time-stamp
			                                        //NumOfIntrac is number of interactions between time-stamp n-1 till n for this edge
			double landaBar_PN = MeanNofInterac;    // mean of interaction between time-stamp 0 till  n-1
			double deltaBar_PN = PastW;             // edge's weight in time-stamp n-1 
			
			// current edge's weight 
			double deltaBar_CN =( ( n/(n+1))*deltaBar_PN ) + ( ( ( 2*(n-1)*(n) )/( Math.pow((n+1),2)) ) * (Math.pow( (NumOfIntrac - landaBar_PN ),2)) );
//			System.out.println("pastw : " + PastW);
//			System.out.println("Time  : " + TmSp);
//			System.out.println("NumInter : " + NumOfIntrac);
//			System.out.println("Meanof : " + MeanNofInterac);
//			System.out.println("deltabar : " + deltaBar_CN );
//			System.out.println();
			return deltaBar_CN;
			
		}
		
		public static double meanOfIntrac(int TmSp, double NumOfIntrac , double PastMeanOfInterac){
			// initial landaBar0  = 0 
			// landaBar1 = NumberOfInteraction occur in time-stamp 1
			double n = TmSp;                          // current time-stamp
			double landaBarPN = PastMeanOfInterac;    // Mean of interaction till time-stamp n-1
			double landaBarCN ;                       // Mean of interaction in current time-stamp
			
			landaBarCN = ( (2/(n+1)) * ( NumOfIntrac - landaBarPN ) ) + landaBarPN;
//			System.out.println("tm : " + TmSp);
//			System.out.println("num inter : " + NumOfIntrac);
//			System.out.println("past mean" + PastMeanOfInterac);
//			System.out.println("landa bar: " + landaBarCN);
//			System.out.println();
			return landaBarCN;
		}
		
	}
}
