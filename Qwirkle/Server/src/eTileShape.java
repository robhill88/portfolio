

public enum eTileShape {
	
	SQUARE(0),
	CIRCLE(1),
	DIAMOND(2),
	CLUB(3),
	STAR(4),
	SPADE(5);
	
	private int iValue;
	
	private eTileShape(int pValue){
		
		iValue = pValue;
	}
	
	public int getValue(){
		
		return iValue;
	}
}
