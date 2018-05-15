package model;

public class City extends Building{

	private BuildingLocation buildingLocation;
	
	public City(String dbidpiece, int dbx_from, int dby_from) {
		idBuilding = dbidpiece;
		x_from = dbx_from;
		y_from = dby_from;
		victoryPoint = 2;
	}

	public BuildingLocation getBuildingLocation() {
		return buildingLocation;
	}

	public void setBuildingLocation(BuildingLocation buildingLocation) {
		this.buildingLocation = buildingLocation;
	}	
	
	public boolean isBuild() {
		if(buildingLocation == null) {
			return true;
		}
		return false;
	}
}
