package model;

import java.util.ArrayList;

import view.TileButton;

public class Tile {
	private int idTile;
	private int x;
	private int y;
	private ResourceType rsType;
	private int chipNumber;
	private ArrayList<BuildingLocation> buildingLocArr;
	private ArrayList<StreetLocation> streetLocArr;
	

	public Tile(int idTile, int xCord, int yCord, ResourceType rsType, int chipNumber) {
		this.idTile = idTile;
		buildingLocArr = new ArrayList<>();
		streetLocArr = new ArrayList<>();
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

	public ArrayList<BuildingLocation> getBuildingLocArr() {
		return buildingLocArr;
	}
	
	public ArrayList<StreetLocation> getStreetLocArr() {
		return streetLocArr;
	}

	public void addBuildingLoc(BuildingLocation buildingLoc) {
		buildingLocArr.add(buildingLoc);
	}
	
	public void addStreetLoc(StreetLocation streetLoc) {
		streetLocArr.add(streetLoc);
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

	public int getIdTile() {
		return idTile;
	}

	public void setIdTile(int idTile) {
		this.idTile = idTile;
	}

	
}
