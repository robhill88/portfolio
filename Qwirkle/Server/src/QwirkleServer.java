
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

public class QwirkleServer extends Thread{
	
	Socket socket;
	Server server;
	ServerReader playerOne;
	ServerReader playerTwo;
	LinkedList<TilePiece> tileStack;
	LinkedList<TilePiece> playerOneHand;
	LinkedList<TilePiece> playerTwoHand;
	ArrayList<String> messageStack;
	DataOutputStream dataOutput;
	boolean run;
	boolean first;
	
	
	public QwirkleServer(Socket socket, Server server, ServerReader playerOne, ServerReader playerTwo){
		
		this.socket = socket;
		this.server = server;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		tileStack = new LinkedList<TilePiece>();
		playerOneHand = new LinkedList<TilePiece>();
		playerTwoHand = new LinkedList<TilePiece>();
		messageStack = new ArrayList<String>();
		run = true;
		first = true;
	}
	
	
	
	public void run(){
		
		String message = null;
		
		while(run){
			if(first == true){
				
				begin();
				first = false;
			}
			
			if(!messageStack.isEmpty()){
				
				message = messageStack.get(0);
				sendBack(message);
				messageStack.remove(0);
			}
			else{
				waitThread(this);
			}
		}
	}


	
	private void begin(){

		CreateTileBag tb = new CreateTileBag();
		tileStack = tb.createTiles(eGameMode.MEDIUM); 

		playerOneHand = tb.getTiles(tileStack, 6);
		playerTwoHand = tb.getTiles(tileStack, 6);
		
		addMessage((playerOne.getConnectionId() + "$START$" + playerOneHand));
		addMessage((playerTwo.getConnectionId() + "$START$" + playerTwoHand));
	}
	
	
	public synchronized void sendTo(String protocol){
		
		String[] protocolParts = protocol.split("\\$");
		int id = Integer.parseInt(protocolParts[0]);
		
		try{
			
			if((id + 2) % 2 == 0){
				
				System.out.println("Send To Player One Message: " + protocol);
				dataOutput = playerOne.getOutputStream();
				dataOutput.writeUTF(protocol);
				dataOutput.flush();
			}
			else{
				System.out.println("Send To Player Two Message: " + protocol);
				dataOutput = playerTwo.getOutputStream();
				dataOutput.writeUTF(protocol);
				dataOutput.flush();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	private synchronized void sendBack(String message){
		
		String[] messageParts = message.split("\\$");
		int id = Integer.parseInt(messageParts[0]);
		
		//TODO change this code 
		// save connections in an a data structure
		// if id == message parts id, send information to all other connections.
		
		try {

			if((id + 2) % 2 == 0){
				
				System.out.println("Send Back Message: " + message);
				dataOutput = playerTwo.getOutputStream();
				dataOutput.writeUTF(message);
				dataOutput.flush();

			}else{
				
				dataOutput = playerOne.getOutputStream();
				dataOutput.writeUTF(message);
				dataOutput.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private synchronized void waitThread(Thread thread){
		
		try{
			thread.wait();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public synchronized void addMessage(String message){

		messageStack.add(message);
		this.notify();
	}
}
