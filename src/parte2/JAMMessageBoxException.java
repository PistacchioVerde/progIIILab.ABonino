package parte2;

public class JAMMessageBoxException extends Exception{

	public JAMMessageBoxException(){
		super("ERR: No match for the given search criteria\n");
	}
	
}