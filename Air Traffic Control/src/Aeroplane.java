import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class Aeroplane extends Agent {
	
	int fuel = 52;	
	int fuelBayNum;
	int runwayNum;
	
	protected void setup() {
		
		
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Aeorplane");
		sd.setName(getLocalName());
		sd.addLanguages("JADE");
		sd.addOntologies("refueling-ontology");
		sd.addProtocols("communicationProtocol");
		dfd.addServices(sd);
		
		
		System.out.println(getAID().getLocalName() + " agent setup.");	
		addBehaviour(new recieveMessage());
		addBehaviour(fuelCounter);
		
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
					String[] tempParts = content.split("\\$");
					System.out.println(getAID().getName() + " 	Recieved	Protocol: " + content + " 		Performative: "  + ACLMessage.getPerformative(msg.getPerformative()) + " 		from " + msg.getSender().getLocalName());				

					
					if(tempParts[0].equals(communicationProtocol.CLEARTOLAND.toString())){
						
						removeBehaviour(fuelCounter);	
						fuelBayNum = Integer.parseInt(tempParts[1]);
						runwayNum = Integer.parseInt(tempParts[2]);
						
						fuel -= 15;
						addBehaviour(fuelBay);
					}
							

							
				//block when message que is empty
				else {
					
					block();
				}
			}
		}
	}
	
	
	
	
	Behaviour fuelCounter = new TickerBehaviour(this, 750) {
		
		protected void onTick() {
			
			fuel -=1;
			
			System.out.println(getAID().getLocalName() + " fuel levels = " +  fuel);
			
			if(fuel == 50) {
				
				message(communicationProtocol.ARRIVED.toString());
			}
			
			else if(fuel <= 0) {
				
				System.out.println("Mayday plane going down!");
				System.out.println("BOOM!");
				removeBehaviour(fuelCounter);
				takeDown();
			}
		}	
	};
	
	
	Behaviour fuelBay  = new TickerBehaviour(this, 750){
		

		int refueled = fuel +4;
		int tempCounter = 0;		//simulates landing timer
		
		protected void onTick() {
			
			tempCounter += 1;
				
			if(tempCounter > 5) {
			
				System.out.println("I am Re-fueling: " + fuel);
				fuel +=1;
				
					if(fuel == refueled) {
						
							System.out.println( "Refueled");
							removeBehaviour(fuelBay);
					}
			}
		}
	};


	//function for formatting and sending message
	public void message(String content) {
		
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.addReceiver(new AID("ATC", AID.ISLOCALNAME));
		msg.setLanguage("english");
		msg.setOntology("airport-ontology");
		msg.setContent(content);
		send(msg);
		//System.out.println("Action aeroplane message =  " + msg);
	}



	protected void takeDown() {
		
		System.out.println(getAID().getName() + " Terminating");
	}

}
		
