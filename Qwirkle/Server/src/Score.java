

/**
 * @author RobHill
 *
 */
public class Score{
	
	private String Name;
	private int Score;
	

	/**
	 * @param pUserName as String
	 * @param pScore as int
	 */
	public Score(String Name, int Score){
		
		this.Name = Name;
		this.Score = Score;
	}
	
	
	/**
	 * Set User 
	 * @param pUserName as String
	 */
	public void setPlayer(String Name){
		
		this.Name = Name;
	}
	
	
	/**
	 * @param pScore
	 */
	public void setScore(int Score){
		
		this.Score = Score;
	}
	
	
	/**
	 * @return
	 */
	public String getName(){
		
		return Name;
	}
	
	
	/**
	 * @return
	 */
	public int getScore(){
		
		return Score;
	}
}
