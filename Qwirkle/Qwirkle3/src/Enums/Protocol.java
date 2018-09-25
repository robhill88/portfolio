package Enums;
/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 * Protocol identifiers, 
 * Used in the protocol messages, states what the server should do with the message
 * 
 */
public enum Protocol {
	
	ID,
	START,
	NAME,
	MOVEUPDATE,
	NEWCARDS,
	REPLACEMENT,
	ENDTURN,
	ENDGAME,
	GETHIGHSCORE,
	SETHIGHSCORE;
}
