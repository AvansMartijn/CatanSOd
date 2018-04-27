package model;

public class VictoryPoint extends DevelopmentCard {

	DevelopmentCard card;
	public VictoryPoint() {
		card = new DevelopmentCard();
		ResourceType iron = card.IRON;
		ResourceType wool = card.WOOL;
		ResourceType wheat = card.WHEAT;
		card.setCardCost(iron, wool, wheat);
	}
	
}
