package test;

import clustering.Graph;
import io.GraphicOutput;
import io.NormalInput;
import io.NormalOutput;

public class Main{
	
	public static void main(String [] args){
			
			final String INPUT_FILE_DIRECTORY = "data/moreno/";
			final String INPUT_FILE_NAME      = "moreno.in";
			
			final String GUI_FILE_DIRECTORY = "GUI/"; 
			final String LOG_FILE_DIRECTORY = "log/";
					
			Graph<String> g = new Graph<>();
			g.setInputter(new NormalInput<>(INPUT_FILE_DIRECTORY + INPUT_FILE_NAME));
			g.setOutputter(new NormalOutput<>(LOG_FILE_DIRECTORY , INPUT_FILE_NAME));
//			g.setOutputter(new GraphicOutput<>(GUI_FILE_DIRECTORY , INPUT_FILE_NAME));
			while(g.hasNextInput()){
				g.readAndEvaluateInput();
//				g.log();
			}
			g.log();
			g.close() ;
	}

}