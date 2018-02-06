
public class JSONelement {
	private String JSONname = "";
	private String JSONmessage = "";
	
	public JSONelement (String name, String message){
		this.JSONname = name;
		this.JSONmessage = message;
	}
	
	public static JSONelement buildElement(String name, String message){
		return new JSONelement(name, message);
	}
	
	public String toString(){
		return "\t'" + JSONname + "': {\n" 
				+ "\t  'message': '" + JSONmessage + "',\n"
				+ "\t  'description': ''\n"
				+ "\t},\n";
	}
}
