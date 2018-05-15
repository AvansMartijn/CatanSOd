package model;

public class Village extends Building {
	
	private BuildingLocation buildingLocation;
	
	public Village(String dbidpiece) {
		idBuilding = dbidpiece;
		victoryPoint = 1;
	}

	public BuildingLocation getBuildingLocation() {
		return buildingLocation;
	}

	public void setBuildingLocation(BuildingLocation buildingLocation) {
		this.buildingLocation = buildingLocation;
	}
	
	public boolean isBuild() {
		if(buildingLocation == null) {			
			return false;
		}
		return true;
	}
}
