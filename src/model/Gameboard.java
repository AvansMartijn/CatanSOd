package model;

import java.util.ArrayList;

public class Gameboard {
	private ArrayList<BuildingLocation> buildingLocationArr = new ArrayList<BuildingLocation>();
	private ArrayList<StreetLocation> streetLocationArr = new ArrayList<StreetLocation>();
	private ArrayList<Tile> tileArr = new ArrayList<Tile>();
	

	//Outer array is the players, the Inner ArrayList is the actual resources the player gets. 
	public ArrayList<Resource>[] distributeResources(int roll) {
		//ArrayList<Resource>[] is an array of players (length 4) which contains an ArrayList of Resources
		return null; //TODO actual return value, this is just to fix the error. 
		// TODO Auto-generated method stub
		
	}
}