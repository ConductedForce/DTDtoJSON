import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JSONfile {
	private ArrayList<JSONelement> elements = null;
	private String JSONcompleteString = "";
	
	public void JSONify(String path) throws IOException{
		FileReader frdr = null;
		BufferedReader rdr = null;
		try {
			frdr = new FileReader(path);
			rdr = new BufferedReader(frdr);
			
		    String txtLine = "";
		    while( frdr.ready() ){
		    	txtLine = rdr.readLine();
			    String[] line = txtLine.split(" ");
			    
			    elements.add(JSONelement.buildElement(line[1], line[2]));
		    }
		} catch (IOException e) {
		    System.err.println("Caught IOException: " + e.getMessage());
		} finally {
			rdr.close();
			frdr.close();
		}
	}
	
	public String JSONstringify(){
		String result = "{";
		for(int i = 0; i<elements.size(); i++){
			result += elements.get(i).toString();
		}
		result += "}";
		
		return result;
	}
	
	public String toString(){
		return JSONcompleteString;
	}
}
