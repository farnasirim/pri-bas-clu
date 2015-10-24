package clustering;

public class EdgeRepresentation<T> {
	public T first ;
	public T second ;
	public Double weight ;
	public Double meanOfInteractions ;
	
	public EdgeRepresentation(T fi , T se , Double we , Double me) {
		first = fi ;
		second = se ;
		weight = we ;
		meanOfInteractions = me ;
	}
	
	@Override
	public String toString() {
		return first + " " + second + " "  + weight +  " " + meanOfInteractions ;
	}
	
}
