package model;

public abstract class DevelopmentCard {

	private Resource[] cardCost;
	public int developmentCardType;
	
	// Constructor
	public DevelopmentCard() {
		cardCost = new Resource[] {new Resource(ResourceType.GRAAN), new Resource(ResourceType.WOL), new Resource(ResourceType.ERTS)};
	}

	public int getDevelopmentCardType() {
		return developmentCardType;
	}

	public void getDevelopmentCardType(int getDevelopmentCardType) {
		this.developmentCardType = getDevelopmentCardType;
	}
	
}
