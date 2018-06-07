package model;

import java.util.ArrayList;

public class Gameboard {
	private ArrayList<Tile> tileArr = new ArrayList<Tile>();
	//create buildinglocation array and streetlocation array to check if an object 
	//for a location is already made (to make sure we don't have duplicates)
	private ArrayList<BuildingLocation> buildingLocArr = new ArrayList<BuildingLocation>();
	private ArrayList<StreetLocation> streetLocArr = new ArrayList<StreetLocation>();

	public Gameboard(ArrayList<Tile> tileList, ArrayList<BuildingLocation> buildingList, ArrayList<StreetLocation> streetList) {
		this.tileArr = tileList;
		this.buildingLocArr = buildingList;
		this.streetLocArr = streetList;
	}

	public ArrayList<Tile> getTileArr() {
		return tileArr;
	}
	
	public int getRobberIDTile() {
		for(Tile t: tileArr) {
			if(t.hasRobber()) {
				return t.getIdTile();
			}
		}
		return 0;
	}
	
	public Tile getRobberTile() {
		for(Tile tile: tileArr) {
			if(tile.hasRobber()) {
				return tile;
			}
		}
		//The robber always is somewhere, this is dead code 
		//just to fix the compilation error. 
		return null;
	}
	
	public void setRobber(int idTile) {
		for(Tile t: tileArr) {
			if(t.hasRobber()) {
				t.setRobber(false);
				break;
			}
		}
		for(Tile t: tileArr) {
			if(t.getIdTile() == idTile) {
				t.setRobber(true);
			}
		}
	}

	public ArrayList<BuildingLocation> getBuildingLocArr() {
		return buildingLocArr;
	}

	public ArrayList<StreetLocation> getStreetLocArr() {
		return streetLocArr;
	}
	

	
	
	
}