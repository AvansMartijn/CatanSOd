package model;

public class YearOfPlenty extends DevelopmentCard {

	DevelopmentCard card;
	public YearOfPlenty() {
		card = new DevelopmentCard();
		ResourceType iron = card.IRON;
		ResourceType wool = card.WOOL;
		ResourceType wheat = card.WHEAT;
		card.setCardCost(iron, wool, wheat);
	}
	
	
}
