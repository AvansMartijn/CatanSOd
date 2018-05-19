package model;

public class VictoryPoint extends DevelopmentCard {
	
	private String developmentCardID;
	private boolean played;

	public VictoryPoint(String developmentCardID, boolean played) {
		this.developmentCardID = developmentCardID;
		this.played = played;
	}

}
