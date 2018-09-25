package Qwirkle;
import java.io.IOException;
import java.net.Socket;

import Enums.Destination;
import Enums.Protocol;

/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 * Client extends Thread
 * 
 * Establishes a connectiont to the games server
 *  
 * 
 */
public class Client extends Thread{

	//private String hostName = "192.168.0.3";
	private String hostName = "localhost";
	private int port = 6690;  	
	private ClientReader clientReader;
	private ClientWriter clientWriter;
	
	/**
	 * Instantiates new client taking Qwirkle as parameter
	 * 
	 * @param qwirkle Qwirkle
	 */
	
	
	public Client(Qwirkle qwirkle){
		
		try{
			
			Socket socket = new Socket(hostName, port);
			clientReader = new ClientReader(qwirkle, socket);
			clientWriter = new ClientWriter(socket, this);
			clientWriter.start();
			clientReader.start();
			

		}catch(IOException e){
			e.printStackTrace();
		}
	}
		
	/**
	 * Concatenates String creating the protocol to pass to the client writer
	 * 
	 * @param id int
	 * @param destination Destination
	 * @param identifier Protocol
	 * @param data String
	 */
	public void sendProtocol(int id, Destination destination, Protocol identifier, String data){
		
		String protocol = id + "$" + destination + "$" + identifier.toString() + "$" + data;
		clientWriter.sendMessage(protocol);
	}
	
	public ClientReader getClientRead(){
		
		return clientReader;
	}
}
