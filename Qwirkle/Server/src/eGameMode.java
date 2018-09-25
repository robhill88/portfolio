

public enum eGameMode {
	
	SMALL(0),
	MEDIUM(1),
	LARGE(2);
	
	private int iValue;
	
	/**
	 * @param pValue int
	 */
	private eGameMode(int pValue){
		
		iValue = pValue;
	}
	
	/**
	 * @return int
	 */
	public int getValue(){
		
		return iValue;
	}
}
