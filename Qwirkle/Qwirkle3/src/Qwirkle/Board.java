package Qwirkle;
import Enums.GameMode;
/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 * Qwirkle Game Board Class 
 *  
 * Outputs board to user size depends on the game mode
 */
public class Board {

	private int totalSize;
	private GameMode gameMode;
	private boolean first = true;
	private TilePiece[] GameBoard;

	
	/**
	 * Instantiates  new Board with Game Mode as Parameter
	 *  
	 * @param gameMode GameMode
	 */
	public Board(GameMode gameMode){
		
		this.gameMode = gameMode;
		
		totalSize = gameMode.getValue() * gameMode.getValue();

		if(first){
			GameBoard = new TilePiece[totalSize];
			first = false;
		}
	}

	
	/**
	 * Outputs Board to User 
	 */
	public void displayBoard(){
		
		
		System.out.print("\t");

		for(int f = 97; f < (97 + gameMode.getValue()); f++){	//Outputs top row letters

			char ascii = (char)f;
			System.out.print("    " + ascii + "   ");
		}

		System.out.println();
		System.out.println();
		System.out.print("1");
		System.out.print("\t");


		for(int i = 1; i <= totalSize; i++){

			if(i >= gameMode.getValue() && i % gameMode.getValue() == 0){

				System.out.println("|");
				System.out.print("\t");

				for(int j = 0; j < gameMode.getValue(); j++){

					System.out.print("--------");
				}

				System.out.println();


				if( i != totalSize){//Prevents An Extra Row from outputting

					System.out.print((i + gameMode.getValue()) / gameMode.getValue());
					System.out.print("\t");
				}

			}
			else{

				//Outputs blank space
				if(GameBoard[i-1] == null){	

					System.out.print("|       ");
				}

				//Outputs any piece
				else{

					System.out.print("| " + GameBoard[i-1].toString() + " ");	
				}
			}
		}

	}//end method


	/**
	 * Set Game board with TilePiece Array as parameter
	 * 
	 * @param GameBoard TilePiece[]
	 */
	public void setBoard(TilePiece[] GameBoard){

		this.GameBoard = GameBoard;
	}
	
	
	
	/**
	 * Set Position within the board parameters index as int and tile to place as TilePiece
	 * @param index int
	 * @param tile TilePiece
	 */
	public void setPosition(int index, TilePiece tile){
		
		GameBoard[index] = tile;
	}

	
	
	/**
	 * Returns Game Board as TilePiece[]
	 * @return TilePiece[] Game board
	 */
	public TilePiece[] getBoard(){

		return GameBoard;
	}

}
