package model;

public class Monopoly extends DevelopmentCard {
	
	private String developmentCardID;
	private boolean played;

	public Monopoly(String developmentCardID, boolean played) {
		this.developmentCardID = developmentCardID;
		this.played = played;
	}

}
