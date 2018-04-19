package model;

public abstract class DevelopmentCard {

	private Resource[] cardCost;
	
	// Constructor
	public DevelopmentCard() {
		ResourceType[] resourceTypes = ResourceType.values();
		cardCost = new Resource[] {null, null, null}; // TODO dit moet makkelijker kunnen dan met onderstaande methode
		
		String[] neededResources = new String[] {"WHEAT", "WOOL", "IRON"};
		
		// For the amount of different resources
		for(int i = 0; i < cardCost.length; i ++) {
			// For the amount of resourceTypes
			for(int j = 0; j < resourceTypes.length; j++) {
				if(resourceTypes[j].equals(neededResources[i])) {
					cardCost[i] = new Resource(resourceTypes[j]);
					break;
				}
			}
		}
	}
}
