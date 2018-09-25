package Qwirkle;


/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 * Score for a player contains name as String and score as int
 *  
 * 
 */
public class Score{
	
	private String name;
	private int score;
	

	/**
	 * Instantiates new Score Object with name as String and score as int 
	 * @param userName as String
	 * @param score as int
	 */
	public Score(String name, int score){
		
		this.name = name;
		this.score = score;
	}
	
	
	/**
	 * Set Players Name as String
	 * @param userName String
	 */
	public void setPlayer(String name){
		
		this.name = name;
	}
	
	
	/**
	 * Set players score as int
	 * @param score int
	 */
	public void setScore(int score){
		
		this.score = score;
	}
	
	
	/**
	 * Return players name as String
	 * @return name String
	 */
	public String getName(){
		
		return name;
	}
	
	
	/**
	 * Return players score as int
	 * @return score int
	 */
	public int getScore(){
		
		return score;
	}
}