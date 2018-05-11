package model;

public class Village extends Building {
	
	private BuildingLocation buildingLocation;
	
	public static final int VICTORY_POINTS = 1;
	
	public Village(PlayerColor color) {
		super(color);
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
