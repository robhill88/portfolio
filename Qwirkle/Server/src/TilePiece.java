

/**
 * Tile Piece class
 * @author RobHill
 * @version 0.1
 * @since 11/03/2017
 */
public class TilePiece {
	
	private eTileColour iColour;
	private eTileShape iShape;
	
	/**
	 *Tile piece constructor sets all instance variable values to null
	 */
	public TilePiece(){
		
		iColour = null;
		iShape = null;
	}//end constructor
	
		
	/**
	 * Set tile enum as parameter int
	 * @param pValue int
	 */
	public void setShape(int pValue){
		
		switch(pValue){
		
		case(0): iShape = eTileShape.CIRCLE;
			break;
			
		case(1): iShape = eTileShape.CLUB;
			break;
			
		case(2): iShape = eTileShape.DIAMOND;
			break;
		
		case(3): iShape = eTileShape.STAR;
			break;
		
		case(4): iShape = eTileShape.SPADE;
			break;
		
		case(5): iShape = eTileShape.SQUARE;
			break;
		
		default: System.out.print("Error: Out of bounds Shape Value");
		}//end case
	}//end set shape
	
	
	
	
	/**
	 * Set tile colour as parameter int
	 * @param pValue int
	 */
	public void setColour(int pValue){
		
		switch(pValue){
		
		case(0): iColour = eTileColour.BLUE;
			break;
			
		case(1): iColour = eTileColour.GREEN;
			break;
		
		case(2): iColour = eTileColour.ORANGE;
			break;
			
		case(3): iColour = eTileColour.PURPLE;
			break;
			
		case(4): iColour = eTileColour.RED;
			break;
			
		case(5): iColour = eTileColour.YELLOW;
			break;
			
		default: System.out.println("Error: Out of bounds Colour Value");
		}//End switch
	}//end set colour
	
	
	
	/**
	 * @return eTileShepe
	 */
	public eTileShape getShape(){
		
		return iShape;
	}
	
	/**
	 * @return eTileColour
	 */
	public eTileColour getColour(){
		
		return iColour;
	}
	
	
	public String toString(){
		
		return iColour.getValue() + ":" + iShape.getValue();
	}	
}//end class
