package io;

public class GenericEdge<T> {
	public T first;
	public T second;
	public Double weight;
	
	public GenericEdge(T a , T b , Double w) {
		first = a ;
		second = b ;
		weight = w; 
	}
	
}
