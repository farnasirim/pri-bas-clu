package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
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
	
	private static String LOG_DIR = "log/";
	
	private static PrintStream createLogger() throws FileNotFoundException{
		int now = 0 ;
		String logPrefix = LOG_DIR + "lg";
		while(new File((String)logPrefix + now + ".txt").exists()){
			now ++;
		}
		return new PrintStream(new File(logPrefix + now + ".txt"));
	}
	
	private static <T> void printMap(Graph<T> g){
		System.out.println();
		for(Integer key : g.getInverseMap().keySet())
			System.out.println(key + " " + g.getInverseMap().get(key));
		
	}
	
	public static void testFunc(){
		PrintStream printer = System.out; 
		try {
			printer = createLogger();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		printer.println("Parameters : ");
		printer.println("Main list size : " + help.Constants.Parameters.MAIN_PRIORITY_LIST_SIZE);
		printer.println("Reserve list size : " + help.Constants.Parameters.RESERVE_PRIORITY_LIST_SIZE);
		printer.println("max cluster size : " + help.Constants.Parameters.CLUSTER_SIZE);
		
		printer.println();
		printer.println("*********************************************************************");
		printer.println();

		Graph <String> graph = new Graph();
//		Scanner inp = new Scanner(System.in);
		Scanner inp = null;
		try {
			inp = new Scanner(new File("data/datanozero2.txt"));
//			inp = new Scanner(new File("data/debug.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(inp.hasNext()){
			int n = inp.nextInt();	
			if(n == -1)
				break ;
			printer.println("query : ");
			printer.println(n);
			
			for(int i = 0 ;i <n ; i++){
				String a , b;
				double w;
				a= inp.next() ;
				b = inp.next() ;
				w = inp.nextDouble() ;
				printer.println(a + " " + b + " " + w);
				graph.addEdge(a, b, w);
			}
			graph.evaluate();
			printer.println();
			printer.println();
			GraphRepresentation<String> rep= graph.getRepresentation();
			printer.println("Main list :");
			for(EdgeRepresentation<String> e: rep.getMainList()){
				printer.println(e);
			}
			printer.println();
			printer.println("Reserve list :");
			for(EdgeRepresentation<String> e : rep.getReserveList()){
				printer.println(e);
			}
			printer.println();
			for(ClusterRepresentation<String> clu : rep.getClusters()){
				printer.println("Nodes : ");
				for(String nd : clu.getNodes()){
					printer.print(nd + " ");
				}
				printer.println();
				printer.println("Edges : ");
				for(EdgeRepresentation<String> e : clu.getEdges()){
					printer.println(e.first + " " + e.second +" " + e.meanOfInteractions +" " + e.weight);
				}
				printer.println();
			}
			printer.println();
			printer.println("---------------------------------------------------------------------");
			printer.println();
					
		}
		printMap(graph);
		printer.close();
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
