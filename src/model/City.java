package model;

public class City extends Building{

	public static final int VICTORY_POINTS = 2;

	/** if buildingLocation is {@code null} it hasn't been built yet.*/
	private BuildingLocation buildingLocation;
	
	public City(PlayerColor playerColor) {
		super(playerColor);
		victoryPoint = VICTORY_POINTS;
	}

	public BuildingLocation getBuildingLocation() {
		return buildingLocation;
	}

	public void setBuildingLocation(BuildingLocation buildingLocation) {
		this.buildingLocation = buildingLocation;
	}
	
	public boolean isBuilt() {
		if(buildingLocation != null) {
			return true;
		}
		return false;
	}
}
