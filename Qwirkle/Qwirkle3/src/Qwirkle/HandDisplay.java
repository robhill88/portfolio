package Qwirkle;

/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 * Hand display
 *  
 *  Displays players hand to the user
 * 
 */
public class HandDisplay {
	
	private TilePiece[] hand = new TilePiece[6];
	
	
	/**
	 * Instantiates new Hand Display
	 */
	public HandDisplay(){
		
		hand = null;
	}
	
	
	
	/**
	 * Instantiates new Hand display using TilePiece[] as parameters
	 * 
	 * @param hand TilePiecep[]
	 */
	public HandDisplay(TilePiece[] hand){
		
		this.hand = hand;	
	}
	
	
	
	/**
	 * Outputs Hand to user
	 */
	public void displayHand(){
		
		System.out.println();
		System.out.println();
		System.out.print("\t\t\t");
		
		
		
		for(int f = 1; f <= 6; f++){
			
			System.out.print("     " + f + "    ");
		}
		
		System.out.println();
		System.out.print("\t\t\t");
		
		for(int i = 0; i < 6; i++){
			
			if(hand[i] == null){
				
				System.out.print("|         ");
			}
			else{
				
				System.out.print("|  " +  hand[i].toString() + "  ");
			}
		}
		
		System.out.print("|");
		System.out.println();
		System.out.println();
	}
	
	
	
	/**
	 * Returns hand as TilePiece[]
	 * 
	 * @return TilePiece[]
	 */
	public TilePiece[] getHand(){
		
		return this.hand;
	}
	
	
	/**
	 * Sets Player hand with TilePiece[] as parameter
	 * 
	 * @param hand TilePiece[]
	 */
	public void setHand(TilePiece[] hand){
		
		this.hand = hand;
	}
}
