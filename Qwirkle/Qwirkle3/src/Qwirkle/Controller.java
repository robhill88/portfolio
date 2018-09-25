package Qwirkle;
import Enums.GameMode;

/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 * Controller to start the program
 *  
 * 
 */
public class Controller {

	
	
	/**
	 * Main thread starts here
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Qwirkle qwirkle = new Qwirkle(GameMode.MEDIUM);
		Client client = new Client(qwirkle);
		qwirkle.setClient(client);
		
		
		System.out.println("Waiting on another player!");
	}
}
