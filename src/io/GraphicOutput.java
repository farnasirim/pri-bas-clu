package io;

import java.io.PrintWriter;

import clustering.GraphRepresentation;

public class GraphicOutput<T> extends NormalOutput<T>{

	public GraphicOutput(String logfiledir, String infilename) {
		super(logfiledir, infilename);
	}
	
	@Override
	public void log(GraphRepresentation<T> rep) {
		PrintWriter pw = getLogger() ;
		pw.println(rep.getGuiOutput());
	}
	
}
