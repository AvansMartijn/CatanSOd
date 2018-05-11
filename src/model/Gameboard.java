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
	
	/**
	 * Test Method. This method should not be used in the final product. <br>
	 * Deprecated just to make it stand out, so it won't be used in the final product. 
	 * <p>
	 * JavaDoc added by Jasper Mooren
	 */
	@Deprecated
	public void printAllTilesAndLocs() {
		for (Tile tile : tileArr) {
			System.out.println("---------TILE-------------");
			System.out.println("resource: " + tile.getRsType());
			System.out.println("chip number: " + tile.getChipNumber());
			System.out.println("loc_x: " + tile.getX());
			System.out.println("loc_y: " + tile.getY());
			
			for (BuildingLocation bl : tile.getBuildingLocations()) {
				System.out.println("------BUILDING LOC---------");
				System.out.println("loc_x: " + bl.getX());
				System.out.println("loc_y: " + bl.getY());
				
				if(bl.getHarbour() != null) {
					System.out.println("harbour: " + bl.getHarbour().getRsType());
				}
				
			}
			
			for(StreetLocation sl : tile.getStreetLocations()) {
				System.out.println("------STREET LOC---------");
				System.out.println("start: " + sl.getBlStart().getX() + " " + sl.getBlStart().getY());
				System.out.println("end: " + sl.getBlEnd().getX() + " " + sl.getBlEnd().getY());
				
				
			}
			System.out.println("----------------------------");
			System.out.println("");
			System.out.println("");
		} 
	}
	
}