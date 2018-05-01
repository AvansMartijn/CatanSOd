package model;

import java.util.ArrayList;

public class Gameboard {
	private ArrayList<Tile> tileArr = new ArrayList<Tile>();
	//create buildinglocation array and streetlocation array to check if an object for a location is already made (to make sure we don't have duplicates)
	private ArrayList<BuildingLocation> buildingLocArr = new ArrayList<BuildingLocation>();
	private ArrayList<StreetLocation> streetLocArr = new ArrayList<StreetLocation>();

	public Gameboard(ArrayList<Tile> tileList, ArrayList<BuildingLocation> buildingList, ArrayList<StreetLocation> streetList) {
		this.tileArr = tileList;
		this.buildingLocArr = buildingList;
		this.streetLocArr = streetList;
	}
	
	//Outer array is the players, the Inner ArrayList is the actual resources the player gets. 
	public ArrayList<Resource>[] distributeResources(int roll) {
		//ArrayList<Resource>[] is an array of players (length 4) which contains an ArrayList of Resources
		return null; //TODO actual return value, this is just to fix the error. 
		// TODO Auto-generated method stub
		
	}
}