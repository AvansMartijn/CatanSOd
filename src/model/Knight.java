package model;

public class Knight extends DevelopmentCard {
	
	private String developmentCardID;
	private boolean played;

	public Knight(String developmentCardID, boolean played) {
		this.developmentCardID = developmentCardID;
		this.played = played;
	}

}
