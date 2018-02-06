import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONfile {
	private ArrayList<JSONelement> JSONelements = null;
	private String JSONcompleteString = "";
	private String fileName = "";
	
	private JSONfile(ArrayList<JSONelement> elements){
		this.JSONelements = elements;
	}
	
	public JSONfile(String path, String name) throws IOException{
		this(JSONify(path));
		this.fileName = name;
	}
	
	public static JSONfile buildFile( String path, String name ) throws IOException{
		return new JSONfile(path, name);
	}
	
	private static ArrayList<JSONelement> JSONify(String path) throws IOException{
		ArrayList<JSONelement> readElements = null;
		FileReader frdr = null;
		BufferedReader rdr = null;
		try {
			frdr = new FileReader(path);
			rdr = new BufferedReader(frdr);
			
		    String txtLine = "";
		    while( frdr.ready() ){
		    	txtLine = rdr.readLine();
			    String[] line = txtLine.split(" ");
			    
			    readElements.add(JSONelement.buildElement(line[1], line[2]));
		    }
		} catch (IOException e) {
		    System.err.println("Caught IOException: " + e.getMessage());
		} finally {
			rdr.close();
			frdr.close();
		}
		return readElements;
	}
	
	public void saveFile(String path) throws IOException{
		stringify(this.JSONelements);
		String fullPath = path+this.fileName;
		
		FileWriter frdr = null;
		BufferedWriter rdr = null;
		
		try {
			frdr = new FileWriter(fullPath);
			rdr = new BufferedWriter(frdr);
			
		    rdr.write(this.JSONcompleteString);
		    
		} catch (IOException e) {
		    System.err.println("Caught IOException: " + e.getMessage());
		} finally {
			rdr.close();
			frdr.close();
		}
	}
	
	private String stringify(ArrayList<JSONelement> elements){
		String result = "{\n";
		for(int i = 0; i<elements.size(); i++){
			result += elements.get(i).toString();
		}
		result += "}";
		
		return result;
	}
	
	public String toString(){
		return this.JSONcompleteString;
	}
}
