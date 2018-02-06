import java.io.File;
import java.io.IOException;

/* 
 * DTD to JSON converter
 * by Brandon
 * 1.31.18
 * 
 */
public class MainConverter {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//read args
		// autodetects if path or file
		// always, takes file path, reads file, converts, saves to new file
		boolean result = false;
		boolean hasRun = false;
		
		switch (args.length){
			case 0: printf("no args"); //no args
				break;
			case 1: 
				result = verifyPath(args[0]); // file path only
				hasRun = true;
				break;
		}
		if ( !result && hasRun)
			printf("Failed");
	}
	
	public static void printf( String message ){
		System.out.println(message);
	}
	
	public static boolean verifyPath(String path) throws IOException{
		File file = new File(path);
		if (file.isDirectory()){
			JSONfolder.buildFiles(path);
			return true;
		}
		if (file.exists()){
			JSONfile.buildFile(path, file.getName());
			return true;
		}
		return false;
	}
}
