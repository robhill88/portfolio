package Qwirkle;
import Enums.TileColour;
import Enums.TileShape;

/**
 * 
 * @author RobHill
 * @version 0.1
 * @since 11/03/2017
 * 
 * TilePiece instantiates from enum TileColour and enum TileShape
 */

public class TilePiece {

	private TileColour colour;
	private TileShape shape;

	/**
	 *Tile piece constructor sets all instance variable values to null
	 */
	public TilePiece(){

		colour = null;
		shape = null;
	}//end constructor


	/**
	 * Set enum value TileShape as parameter int
	 * @param value int
	 */
	public void setShape(int value){

		switch(value){

		case(0): shape = TileShape.SQUARE;
		break;

		case(1): shape = TileShape.CIRCLE;
		break;

		case(2): shape = TileShape.DIAMOND;
		break;

		case(3): shape = TileShape.CLUB;
		break;

		case(4): shape = TileShape.STAR;
		break;

		case(5): shape = TileShape.SPADE;
		break;

		default: System.out.print("Error: Out of bounds Shape Value");
		}//end case
	}//end set shape


	
	
	/**
	 * Set enum TileColour as parameter int
	 * @param value int
	 */
	public void setColour(int value){
		
		switch(value){
		
		case(0): colour = TileColour.GREEN;
			break;
			
		case(1): colour = TileColour.BLUE;
			break;
		
		case(2): colour = TileColour.RED;
			break;
			
		case(3): colour = TileColour.YELLOW;
			break;
			
		case(4): colour = TileColour.ORANGE;
			break;
			
		case(5): colour = TileColour.PURPLE;
			break;
			
		default: System.out.println("Error: Out of bounds Colour Value");
		}//End switch
	}//end set colour
	
	
	
	/**
	 * Returns shape as TileShape
	 * @return shape TileShape
	 */
	public TileShape getShape(){
		
		return shape;
	}
	
	/**
	 * Returns colour as TileColour
	 * @return colour
	 */
	public TileColour getColour(){
		
		return colour;
	}
	
	
	/** 
	 * Returns the first 2 characters from both TileShape and TileColour as String to Create the full tile
	 */
	public String toString(){
		
		return colour.name().substring(0, 2) + ":" + shape.name().substring(0, 2);
	}
	
	/**
	 * Returns the tiles shape and colour values int values as String
	 * @return String
	 */
	public String toInt(){
		
		return colour.getValue() + "," + shape.getValue();
	}
}//end class
