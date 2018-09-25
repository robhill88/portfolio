package Qwirkle;
import Enums.Destination;
import Enums.GameMode;
import Enums.Protocol;
import ProtocolLogic.Turn;

/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 * Qwirkle extends Thread
 *  
 *  Main class which runs the game
 * 
 */
public class Qwirkle extends Thread {
	
	private ScorePanelDisplay scorePanel;
	private Board board;
	private HandDisplay handDisplay;
	private TilePiece[] hand;
	private int connectionID;
	private String name;
	private boolean run = true;
	private Client client;
	private String moveFirst;	
	private GameMode gameMode;
	
	
	/**
	 * Instantiates new Qwirkle Game taking Game mode as parameter
	 * @param gameMode GameMode
	 */
	public Qwirkle(GameMode gameMode) {
		
		connectionID = 0;
		hand = new TilePiece[6];
		name = null;
		scorePanel = null;
		board = null;
		handDisplay = new HandDisplay();
		client = null;
		this.gameMode = gameMode; 
	}
	
	
	/** 
	 * Runs whilst the thread is active
	 * outputs the game to the user 
	 */
	public void run(){

		while(run){
			
			scorePanel.outputScore();
			board.displayBoard();
			handDisplay.displayHand();
			
			try {
				Turn.class.newInstance().playerTurn(this);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}			
		}
	}
	
	
	/**
	 * Sets whether the user will be going first or not
	 * Further development to restructure this and change it to a protocol 
	 * @param moveFirst String
	 */
	public void setMoveFirst(String moveFirst){
		
		this.moveFirst = moveFirst;
	}
	
	
	
	/**
	 * Returns whether the player will go first
	 * @return String
	 */
	public String getMoveFirst(){
		
		return moveFirst;
	}
	
	
	
	
	/**
	 * Sets the client allowing connection to the server
	 * @param client Client
	 */
	public void setClient(Client client){
		
		this.client = client;
	}
	
	
	
	/**
	 * 
	 * Returns client
	 * @return Client 
	 */
	public Client getClient(){
		
		return client;
	}
	
	
	
	/**
	 * Sets the players hand
	 * @param hand TilePiece[]
	 */
	public void setHand(TilePiece[] hand){

		this.hand = hand;
	}
	
	
	/**
	 * Returns player hand as TilePiece[]
	 * @return TilePiecep[]
	 */
	public TilePiece[] getHand(){

		return hand;
	}
	
	
	
	/**
	 * Returns current GameMode
	 * @return GameMode
	 */
	public GameMode getGameMode(){
		
		return gameMode;
	}
	
	
	/**
	 * Sets gameMode as GameMode
	 * @param gameMode GameMode
	 */
	public void setGameMode(GameMode gameMode){
		
		this.gameMode = gameMode;
	}
	
	
	
	
	/**
	 * Sets connectionID as int
	 * @param connectionID int
	 */
	public void setConnectionID(int connectionID){

		this.connectionID = connectionID;
	}

	
	/**
	 * Returns connectionId as int
	 * @return int connectionId
	 */
	public int getConnectionID(){

		return connectionID;
	}
	
	
	
	
	/**
	 * Sets playerName as String
	 * @param name String
	 */
	public void setPlayerName(String name){

		this.name = name;
	}

	
	/**
	 * Gets playerName as String
	 * @return String playerName
	 */
	public String getPlayerName(){

		return name;
	}
	
	
	
	
	/**
	 * Set board as Board
	 * @param board Board
	 */
	public void setBoard(Board board){
		
		this.board = board;
	}

	
	/**
	 * Returns board as Board
	 * @return Board board
	 */
	public Board getBoard(){

		return board;
	}
	
	
	
	
	/**
	 * Set Score Panel as ScorePanelDisplayer
	 * @param scorePanel
	 */
	public void setScorePanel(ScorePanelDisplay scorePanel){

		this.scorePanel = scorePanel;
	
	}
	
	/**
	 * Returns scorePanel as ScorePanelDisplay
	 * @return ScorePanelDisplay
	 */
	public ScorePanelDisplay getScorePanel(){
		
		return scorePanel;
	}
	
	
	
	
	
	/**
	 * @param handDisplay
	 */
	public void setHandDisplay(HandDisplay handDisplay){
		
		this.handDisplay = handDisplay;
	}
	
	/**
	 * Returns handDisplay as HandDisplay
	 * @return HandDisplay
	 */
	public HandDisplay getHandDisplay(){
		
		return handDisplay;
	}
	
	
	
	/**
	 * Sends Protocol to the client method send protocol
	 * 
	 * @param id int
	 * @param destination Destination
	 * @param identifier Protocol
	 * @param data String
	 */
	public void sendProtocol(int id, Destination destination, Protocol identifier, String data){
		
		client.sendProtocol(id, destination,  identifier, data);
	}
	
	
	
	/**
	 * Notify Thread to run
	 * @param thread Thread
	 */
	public synchronized void notifyThread(Thread thread){

		thread.notify();
	}
	
	
	/**
	 * Waits thread until further instructions
	 * @param thread Thread
	 */
	public synchronized void waitThread(Thread thread){

		try {
			thread.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
