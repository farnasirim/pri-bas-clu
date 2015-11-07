package io;

public class Caster {
	public static <T> T cast(String val , T a){
//		System.out.println("casting : " + val);
		if(a instanceof Integer){
			return (T) Integer.valueOf(val);
		}
		return (T) val;
	}
}
