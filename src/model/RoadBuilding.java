package model;

public class RoadBuilding extends DevelopmentCard {
	
	private String developmentCardID;
	private boolean played;

	public RoadBuilding(String developmentCardID, boolean played) {
		this.developmentCardID = developmentCardID;
		this.played = played;
	}

}
