import java.net.*;
import java.io.*;

public class ServerReader extends Thread {
	
	private Socket socket;
	private QwirkleServer server;
	private DataInputStream dataInput;
	private DataOutputStream dataOutput;
	private int connectionId;
	private boolean iRun = true;
	

	public ServerReader(Socket socket, int connectionId){
		
		super("ServerConnectionThread");
		this.socket = socket;
		this.connectionId = connectionId;
		this.server = null;
		
		try {
			dataOutput = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){

		while(iRun){

			try {
				dataInput = new DataInputStream(socket.getInputStream());
				String message = dataInput.readUTF();
				String messageParts[] = message.split("\\$");

				if(messageParts[1].equals(eProtocol.valueOf("START"))){
					
					System.out.println("Server START Protocol: " + message);
					server.sendTo(message);	
				}
				
				else if(messageParts[1].equals(eProtocol.valueOf("NAME"))){
					
					System.out.println("Server Name Protocol: " + message);
					server.sendTo(message);
				}
				
				else if(messageParts[1].equals(eProtocol.valueOf("ENDTURN"))){
					
					System.out.println("Server ENDTURN Protocol: " + message);
				}
				
				

			} catch (IOException e){
				e.printStackTrace();				
			}
		}
	}
	
	public int getConnectionId(){
		
		return connectionId;
	}
	
	public DataOutputStream getOutputStream(){
		
		return dataOutput;
	}
	
	public void setServer(QwirkleServer server){
		
		this.server = server;
	}
}