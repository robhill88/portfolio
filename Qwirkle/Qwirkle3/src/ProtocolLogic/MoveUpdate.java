package ProtocolLogic;
import Qwirkle.*;
/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 * Move Update class implements Protocols
 * 
 * Recieves opposing players moves and assigns their tile piece to the board
 *  
 * 
 */
public class MoveUpdate implements Protocols {


	

	public void recieveProtocol(Qwirkle qwirkle, String data) {
		
		updateBoard(qwirkle, data);
	}//end method
	
	/**
	 * 
	 * Updates Game Board, sets the opposing players tile to the board
	 * 
	 * 
	 * @param qwirkle Qwirkle
	 * @param data String
	 */
	private void updateBoard(Qwirkle qwirkle, String data){
		
		Board board = qwirkle.getBoard();
		
		String[] playerMove = data.split("\\,");
		
		TilePiece tile = new TilePiece();
		
		int index = Integer.parseInt(playerMove[0]);
		
		tile.setColour(Integer.parseInt(playerMove[1]));
		tile.setShape(Integer.parseInt(playerMove[2]));
		qwirkle.getScorePanel().getPlayerTwoScore().setScore(Integer.parseInt(playerMove[3]));
		
		board.setPosition(index, tile);
	}//end method
}//end class
