

import java.util.Collections;
import java.util.LinkedList;

public class CreateTileBag {
	
	/**
	 * creates all the game pieces
	 * @param pMode eGameMode
	 */
	public LinkedList<TilePiece> createTiles(eGameMode pMode){
		
			LinkedList<TilePiece> tGameBag = new LinkedList<TilePiece>();
	
			int setOfPieces = 0;
				
			switch(pMode.getValue()){
	
				case(0): setOfPieces = 1;	//small game
					break;
				
				case(1): setOfPieces = 3;	//medium game
					break;
				
				case(2): setOfPieces = 6;   //large game
					break;
				
			default: System.out.println("Error: Set of Pieces out of bounds");
			}
		
		
			for(int i = 0; i < setOfPieces; i++){//game type loop
			
				for(int j = 0; j < 6; j++){//colours loop
			
					for(int k = 0; k < 6; k++){//shape loop
					
						TilePiece tPiece = new TilePiece();
				
						tPiece.setColour(j);
						tPiece.setShape(k);
						tGameBag.add(tPiece);
				
					}//end shape loop
				}//end colours loop
			}//end game type loop
			System.out.println("All Pieces implemented successfully");
			
			Collections.shuffle(tGameBag);
			return tGameBag;
	}//end method
	
	
	
	
	/**
	 * Public method returns tile piece linked List containing tiles to replenish players hand 
	 * @param totalTiles int
	 * @return LinkedList tilePiece
	 */
	public LinkedList<TilePiece> getTiles(LinkedList<TilePiece> pTileBag, int totalTiles){
		
		LinkedList<TilePiece> tilesToSend = new LinkedList<TilePiece>();
		
		for(int i = 0; i < totalTiles; i++){
			
			tilesToSend.add(pTileBag.remove(0));
		}//end for loop
		
		return tilesToSend;
	}//end method
}
