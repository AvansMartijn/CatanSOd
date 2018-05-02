package model;

public abstract class DevelopmentCard {

	private Resource[] cardCost;
	
	// Constructor
	public DevelopmentCard() {
		cardCost = new Resource[] {new Resource(ResourceType.WHEAT), new Resource(ResourceType.WOOL), new Resource(ResourceType.IRON)};
	}
}
