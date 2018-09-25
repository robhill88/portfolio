package ProtocolLogic;

import Qwirkle.Qwirkle;

/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 *  
 * Protocol interface contains one method recieve protocl
 * 
 */

public interface Protocols {
		
	/**
	 *  Protocol data as String to be passed on to the corrisponding logic
	 * 
	 * @param qwirkle Qwirkle
	 * @param data String
	 */
	public void recieveProtocol(Qwirkle qwirkle, String data);
}
