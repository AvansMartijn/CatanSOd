package model;

public class City extends Building{

	private BuildingLocation buildingLocation;
	
	public City() {
		victoryPoint = 2;
	}

	public BuildingLocation getBuildingLocation() {
		return buildingLocation;
	}

	public void setBuildingLocation(BuildingLocation buildingLocation) {
		this.buildingLocation = buildingLocation;
	}	
}
