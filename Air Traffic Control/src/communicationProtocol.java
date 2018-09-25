
public enum communicationProtocol {
	
	ARRIVED, 				//Air craft has arrived
	CLEARTOLAND,            //lets the plane know they can lanbd       
	RUNWAYAVAILABLE,		//Check to see if runway is available, 
	LANDED,					//Air craft landed 
	FUELBAYAVAILABLE,		//Check to see if fuel bay is available
	PLANEINCOMING,			//Message to both supervisors informing plane incoming
	PLANEOUTGOING,			//message to both supervisors informing plane outgoing
	FLEWOFF,				//message from runway to ATC informing plane flew off
	RELEASEPLANE,			//ATC messages fuel bay to release plane
	FUELFINISHED;			//Plane has finished refueling 
}
