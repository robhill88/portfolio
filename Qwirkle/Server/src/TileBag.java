

import java.util.Collections;
import java.util.LinkedList;

/**
 * Tile Bag class for game Qwirkle, creates the bag which contains all the game pieces dependent upon game size
 * 
 * @author robhill
 * @version 0.1
 * @since 14/03/2017
 */
public class TileBag{
	
	private static TileBag iBag = null;	
	private LinkedList<TilePiece> iGamePieces = new LinkedList<TilePiece>();
	static boolean first = true;
	
	//Throws an Error if more than one TileBag is instantiated 
	private TileBag(){} 
	
	
	
	/**
	 * returns the Tile Bag, only one game can be instantiated 
	 * @return TileBag
	 */
	public static TileBag getBag(eGameMode pMode){
		
		if(iBag == null){
			
			if(first){
				
				System.out.println("I made it thus far");
				first = false;
				Thread.currentThread();
				
				try {
					
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
					System.out.print("Bag Error: ");
					e.printStackTrace();
				}// end try catch
			}// end if
			
			System.out.println("I made it thus far 2");
			
			synchronized(TileBag.class){
				
				if(first){
					
					iBag = new TileBag();
					Collections.shuffle(iBag.iGamePieces);
				}// end first thread if
			}// end synchronised
		}// end bag if		
		return iBag;
	}//end method
	
	

	/**
	 * returns linked list containing all the game pieces
	 * @return LinkedList 
	 */
	public LinkedList<TilePiece> getGamePieces(){
		
		return iBag.iGamePieces;
	}//end method
	
	
	
	
	
	public LinkedList<TilePiece> createTiles(eGameMode pMode){
		
		LinkedList<TilePiece> tGameBag = new LinkedList<TilePiece>();
		System.out.println("Looping");
	
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
				
					System.out.println("Looping");
					TilePiece tPiece = new TilePiece();
			
					tPiece.setColour(j);
					tPiece.setShape(k);
					tGameBag.add(tPiece);
			
				}//end shape loop
			}//end colours loop
		}//end game type loop
		System.out.println("All Pieces implemented successfully");
		return tGameBag;
	}//end method

	
	
	
	
	/**
	 * Public method returns tile piece linked List containing tiles to replenish players hand 
	 * @param totalTiles int
	 * @return LinkedList tilePiece
	 */
	public LinkedList<TilePiece> getTiles(int totalTiles){
		
		LinkedList<TilePiece> tilesToSend = new LinkedList<TilePiece>();
		
		for(int i = 0; i < totalTiles; i++){
			
			tilesToSend.add(iBag.iGamePieces.remove(0));
		}//end for loop
		
		return tilesToSend;
	}//end method	
}//end class
