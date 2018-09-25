package Enums;

/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 * Game Mode enumerated Value
 * Values assigned to game mode are used for the game boards length and width
 * 
 */
public enum GameMode {
	
	SMALL(10),
	MEDIUM(15),
	LARGE(20);
	
	private int value;
	
	/**
	 * sets GameMode value taking int as parameter 
	 * @param pValue int
	 */
	private GameMode(int value){
		
		this.value = value;
	}
	
	/**
	 * returns GameMode value as Int
	 * @return int
	 */
	public int getValue(){
		
		return value;
	}
}
