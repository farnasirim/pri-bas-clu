package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import clustering.IncrementalGraph;

public class NormalInput <T> {
	public boolean hasNext(){
		return numNext != -1 ;
	}
	
	Scanner inp ;
	
	private int numNext;
	
	public NormalInput(String file){
		try {
			inp = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		readNum();
	}
	
	private void readNum(){
		numNext = inp.nextInt();
	}
//	private static int line = 0;
	public GenericDataStash<T> getData(){
		GenericDataStash<T> ret = new GenericDataStash<>();
		ret.timeStamp = inp.nextInt() ;
		
		for(int i = 0 ; i < numNext ; i++){
			T from = null , to = null ;
			Double weight ;
//			System.out.println("read line : " + (line ++));
			from = Caster.cast(inp.next() , from);
			to =   Caster.cast(inp.next(), to);
			weight = inp.nextDouble() ;
			ret.al.add(new GenericEdge<T>(from , to ,  weight));
		}
		
		readNum();
		return ret; 
	}
	
	public void close(){
		inp.close();	
	}
	
}
