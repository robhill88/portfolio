package ProtocolLogic;
import java.util.Scanner;

import Enums.Destination;
import Enums.GameMode;
import Enums.Protocol;
import Qwirkle.*;
/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 * Turn class implements Protocol
 *  
 *  Class runs when the player makes a move
 *  
 *  Further development add error checking for user inputs
 *  
 * 
 */
public class Turn implements Protocols {


	/* (non-Javadoc)
	 * @see ProtocolLogic.Protocols#recieveProtocol(Qwirkle.Qwirkle, java.lang.String)
	 */
	public void recieveProtocol(Qwirkle qwirkle, String data) {

		playerTurn(qwirkle);
	}
	
	
	/**
	 * Outputs and inputs to aid the player in making their move
	 * 
	 * @param qwirkle Qwirkle
	 */
	public void playerTurn(Qwirkle qwirkle){

		Board board = qwirkle.getBoard();

		@SuppressWarnings("resource")
		Scanner kIn = new Scanner(System.in);

		int tileNum = -1;
		int pos = 0;
		int row = 0; 
		int points = 0; 
		
		char horizontal;
		
		TilePiece tileToMove = null;
		
		boolean valid = false;
	
		while(!valid){	//Loops until valid placement
			
			while(tileNum < 0 || tileNum >= 6){ //Loops until user selects valid piece, no check for whether the user selects a null piece

				System.out.println("Choose  Your Piece");
				tileNum = (kIn.nextInt() - 1);
			}//end tile piece selction loop

			System.out.println();
			System.out.println("Row Num: ");

			row = (kIn.nextInt() -1);

			System.out.println();
			System.out.println("Horizontal Position: ");

			String hor = kIn.nextLine();
			hor = kIn.nextLine();

			horizontal = hor.charAt(0);

			pos  = calculatePos(GameMode.MEDIUM, row, horizontal);

			tileToMove = qwirkle.getHand()[tileNum];

			valid = checkValidMove(board.getBoard(), tileToMove, pos);
		}//end validation loop

		
		
		System.out.println("To Move: " + tileToMove);

		board.setPosition(pos, tileToMove);
			
		points = calculatePoints(qwirkle.getGameMode(), qwirkle.getBoard().getBoard(), pos);
		
		qwirkle.getScorePanel().getPlayerOneScore().setScore(points);
		
		qwirkle.getHand()[tileNum] = null;
		
		String dataToSend  = pos + "," + tileToMove.toInt() + "," + points;
		
		System.out.println("Place Another Tile Press 1 : End Turn 9");	
		
		tileNum = kIn.nextInt();

		
		
		if(tileNum == 1){

			qwirkle.sendProtocol(qwirkle.getConnectionID(),Destination.TO, Protocol.MOVEUPDATE, dataToSend);

		}else{

			qwirkle.sendProtocol(qwirkle.getConnectionID(), Destination.TO, Protocol.MOVEUPDATE, dataToSend);

			int totalCards = 0;

			for(TilePiece tile : qwirkle.getHand()){

				if(tile == null){

					totalCards ++;
				}
			}

			dataToSend = Integer.toString(totalCards);
			qwirkle.sendProtocol(qwirkle.getConnectionID(), Destination.TO, Protocol.ENDTURN, dataToSend);
			System.out.println("Waiting on Player Two");
			qwirkle.waitThread(qwirkle);
		}
	}



	/**
	 * calculates board grid array index
	 * 
	 * @param gameMode GameMode
	 * @param row int
	 * @param letter char
	 * @return int
	 */
	private int calculatePos(GameMode gameMode, int row, char letter){

		int hor = (Character.getNumericValue(letter) - 10);
		int position = (gameMode.getValue() * row) + hor;
		return position;
	}



	/**
	 * Checks whether move is valid
	 * Further development check whether the tile is able to fit in that row or column
	 * 
	 * @param board TilePiece[]
	 * @param tile TilePiece
	 * @param position int
	 * @return Boolean
	 */
	private Boolean checkValidMove(TilePiece[] board, TilePiece tile, int position){
		
		boolean valid;
		
		if(board[position] == null){
			
			valid = true;
		}
		else{
			
			valid = false;
		}
		
		valid = true;

		return valid;
	}
	
	
	
	/**
	 * Calculates the points for the tile placement
	 * 
	 * @param gameMode GameMode
	 * @param board TilePiece[]
	 * @param position int
	 * @return int
	 */
	private int calculatePoints(GameMode gameMode, TilePiece[] board, int position){
		
		int points = 0;
		int verticalCheck = gameMode.getValue();
		
		points += checkHorizontalPoints(board, position);
		points += checkVerticalPoints(board, position, verticalCheck);
		
		System.out.println("Points: " + points );
		return points;
	}
	
	
	/**
	 * Checks the points for the column
	 * 
	 * @param board TilePiece[]
	 * @param position int
	 * @return int
	 */
	private int checkHorizontalPoints(TilePiece[] board, int position){

		int count = 1;
		int points = 0;
		
		while(position + count < board.length && board[position + count] != null){

			points += 1;
			count++;
		}

		count = 1;

		while(position - count >= 0 && board[position - count] != null){

			points += 1;
			count++;
		}
		return points;
	}
	
	
	/**
	 * Checks the points on the row
	 * 
	 * @param board TilePiece[]
	 * @param position int
	 * @param verticalCheck int
	 * @return int
	 */
	private int checkVerticalPoints(TilePiece[] board, int position, int verticalCheck){

		int count = verticalCheck;
		int points = 0; 
		
		while(position + count < board.length && board[position + count] != null ){

			points += 1;
			count++;
		}
		
		count = verticalCheck;

		while(position - count >= 0 && board[position - count] != null){

			points += 1;
			count++;
		}
		
		return points;
	}
}
