package model;

import java.util.ArrayList;

public class Tile {
	
	private int x;
	private int y;
	private ResourceType rsType;
	private int chipNumber;
	private ArrayList<BuildingLocation> buildingLocations;
	private ArrayList<StreetLocation> streetLocations;
	
	public Tile(int xCord, int yCord, ResourceType rsType, int chipNumber) {
		buildingLocations = new ArrayList<>();
		streetLocations = new ArrayList<>();
		this.x = xCord;
		this.y = yCord;
		this.rsType = rsType;
		this.chipNumber = chipNumber;	
	}
	
	public int getChipNumber() {
		return chipNumber;
	}

	public void setChipNumber(int chipNumber) {
		this.chipNumber = chipNumber;
	}

	public ArrayList<BuildingLocation> getBuildingLocations() {
		return buildingLocations;
	}
	
	public ArrayList<StreetLocation> getStreetLocations() {
		return streetLocations;
	}

	public void addBuildingLocation(BuildingLocation buildingLoc) {
		buildingLocations.add(buildingLoc);
	}
	
	public void addStreetLocation(StreetLocation streetLoc) {
		streetLocations.add(streetLoc);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public ResourceType getRsType() {
		return rsType;
	}
	public void setRsType(ResourceType rsType) {
		this.rsType = rsType;
	}
}