package Qwirkle;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import Enums.GameMode;
import Enums.Protocol;
import ProtocolLogic.*;
/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 * Client Reader extends Thread
 *  
 * 
 * Reads data from the input stream passed by the server
 *  
 */
public class ClientReader extends Thread{

	private Socket socket;
	private Qwirkle qwirkle;
	private DataInputStream dataInput;
	private boolean run;
	private boolean first;
	

	/**
	 * Instantiates new Client Reader with parameters qwirkle as Qwirkle and socket as Socket
	 * 
	 * @param socket Socket
	 * @param qwirkle Qwirkle
	 */
	public ClientReader(Qwirkle qwirkle, Socket socket){

		this.socket = socket;
		this.qwirkle = qwirkle;
		run = true;
		first = true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run(){

		while(run){
			try {
				
				dataInput = new DataInputStream(socket.getInputStream());
				String protocol = dataInput.readUTF();
				System.out.println(protocol);
				String protocolParts[] = protocol.split("\\$");
				//System.out.println(ProtocolParts[1]);
				int id = Integer.parseInt(protocolParts[0]);
				Protocol identifier = Protocol.valueOf(protocolParts[2]);
				String data = protocolParts[3];
				System.out.println(identifier);
				
				if(first){

					qwirkle.setConnectionID(id);
					System.out.println("ID = " + qwirkle.getConnectionID());
					first = false;
					Board board = new Board(GameMode.MEDIUM);
					qwirkle.setBoard(board);
						
				}
				

				if(identifier.equals(Protocol.START)){

					//System.out.println(ProtocolParts[2]);	
					try {
						
						qwirkle.setMoveFirst(protocolParts[4]);
						
						Start.class.newInstance().recieveProtocol(qwirkle, data);
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}		
				}

				else if(identifier.equals(Protocol.NAME)){

					//System.out.println("Name Recieved " + Protocol);
					try {
						
						Name.class.newInstance().recieveProtocol(qwirkle, data);
					
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}	
				}
				
				else if(identifier.equals(Protocol.GETHIGHSCORE)){
					
					try {
						
						parseDBResults.class.newInstance().recieveProtocol(qwirkle, data);
					
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}

				else if(identifier.equals(Protocol.MOVEUPDATE)){

					try{
						
						MoveUpdate.class.newInstance().recieveProtocol(qwirkle, data);
					
					}catch(InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}			
				}

				else if(identifier.equals(Protocol.NEWCARDS)){

					try{
						
						NewCards.class.newInstance().recieveProtocol(qwirkle, data);
					
					}catch(InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}	
				}

				else if(identifier.equals(Protocol.REPLACEMENT)){

					//TODO further development for when the player wants to swap some of their cards
				}
				
				else if(identifier.equals(Protocol.ENDGAME)){
					
					//TODO further development for when a player plays all their tiles
				}

				else if(identifier.equals(Protocol.ENDTURN)){

					if(!qwirkle.isAlive()){

						qwirkle.start();
					}
					else{

						qwirkle.notifyThread(qwirkle);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				close();
			}
		}	
	}


	/**
	 * closes any streams if any errors occur
	 */
	public void close(){

		try {
			dataInput.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}