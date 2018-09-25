package Qwirkle;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author rob hill
 * @since 01/04/17
 * @version 0.3
 * 
 * Client Writer extends Thread 
 *  
 *  Writes data to the output stream and sends its to the server
 * 
 */
public class ClientWriter extends Thread{
	
	private Socket socket;
	private Boolean run = true;
	private ArrayList<String> messageStack;
	
	
	
	/**
	 * Instantiates new client Writer class taking socket as Socket and client as Client
	 * @param socket Socket
	 * @param client Client
	 */
	public ClientWriter(Socket socket, Client client){
		
		super("ClientWriter");
		this.socket = socket;
		messageStack = new ArrayList<String>();
	}
	
	
	
	
	/** 
	 * 
	 * Runs when Thread begins or is notfied
	 * runs until told otherwise
	 */
	public void run(){

		while(run){

			if(!messageStack.isEmpty()){

				try {

					DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
					//System.out.println(messageStack.get(0));

					dataOutput.writeUTF(messageStack.get(0));
					dataOutput.flush();
					messageStack.remove(0);

				} catch (IOException e) {
					e.printStackTrace();
				}

			}else{

				waitThread(this);
			} 
		}
	}




	
	/**
	 * Adds the protocol to the message stack 
	 * 
	 * @param message String 
	 */
	public synchronized void sendMessage(String message){

			messageStack.add(message);
			this.notify();
		}
	
	
	
	/**
	 * calls the thread to wait until further instructions
	 * 
	 * @param thread Thread
	 */
	private synchronized void waitThread(Thread thread){
		
		try {
			thread.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
