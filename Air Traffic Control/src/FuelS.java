import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class FuelS extends Agent {
	
	boolean available = false;
	int fuelTimer = 20;
	String planeInBay;
	
		
	//on agent setup
		protected void setup() {
			
			
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(getAID());
			
			ServiceDescription sd = new ServiceDescription();
			sd.setType("FuelS");
			sd.setName(getLocalName());
			sd.addLanguages("JADE");
			sd.addOntologies("refueling-ontology");
			sd.addProtocols("communicationProtocol");
			dfd.addServices(sd);
			
			
			System.out.println(getAID().getLocalName() + " agent setup.");	
			addBehaviour(new recieveMessage());
			
			
				try {
					DFService.register(this,  dfd);
				}
				catch(FIPAException fe) {
					
					fe.printStackTrace();
				}
		}//end setup
	
	
	private class recieveMessage extends CyclicBehaviour{
		
		public void action() {
			
			ACLMessage msg = myAgent.receive();
			
			if(msg != null) {
				
				String content = msg.getContent();
				ACLMessage reply = msg.createReply();
				
				String tempParts [] = content.split("\\$");
				
				System.out.println(getAID().getName() + " 	Recieved	Protocol: " + tempParts[0] + " 		Performative: "  + ACLMessage.getPerformative(msg.getPerformative()) + " 		from " + msg.getSender().getLocalName());				
				//System.out.println("Protocol = " + msg.getProtocol());
				
								
				if(tempParts[0].equals(communicationProtocol.FUELBAYAVAILABLE.toString())) {
					
					reply.setContent(communicationProtocol.FUELBAYAVAILABLE.toString());
					
					if(available == true) {
						
						reply.setPerformative(ACLMessage.CONFIRM);
						System.out.println(myAgent.getLocalName() + " : fuelBay is free!");
					}
					else {
						
						reply.setPerformative(ACLMessage.DISCONFIRM);
						System.out.println(myAgent.getLocalName() + " : fuelBay is Busy!");

					}
				
				myAgent.send(reply);
				}
				
				//Condition Plane Incoming, Outputs the visual representation, as it watches the refuel
				else if(tempParts[0].equals(communicationProtocol.PLANEINCOMING.toString())){
					
					addBehaviour(fuelBayInUse);
				}
				
				
				//Condition, run way is free, release the plane
				else if(tempParts[0].equals(communicationProtocol.RUNWAYAVAILABLE.toString())) {
					
					System.out.println(getAID().getLocalName() + " Waves at aeroplane to go to runway number: " + tempParts[1]);
					
					
				}
			}
		}
	}
	
	
	
	
	Behaviour fuelBayInUse = new TickerBehaviour(this, 750) {
		
		protected void onTick() {
			
			System.out.println(getAID().getLocalName() + " can see plane refueling " + fuelTimer);
			
			fuelTimer -= 1;
			
			if(fuelTimer == 0) {
				
				System.out.println("Plane Refueled");
				message(communicationProtocol.FUELFINISHED.toString());
				fuelTimer = 20;
				removeBehaviour(fuelBayInUse);
			}
		}
	};
	
	
	public void message(String content) {
		
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.addReceiver(new AID("ATC", AID.ISLOCALNAME));
		msg.setLanguage("english");
		msg.setOntology("airport-ontology");
		msg.setContent(content);
		msg.setProtocol("communicationProtocol");
		send(msg);
	}
}
