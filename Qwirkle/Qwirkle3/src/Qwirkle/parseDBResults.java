package Qwirkle;

import ProtocolLogic.Protocols;

public class parseDBResults implements Protocols {

	

	public void recieveProtocol(Qwirkle qwirkle, String data) {
		
		outputResults(qwirkle, data);
	}
	
	private void outputResults(Qwirkle qwirkle, String data){
		
		String[] eachResult = data.substring(1, data.length()-1).split(", ");
		
		for(String result : eachResult){
			
			String[] splitResult =  result.split("\\:");
			
			int id = Integer.parseInt(splitResult[0]);
			String name = splitResult[1];
			int score = Integer.parseInt(splitResult[2]);
			
			System.out.println(id + " : " + name + " : " + score);			
		}
	}
}
