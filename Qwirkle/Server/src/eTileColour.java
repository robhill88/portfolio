
/**
 * @author robhill
 *
 */
public enum eTileColour {
	
	GREEN(0),
	BLUE(1),
	RED(2),
	YELLOW(3),
	ORANGE(4),
	PURPLE(5);
	
	private int iValue;
	
	private eTileColour(int pValue){
		
		iValue = pValue;
	}
	
	public int getValue(){
		
		return iValue;
	}
}
