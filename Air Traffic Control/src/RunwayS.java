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
public class RunwayS extends Agent{
	
	String planeID;
	boolean runwayAvailable = true;
	int runwayTimer = 5;


	//on agent setup
	protected void setup() {
		
		
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		
		ServiceDescription sd = new ServiceDescription();
		sd.setType("RunwayS");
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
	
	//Receiving message behaviour, runs on setup
	private class recieveMessage extends CyclicBehaviour{
		
		
		public void action() {
			
			ACLMessage msg = myAgent.receive();

			if(msg != null) {
				
				
				String content = msg.getContent();
				ACLMessage reply = msg.createReply();
				
				
				String tempParts [] = content.split("\\$");
				System.out.println(getAID().getName() + " 	Recieved	Protocol: " + tempParts[0] + " 		Performative: "  + ACLMessage.getPerformative(msg.getPerformative()) + " 		from " + msg.getSender().getLocalName());				
				
				
				
					//messaged received from air traffic control asking if the runway is available
					if(tempParts[0].equals(communicationProtocol.RUNWAYAVAILABLE.toString())) {
						
						reply.setContent(communicationProtocol.RUNWAYAVAILABLE.toString());

						//if run-way is available
						if(runwayAvailable = false) {
							
							reply.setPerformative(ACLMessage.DISCONFIRM);
						}
						else {
							
							reply.setPerformative(ACLMessage.CONFIRM);
						}
						
						myAgent.send(reply);		
					}//end if runway available
					
					//Message from tower for which plane will be incoming
					else if(tempParts[0].equals(communicationProtocol.PLANEINCOMING.toString())) {
						
						planeID = content;
						System.out.println(getAID().getLocalName() + " can see " + tempParts[1] + " INCOMING");
						runwayAvailable = false;
						
						//set ticket behaviour for time it takes for plane to land	
						addBehaviour(runwayInUse);
					}				
			
					else if(tempParts[0].equals(communicationProtocol.PLANEOUTGOING.toString())){
						
						
						reply.setContent(communicationProtocol.PLANEOUTGOING.toString());

						
						if(runwayAvailable = true) {
							
							reply.setPerformative(ACLMessage.CONFIRM);
							System.out.println(runwayAvailable);
						}
						else {
							
							reply.setPerformative(ACLMessage.DISCONFIRM);
						}
						myAgent.send(reply);		
					}
				}
			//block when message que is empty
			else {
				
				block();
			}
		}
	}
	
	
	
	//run-way in use for either take off or landing.
	Behaviour runwayInUse = new TickerBehaviour(this, 750) {
		
		int tempTimer = runwayTimer;
		
		protected void onTick() {
			
			System.out.println(getAID().getLocalName() + " i can see plane " + tempTimer );
			
			tempTimer  -= 1;
			
			if(tempTimer  == 0) {
							
				System.out.println("Plane Landed");
				message(communicationProtocol.LANDED.toString());
				removeBehaviour(runwayInUse);
				runwayAvailable = true;
			}
		}
	};
	
	
	
	Behaviour planeTackingOff = new TickerBehaviour(this, 750) {
		
		int tempTimer = runwayTimer;
		
		protected void onTick() {
			
			System.out.println(getAID().getLocalName() + " i can see plane Outgoing " + tempTimer);
			
			tempTimer  -= 1;
			
			if(tempTimer == 0) {
							
				System.out.println("Plane Successfully Tookoff");
				message(communicationProtocol.FLEWOFF.toString());
				removeBehaviour(planeTackingOff);
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
		}
}



