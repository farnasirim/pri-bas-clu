package clustering;

import help.InvalidCallException;

public class WeightedEdge {
	public Integer first, second ;
	public Double weight ;
	public Double meanOfInteractions ;
	
	@Override
	public String toString() {
		return first + " " + second + " "  + weight +  " " + meanOfInteractions ;
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
	}
	
	public WeightedEdge(Integer a , Integer b , Double w , Double mean){
		this(a , b, w);
		meanOfInteractions = mean ;
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
			if(this.weight == obj.weight){
				if(this.first == obj.first){
					return this.second == obj.second;
				}
				return this.first == obj.first;
			}
			return this.weight == obj.weight;
		}
		return super.equals(obj1);
	}
	public WeightedEdge getClone(){
		return new WeightedEdge(first , second, weight,meanOfInteractions);
	}
}
