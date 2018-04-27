package model;

public class Knight extends DevelopmentCard {
	DevelopmentCard card;
	public Knight() {
		card = new DevelopmentCard();
		ResourceType iron = card.IRON;
		ResourceType wool = card.WOOL;
		ResourceType wheat = card.WHEAT;
		card.setCardCost(iron, wool, wheat);
	}
	

	
}
