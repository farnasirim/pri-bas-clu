package clustering;

public class EdgeRepresentation<T> {
	public T first ;
	public T second ;
	public Double weight ;
	public Double meanOfInteractions ;
	public Integer t ;
	public Double newW ;
	public EdgeRepresentation(T fi , T se , Double we , Double me , Integer time , Double newWeight) {
		first = fi ;
		second = se ;
		weight = we ;
		meanOfInteractions = me ;
		t = time ;
		newW = newWeight ;
	}
	
	@Override
	public String toString() {
		return first + " " + second + " "  + weight +  " " + meanOfInteractions + " " + t + " " +(weight + t*meanOfInteractions) ;
	}
	
}
