package test;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

import clustering.ClusterRepresentation;
import clustering.EdgeRepresentation;
import clustering.Graph;
import clustering.GraphRepresentation;
import clustering.WeightedEdge;
import clustering.WeightedEdgeWeightComparator;

public class Main {
	
	public static void testFunc(){
		Graph <String> graph = new Graph();
		Scanner inp = new Scanner(System.in);
		while(inp.hasNext()){
			int n = inp.nextInt();			
			for(int i = 0 ;i <n ; i++){
				String a , b;
				double w;
				a= inp.next() ;
				b = inp.next() ;
				w = inp.nextDouble() ;
				graph.addEdge(a, b, w);
			}
			graph.evaluate();
			
			GraphRepresentation<String> rep= graph.getRepresentation();
			for(ClusterRepresentation<String> clu : rep.getClusters()){
				System.out.println("Nodes : ");
				for(String nd : clu.getNodes()){
					System.out.print(nd + " ");
				}
				System.out.println();
				System.out.println("Edges : ");
				for(EdgeRepresentation<String> e : clu.getEdges()){
					System.out.println(e.first + " " + e.second +" " + e.meanOfInteractions +" " + e.weight);
				}
				System.out.println();
			}
		}
	}
	
	public static void main(String[] args) {
//		TreeSet<WeightedEdge> set = new TreeSet<WeightedEdge>(new WeightedEdgeWeightComparator()) ;
//		set.add(new WeightedEdge(1 , 2 , 0. , 6.));
//		set.add(new WeightedEdge(2 , 3 , 0. , 10.));
//		
//		for(WeightedEdge e : set){
//			System.out.println(e);
//		}
//		
//		if(1 == 1)
//			return ;
		
		testFunc();
		if(1 == 1)
			return ;
		
		Graph<Integer> graph = new Graph<>() ;
		
		graph.addEdge(1 ,  2,  3.);
		graph.addEdge(2 ,  7,  5.);
		graph.addEdge(6 ,  9,  8.);
		
		graph.evaluate();
		GraphRepresentation<Integer> rep= graph.getRepresentation();


			
		for(ClusterRepresentation<Integer> clu : rep.getClusters()){
			System.out.println("Nodes : ");
			for(Integer nd : clu.getNodes()){
				System.out.print(nd + " ");
			}
			System.out.println();
			System.out.println("Edges : ");
			for(EdgeRepresentation<Integer> e : clu.getEdges()){
				System.out.println(e.first + " " + e.second + " " + e.weight + " " + e.meanOfInteractions);
			}
			System.out.println();
		}
	}
}
