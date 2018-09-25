package ProtocolLogic;

import Qwirkle.Qwirkle;
import Qwirkle.TilePiece;

/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 * Receives new cards as a string 
 * splits the String and instantiates new Tile Pieces
 * assigns null elements the new tiles
 * 
 */
public class NewCards implements Protocols {


	
	public void recieveProtocol(Qwirkle qwirkle, String data) {

		replaceCards(qwirkle, data);
	}


	/**
	 * Replaces null elements with new tile pieces
	 * 
	 * @param qwirkle Qwirkle
	 * @param data String
	 */
	private void replaceCards(Qwirkle qwirkle, String data){
		
		String[] eachTile = data.substring(1, data.length()-1).split(", ");

		TilePiece temp = new TilePiece(); 
		int count = 0;

		for(int i = 0; i < 6; i++){

			if(qwirkle.getHand()[i] == null){

				if(eachTile[i] != null){

					String[] splitTile = eachTile[count].split("\\:");
					temp.setColour(Integer.parseInt(splitTile[0]));
					temp.setShape(Integer.parseInt(splitTile[1]));
					qwirkle.getHand()[i] = temp;
					count++;
				}
				else{
					
					break;
				}
			}
		}
	}
}
