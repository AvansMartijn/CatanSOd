package model;

public class RoadBuilding extends DevelopmentCard {

	
	DevelopmentCard card;
	public RoadBuilding() {
		card = new DevelopmentCard();
		ResourceType iron = card.IRON;
		ResourceType wool = card.WOOL;
		ResourceType wheat = card.WHEAT;
		card.setCardCost(iron, wool, wheat);
	}
	
}
