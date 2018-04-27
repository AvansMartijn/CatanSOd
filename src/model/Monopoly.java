package model;

public class Monopoly extends DevelopmentCard {

	DevelopmentCard card;

	public Monopoly() {

		card = new DevelopmentCard();
		ResourceType iron = card.IRON;
		ResourceType wool = card.WOOL;
		ResourceType wheat = card.WHEAT;
		card.setCardCost(iron, wool, wheat);
	}

	public void checkPlayers() {

	}

	public void giveresources() {

	}

	public void takeresources() {

	}

}
