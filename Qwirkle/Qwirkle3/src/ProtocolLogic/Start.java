package ProtocolLogic;
import Qwirkle.HandDisplay;
import Qwirkle.Qwirkle;
import Qwirkle.TilePiece;
import Qwirkle.WelcomeScreen;

/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 * 
 * Extends Protocol class, Once two connections have been established the player enters their name
 *
 * Further development allow the player to enter the name before they connect
 * 
 */
public class Start implements Protocols {


	/* (non-Javadoc)
	 * @see ProtocolLogic.Protocols#recieveProtocol(Qwirkle.Qwirkle, java.lang.String)
	 */
	public void recieveProtocol(Qwirkle qwirkle, String data) {
		
		setPlayerHand(qwirkle, data);
		WelcomeScreen welcomeScreen = new WelcomeScreen();
		welcomeScreen.displayWelcomeScreen(qwirkle);
	}


	/**
	 * Assigns the player their starting hand splitting the data String with csv
	 * @param qwirkle Qwirkle
	 * @param data String
	 */
	private void setPlayerHand(Qwirkle qwirkle, String data){

		TilePiece[] hand = new TilePiece[6];
		String[] eachTile = data.substring(1, data.length()-1).split(", ");

		for(int i = 0; i < 6; i++){

			String[] TilePart = eachTile[i].split(":");
			TilePiece temp = new TilePiece();
			temp.setColour(Integer.parseInt(TilePart[0]));
			temp.setShape(Integer.parseInt(TilePart[1]));
			hand[i] = temp;
		}

		qwirkle.setHand(hand);

		HandDisplay handDisplay = new HandDisplay(hand);
		qwirkle.setHandDisplay(handDisplay);		
	}
}

