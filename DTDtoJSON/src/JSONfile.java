import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONfile {
	private ArrayList<JSONelement> JSONelements = new ArrayList<JSONelement>();
	private String JSONcompleteString = "";
	private String fileName = "messages.json";
	
	private JSONfile(ArrayList<JSONelement> elements){
		this.JSONelements = elements;
	}
	
	public JSONfile(String path, String name) throws IOException{
		this(JSONify(path + "\\" + name));
	}
	
	public static JSONfile buildFile( String path, String name ) throws IOException{
		return new JSONfile(path, name);
	}
	
	private static ArrayList<JSONelement> JSONify(String path) throws IOException{
		ArrayList<JSONelement> readElements = new ArrayList<JSONelement>();
		BufferedReader rdr = null;
		try {
			rdr = new BufferedReader(new FileReader(path));
			
		    String txtLine = "";
		    while( rdr.read() != -1 ){
		    	txtLine = rdr.readLine();
			    String[] line = txtLine.split(" ");
			    String message = "";
			    for (int i = 2; i<line.length; i++){
			    	message += line[i] + " ";
			    	if (i == line.length-1)
			    		message = message.substring(1, message.length()-3);
			    }
			    if (line.length > 1)
			    	readElements.add(JSONelement.buildElement(line[1], message));
		    }
		} catch (IOException e) {
		    System.err.println("Caught IOException: " + e.getMessage());
		} finally {
			rdr.close();
		}
		return readElements;
	}
	
	public void saveFile(String path) throws IOException{
		stringify(this.JSONelements);
		String fullPath = path + "\\" + this.fileName;
		
		BufferedWriter rdr = null;
		
		try {
			rdr = new BufferedWriter(new FileWriter(fullPath));
			
		    rdr.write(this.JSONcompleteString);
		    
		} catch (IOException e) {
		    System.err.println("Caught IOException: " + e.getMessage());
		} finally {
			rdr.close();
		}
	}
	
	public ArrayList<JSONelement> getElements(){
		return this.JSONelements;
	}
	
	private void stringify(ArrayList<JSONelement> elements){
		String result = "{\n";
		for(int i = 0; i<elements.size(); i++){
			result += elements.get(i).toString();
			if ( i == elements.size()-1){
				result += "\n\n";
			} else {
				result += ",\n\n";
			}
		}
		result += "}";
		
		this.JSONcompleteString = result;
	}
	
	public String toString(){
		return this.JSONcompleteString;
	}
}
