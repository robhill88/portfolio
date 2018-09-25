package ProtocolLogic;
import Qwirkle.Qwirkle;
import Qwirkle.Score;
import Qwirkle.ScorePanelDisplay;

/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 * Instantiates a new Score Panel and 
 * assigns both players names to their corresponding scores 
 * 
 */
public class Name implements Protocols {



	public void recieveProtocol(Qwirkle qwirkle, String data) {
		
		setPlayerNames(qwirkle, data);
	}

	
	/**
	 * Assigns both players names to their corresponding scores
	 * 
	 * 
	 * @param qwirkle Qwirkle
	 * @param data String
	 */
	private void setPlayerNames(Qwirkle qwirkle, String data){

		String player = qwirkle.getName();

		Score score1 = new Score(player, 0);
		Score score2 = new Score(data, 0);

		ScorePanelDisplay scorePanel = new ScorePanelDisplay(score1, score2);

		qwirkle.setScorePanel(scorePanel);
			
		//Game begins at this point, either the player will wait for the opposition or 
		//begin making their moves
		
		if(qwirkle.getMoveFirst().equals("true")){
			
			qwirkle.start();
		}
		else{
			
			System.out.println("Waiting on " + data + " to make their moves. ");
		}
	}
}
