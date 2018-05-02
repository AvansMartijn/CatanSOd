package model;

public abstract class DevelopmentCard {

	private Resource[] cardCost;
	
	// Constructor
	public DevelopmentCard() {
		cardCost = new Resource[] {new Resource(ResourceType.GRAAN), new Resource(ResourceType.WOL), new Resource(ResourceType.ERTS)};
	}
}
