package model;

public abstract class DevelopmentCard {

	private ResourceType[] cardCost;
	public int developmentCardType;
	
	// Constructor
	public DevelopmentCard() {
		cardCost = new ResourceType[] {ResourceType.GRAAN, ResourceType.WOL, ResourceType.ERTS};
	}

	public int getDevelopmentCardType() {
		return developmentCardType;
	}

	public void getDevelopmentCardType(int getDevelopmentCardType) {
		this.developmentCardType = getDevelopmentCardType;
	}
	
}
