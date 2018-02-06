import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONfolder {
	private ArrayList<JSONfile> JSONfiles = null;
	private File[] fileNames = null;
	
	private File[] finder( String dirName){
        File dir = new File(dirName);

        return dir.listFiles(new FilenameFilter() { 
                 public boolean accept(File dir, String filename)
                      { return filename.endsWith(".dtd"); }
        } );

    }
	
	public void buildFiles( String dirPath ) throws IOException{
		fileNames = finder( dirPath );
		
		for ( int i = 0; i< fileNames.length; i++){
			String fileName = fileNames[i].getName();
			JSONfiles.add(JSONfile.buildFile(dirPath, fileName));
		}
		
		saveFiles(dirPath);
	}
	
	private void saveFiles(String dirPath) throws IOException{
		for(int i = 0; i<JSONfiles.size(); i++){
			JSONfiles.get(i).saveFile(dirPath);
		}
	}
}
