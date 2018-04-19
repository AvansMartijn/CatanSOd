package model;

public class Village extends Building {
	
	private BuildingLocation buildingLocation;
	
	
	
	public Village() {

		victoryPoint = 1;
		
	}

	public BuildingLocation getBuildingLocation() {
		return buildingLocation;
	}

	public void setBuildingLocation(BuildingLocation buildingLocation) {
		this.buildingLocation = buildingLocation;
	}
}
