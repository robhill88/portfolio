import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	ServerSocket serverSocket;	
	ArrayList<ServerReader> connections = new ArrayList<ServerReader>();
	ArrayList<QwirkleServer> gameConnections = new ArrayList<QwirkleServer>();
		
	private boolean run = true;
	private int socketNumber = 6690;
	private int count = 0;
	
	public Server(){
		try{
			serverSocket = new ServerSocket(socketNumber);	
			while(run){
					
				Socket socket = serverSocket.accept();
				ServerReader serverReader = new ServerReader(socket, count);
				serverReader.start();
				connections.add(serverReader);
				count++;
			
				//System.out.println("Server Count = " + count);
				
				if(connections.size() > 1 && connections.size() % 2 == 0){
					
					System.out.println("Two Connections established");
					
					int playerOne = connections.size() - 2;
					int playerTwo = connections.size() - 1;
					
					//iConnectionsAL.get(playerOne).iDataOutput.writeUTF("CONNECTED$Connected With Player Two");
					//iConnectionsAL.get(playerTwo).iDataOutput.writeUTF("CONNECTED$Connected With Player One");
					
					QwirkleServer qwirkleServer = new QwirkleServer(socket, this, connections.get(playerOne), connections.get(playerTwo));
					qwirkleServer.start();
					connections.get(playerOne).setServer(qwirkleServer);
					connections.get(playerTwo).setServer(qwirkleServer);
					gameConnections.add(qwirkleServer);
				}
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
