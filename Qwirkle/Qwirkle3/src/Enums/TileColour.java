package Enums;


/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 * Assigns a colour to the tile piece
 * values are used to instantiate the full tile piece
 * 
 */
public enum TileColour {
	
	GREEN(0),
	BLUE(1),
	RED(2),
	YELLOW(3),
	ORANGE(4),
	PURPLE(5);
	
	private int value;
	
	/**
	 * set tile colour value using int as parameter
	 * @param pValue int
	 */
	private TileColour(int value){
		
		this.value = value;
	}
	
	/**
	 * returns tile colour value as int
	 * @return value as int
	 */
	public int getValue(){
		
		return value;
	}
}
