package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import clustering.ClusterRepresentation;
import clustering.EdgeRepresentation;
import clustering.GraphRepresentation;

public class NormalOutput <T>{
	private PrintWriter logger;
	public NormalOutput(String logfiledir , String infilename){
		try {
			int num = 0;
			while(true){
				File ff =new File(logfiledir + infilename + "_log"  + num + ".txt");
				if(!ff.exists()){
					logger = new PrintWriter(ff);
					break ;
				}
				num ++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public PrintWriter getLogger(){
		return logger;
	}
	
	public void log(GraphRepresentation<T> rep){
		logger.println("Main list :");
		for(EdgeRepresentation<T> e: rep.getMainList()){
			logger.println(e);
		}
		logger.println();
		logger.println("Reserve list :");
		for(EdgeRepresentation<T> e : rep.getReserveList()){
			logger.println(e);
		}
		logger.println();
		logger.println("Num clu : " + rep.getClusters().size());
		for(ClusterRepresentation<T> clu : rep.getClusters()){
			logger.println("Nodes : ");
			for(T nd : clu.getNodes()){
				logger.print(nd + " ");
			}
			logger.println();
			logger.println("Edges : ");
			for(EdgeRepresentation<T> e : clu.getEdges()){
				logger.println(e.first + " " + e.second +" " + e.newW);
			}
			logger.println();
		}
		logger.println();
		logger.println("*************************************************************");
		logger.println();
	}
	
	public void close(){
		logger.flush();
		logger.close();
	}
	
}
