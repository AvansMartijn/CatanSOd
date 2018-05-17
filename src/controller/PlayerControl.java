package controller;

import model.Bank;
import model.Catan;
import model.City;
import model.Hand;
import model.Player;
import model.Resource;
import model.ResourceType;
import model.Tile;
import model.Village;

public class PlayerControl {

	Player player;
//	Robber robber;
	Village village;
	City city;
	Catan catan;
	Hand hand;
	Bank bank;
	Resource resource;
	ResourceType type;

	public PlayerControl() {

	}

	// get one victory point
	public void victoryPoint() {
		player.setOverwinningspunten(1);

	}

	// player can claim all resource cards of a specific declared type from all the
	// players
	public void monopoly(Resource resource, Player nummer) {
//		for (int i = 0; i < catan.getPlayers().length; i++) {
//			for (int j = 0; j < hand.getResources().size(); j++) {
//				if (hand.getResources().get(j).equals(resource)) {
//					// TODO geef de speler zijn resource van de andere speler.
//				}
//			}
//		}
	}

	// may put two streets on the board of choice
	public void roadBuilding() {
		// TODO ik weet niet hoe het moet maar succes
	}

	// move the robber to a new destination
	public void knight(Tile tile) {
//		robber.setOnTile(tile);
		for (int i = 0; i < tile.getBuildingLocArr().size(); i++) {
			// TODO persoon moet door de array van building locations gaan van de tile waar
			// de robber op staat en dan kijken hoeveel spelers er aan grenzen en dan een
			// speler kiezen en een grondstof random pakken

		}
	}

	// may take two resources from the bank
	public void yearOfPlenty() {
		// TODO de bank moet nog gemakt worden.
	}

}
