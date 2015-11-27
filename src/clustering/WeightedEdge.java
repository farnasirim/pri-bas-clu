package clustering;

import help.InvalidCallException;

public class WeightedEdge {
	private Integer first, second ;
	private Double weight ;
	private Double meanOfInteractions ;
	private Integer t ;
	
	public Double getNewWeight(){
		return getWeight() + t*getMean();
	}
	
	public Integer getTime(){
		return t;
	}
	
	public void setTime(Integer t){
		this.t = t; 
	}
	
	public void setWeight(Double w){
		this.weight = w ;
	}
	
	public void setMean(Double m){
		this.meanOfInteractions = m ;
	}
	
	public Integer getFirst(){
		return first ;
	}
	
	public Integer getSecond(){
		return second ;
	}
	
	public Double getWeight(){
		return weight;
	}
	
	public Double getMean(){
		return meanOfInteractions;
	}
	
	@Override
	public String toString() {
		return first + " " + second + " "  + weight +  " " + meanOfInteractions + " " + t + " " + getNewWeight();
	}
	
	WeightedEdge(Integer a , Integer b , Double w){
		first = a ;
		second = b; 
		if(first > second){
			Integer tmp = second ;
			second = first; 
			first = tmp ;
		}
		weight = w ;
		meanOfInteractions = 0.0 ;
		t = 0 ;
	}
	
	public WeightedEdge(Integer a , Integer b , Double w , Double mean){
		this(a , b, w);
		meanOfInteractions = mean ;
	}

	public WeightedEdge(Integer a , Integer b , Double w , Double mean, Integer time){
		this(a , b, w,mean);
		this.t = time ;
	}

	
	public WeightedEdge(WeightedEdge w){
		this(w.getFirst() , w.getSecond() , w.getWeight() , w.getMean() , w.getTime());
	}
	
	@Override
	public int hashCode(){
		System.err.println("INVALID CALL TO WEIGHTED EDGE");
		try{
			throw new InvalidCallException();
		}
		catch(Exception e){
			e.printStackTrace(System.err);
		}
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj1) {
		if(obj1 instanceof WeightedEdge){
			WeightedEdge obj = (WeightedEdge) obj1 ;
			if(this.t.equals(obj.t)){
				if(this.weight.equals(obj.weight)){
					if(this.first.equals(obj.first)){
						return this.second.equals(obj.second);
					}
				}
			}
		}
		return false; 
	}
	public WeightedEdge getClone(){
		return new WeightedEdge(this);
	}
}
