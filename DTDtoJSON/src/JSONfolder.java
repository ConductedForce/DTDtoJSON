import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONfolder {
	private static ArrayList<JSONfile> JSONfiles = new ArrayList<JSONfile>();
	private static String FileName = "messages.json";
	
	private static File[] finder( String dirName){
        File dir = new File(dirName);

        return dir.listFiles(new FilenameFilter() { 
                 public boolean accept(File dir, String filename)
                      { return filename.endsWith(".dtd"); }
        } );

    }
	
	public static void buildFiles( String dirPath ) throws IOException{
		File[] fileNames = finder( dirPath );
		
		for ( int i = 0; i< fileNames.length; i++){
			String fileName = fileNames[i].getName();
			JSONfiles.add(JSONfile.buildFile(dirPath, fileName));
		}
		
		saveFile(dirPath);
	}
	
	private static void saveFile(String dirPath) throws IOException{
		String result = "{\n";
		
		for(int i = 0; i<JSONfiles.size(); i++){
			result += stringify(JSONfiles.get(i).getElements());
		}
		result += "}";
		
		String fullPath = dirPath + "\\" + FileName;
		
		BufferedWriter rdr = null;
		
		try {
			rdr = new BufferedWriter(new FileWriter(fullPath));
			
		    rdr.write(result);
		    
		} catch (IOException e) {
		    System.err.println("Caught IOException: " + e.getMessage());
		} finally {
			rdr.close();
		}
	}
	
	private static void saveFiles(String dirPath) throws IOException{
		for(int i = 0; i<JSONfiles.size(); i++){
			JSONfiles.get(i).saveFile(dirPath);
		}
	}
	
	private static String stringify(ArrayList<JSONelement> elements){
		String result = "";
		for(int i = 0; i<elements.size(); i++){
			result += elements.get(i).toString();
			if ( i == elements.size()-1){
				result += "\n\n";
			} else {
				result += ",\n\n";
			}
		}

		return result;
	}
}
