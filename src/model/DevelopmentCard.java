package model;

public class DevelopmentCard {

	private ResourceType[] cardCost;
	private ResourceType resourceType;
	protected ResourceType WOOL = ResourceType.WOOL;
	protected ResourceType IRON = ResourceType.IRON;
	protected ResourceType WHEAT = ResourceType.WHEAT;
	private Hand hand;

	// Constructor
	public DevelopmentCard() {
		hand = new Hand();
	}

	public void setCardCost(ResourceType type, ResourceType type2, ResourceType type3) {
		ResourceType wool = ResourceType.WOOL;
		cardCost = new ResourceType[] { type, type2, type3 };
		System.out.println(cardCost[0] + " " + cardCost[1] + " " + cardCost[2]);
	}

	public void addCard(DevelopmentCard type) {
		hand.addDevelopmentCard(type);
	}
}
