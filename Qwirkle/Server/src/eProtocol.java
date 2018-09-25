

public enum eProtocol {
	
	ID,
	START,
	NANE,
	ENDTURN;
	

	public String setProtocol(int id, eProtocol protocol, String data){
		
		return id + "$" + protocol + "$" + data;
	}
	
}
