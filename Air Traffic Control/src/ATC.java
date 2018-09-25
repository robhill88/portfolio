import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class ATC extends Agent {
	
	String runway = "RunwayS";
	String fuelBay = "FuelS";
	String plane = "Aeroplane";
	
	int tempRunwayNum = 1;
	int tempFuelBayNum = 1;
	

	AID aeroplaneID;
	
	
	//on agent setup
	protected void setup() {
		
		
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		
		ServiceDescription sd = new ServiceDescription();
		sd.setType("ATC");
		sd.setName(getLocalName());
		sd.addLanguages("JADE");
		sd.addOntologies("refueling-ontology");
		sd.addProtocols("communicationProtocol");
		dfd.addServices(sd);
			
			try {
				DFService.register(this,  dfd);
			}
			catch(FIPAException fe) {
				
				fe.printStackTrace();
			}
		
		
		System.out.println(getAID().getLocalName() + " agent setup.");	
		addBehaviour(new recieveMessage());
	}//end setup
	
	
	//Receives message behaviour
	private class recieveMessage extends CyclicBehaviour{
		
		public void action() {
			
			ACLMessage msg = myAgent.receive();
			
			
			if(msg != null) {
				
				
				String content = msg.getContent();
				ACLMessage reply = msg.createReply();
				System.out.println(getAID().getName() + " 	Recieved	Protocol: " + content + " 		Performative: "  + ACLMessage.getPerformative(msg.getPerformative()) + " 		from " + msg.getSender().getLocalName());				
				
				
				
				
				//aeroplane arrives 
				if(content.equals(communicationProtocol.ARRIVED.toString())) {
						
						//sends a message to run-way asking if available
					
					
						message(fuelBay, communicationProtocol.FUELBAYAVAILABLE.toString());
						aeroplaneID = msg.getSender();
					}
				
				
				
				else if(content.equals(communicationProtocol.FUELBAYAVAILABLE.toString())){
					
						if(msg.getPerformative() == ACLMessage.CONFIRM) {
									
								//message(plane, communicationProtocol.FUELBAYAVAILABLE.toString());
								message(runway, communicationProtocol.RUNWAYAVAILABLE.toString());
							}
						else {
								
								System.out.println("Uh-oh fuel bay is busy need to add code to save the plane" );
							}
					}
					
				//Condition,  run-way is available
				else if(content.equals(communicationProtocol.RUNWAYAVAILABLE.toString())) {
						
						
						if(msg.getPerformative() == ACLMessage.CONFIRM) {
						
							//message run-way informing there will be an incoming plane and the id number
							reply.setContent(communicationProtocol.PLANEINCOMING.toString() + "$" + aeroplaneID.getLocalName().toString());
							myAgent.send(reply);
							
							String runwayAndFuelBayNum = Integer.toString(tempRunwayNum) + "$" + Integer.toString(tempFuelBayNum);
							message("Aeroplane", communicationProtocol.CLEARTOLAND.toString() + "$" + runwayAndFuelBayNum);
						}
						
						else {
							
							System.out.println("Uh-oh run wau is busy need to add code to save the plane" );
			
						}
					}//end run way availability communication
				
				
				
				//Condition, plane has landed, message fuel bay
				else if(content.equals(communicationProtocol.LANDED.toString())) {
						
						message(fuelBay, communicationProtocol.PLANEINCOMING.toString());
					}
				
				
				
				
				
				//Condition, re-fuelling has finished message to see if run way is free
				else if(content.equals(communicationProtocol.FUELFINISHED.toString())) {
					
						message(runway, communicationProtocol.PLANEOUTGOING.toString());	
				}
				
				
				
				
				//Condition, message the fuel bay to find whether the run way is free for outgoing
				else if(content.equals(communicationProtocol.PLANEOUTGOING.toString())) {
						
						//Run way is free message fuel bay
						if(msg.getPerformative() == ACLMessage.CONFIRM) {
							
							message(fuelBay, communicationProtocol.RUNWAYAVAILABLE.toString() + "$" + tempRunwayNum);
						}
						else {
							
							//insert code for runway unavailable when a plane is sat in the fuelbay
						}
				}
			}//end message if
			
			//block when message que is empty
			else {
				
				block();
			}
		}//end action 
	}//end receive message behaviour
	
	
	//function for formatting and sending message
	public void message(String reciever, String protocol) {
			
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.addReceiver(new AID(reciever, AID.ISLOCALNAME));
			msg.setLanguage("english");
			msg.setOntology("airport-ontology");
			msg.setContent(protocol);
			msg.setProtocol("communicationProtocol");
			send(msg);
	}// end message method
		
	
	protected void takeDown() {
		
		System.out.println("Air Traffic Control " + getAID().getName() + " Terminating");
	}// end take down method
}//end agent class
