import java.io.File;

/* 
 * DTD to JSON converter
 * by Brandon
 * 1.31.18
 * 
 */
public class MainConverter {
	private static String pathType = ""; //file or directory
	private static String argType = "";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//read args
		// last arg, always file path
		// autodetects if path or file
		// -p Print, prints message and title to screen
		// -d Debug, prints everything read to screen
		// always, takes file path, reads file, converts, saves to new file
		switch (args.length){
			case 0: printf("no args"); //no args
				break;
			case 1: // file path only
				
				break;
			case 2: // file path + arg
				
				break;
		}
	}
	
	public static void printf( String message ){
		System.out.println(message);
	}
	
	public static boolean verifyPathWithArg(String path, String args){
		char arg = args.charAt(1);
		boolean result = false;
		
		switch(arg){
		case 'p':
			argType = "p";
			result = verifyPath(path) ? true : false;
			break;
		case 'd':
			argType = "d";
			result = verifyPath(path) ? true : false;
			break;
		}
		return result;
	}
	
	public static boolean verifyPath(String path){
		File file = new File(path);
		if (file.isDirectory()){
			pathType = "directory";
			return true;
		}
		if (file.exists()){
			pathType = "file";
			return true;
		}
		return false;
	}

}
