package Qwirkle;
/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 * ScorePanelDisplay outputs score panel to the player
 *  
 * 
 */
public class ScorePanelDisplay {
	
	private Score playerOne;
	private Score playerTwo;
	
	
	/**
	 * Instantiates new ScoreDisplayPanel with playerOne as Score and playerTwo as Score
	 * @param playerOne Score
	 * @param playerTwo Score
	 */
	public ScorePanelDisplay(Score playerOne, Score playerTwo){
		
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
	}
	
	/**
	 * outputs the panel to the player
	 */
	public void outputScore(){
	
		System.out.print("\t\t\t\t\t");
		System.out.println(playerOne.getName() + ": " + playerOne.getScore() + " | " + playerTwo.getScore() + " :" + playerTwo.getName());	
		System.out.println();
		System.out.println();
	}
	
	/**
	 * Returns playerOne as Score
	 * @return Score playerOne
	 */
	public Score getPlayerOneScore(){
		
		return playerOne;
	}
	
	/**
	 * Returns playerTwo as Score
	 * @return Score playerTwo
	 */
	public Score getPlayerTwoScore(){

		return playerTwo;
	}
}
