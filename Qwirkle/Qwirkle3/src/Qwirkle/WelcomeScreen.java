package Qwirkle;
import java.util.Scanner;

import Enums.Destination;
import Enums.Protocol;

/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 * Outputs the opening menu screen for the game
 *  
 * 
 */
public class WelcomeScreen {
	
	int option;
	
	/**
	 * Instantiates new WelcomeScreen
	 */
	public WelcomeScreen(){
		
		option = 0;
	}
	
	/**
	 * Outputs Welcome Screen to the player
	 */
	public void displayWelcomeScreen(Qwirkle qwirkle){
		
		@SuppressWarnings("resource")
		Scanner kInput = new Scanner(System.in); 
		
		System.out.println("Welcome To Qwirkle");
		System.out.println();
		System.out.println("Enter The Corrisponding Number: ");
		
		while(option != 1){
		System.out.println("1: Begin");
		System.out.println("2: Rules");
		System.out.println("3: How To Player");
		System.out.println("4: High Scores");
		System.out.println("9: Exit");
		
		option = kInput.nextInt();
		
		if(option == 1){
			
			enterName(qwirkle);
		}
		
		else if(option == 2){
			
			rules();
		}
		
		else if(option == 3){
			
			instructions();
		}
		
		else if(option == 4){
			
			highScore(qwirkle);
		}
		
		else if(option == 9){
			
			System.exit(0);
		}
	}
}
	
	/**
	 * Further Development Outputs rules to user
	 */
	private void rules(){
		
		//TODO further development insert rules
		System.out.println("Rules");
	}
	
	/**
	 * Further Development Output instructions to user
	 */
	private void instructions(){
		
		//TODO further development insert instructions for how the software operates
		System.out.print("Instructions");
	}
	
	
	/**
	 * Outputs high scores from the database to the player
	 * Further development place on server
	 */
	private void highScore(Qwirkle qwirkle){
		
		qwirkle.sendProtocol(qwirkle.getConnectionID(), Destination.BACK, Protocol.GETHIGHSCORE, null);
		//qwirkle.sendProtocol(0, null, null, null);
	}
	
	
	/**
	 * Player enters their name
	 * @param qwirkle Qwirkle
	 * 
	 */
	private void enterName(Qwirkle qwirkle){

		@SuppressWarnings("resource")
		Scanner kInput = new Scanner(System.in);

		System.out.println("Enter Name: ");
		String name = kInput.next();

		qwirkle.setName(name);
		qwirkle.sendProtocol(qwirkle.getConnectionID(), Destination.TO, Protocol.NAME, name);

	}
}
