package Enums;
/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 * Assigns a shape to the tile piece
 * Values used to instantiate the full tile piece
 * 
 */
public enum TileShape {
	
	SQUARE(0),
	CIRCLE(1),
	DIAMOND(2),
	CLUB(3),
	STAR(4),
	SPADE(5);
	
	private int value;
	
	/**
	 * set Tile Shape value as int
	 * @param value as int
	 */
	private TileShape(int value){
		
		this.value = value;
	}
	
	/**
	 * returns tile shape value as int
	 * @return value as int
	 */
	public int getValue(){
		
		return value;
	}
}
