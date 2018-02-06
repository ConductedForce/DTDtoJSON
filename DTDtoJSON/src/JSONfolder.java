import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONfolder {
	private static ArrayList<JSONfile> JSONfiles = new ArrayList<JSONfile>();
	
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
		
		saveFiles(dirPath);
	}
	
	private static void saveFiles(String dirPath) throws IOException{
		for(int i = 0; i<JSONfiles.size(); i++){
			JSONfiles.get(i).saveFile(dirPath);
		}
	}
}
