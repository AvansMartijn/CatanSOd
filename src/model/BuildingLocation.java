package model;

import java.util.ArrayList;

public class BuildingLocation {
	private int x;
	private int y;	
	private Harbour harbour;
	private Building building;
	private ArrayList<StreetLocation> adjecentStreets;

	public BuildingLocation(int x, int y) {
		adjecentStreets = new ArrayList<StreetLocation>();
//		streetLocArr = new ArrayList<>();
		this.x = x;
		this.y = y;	
	}
	
	public int getXLoc() {
		return x;
	}
	
	public void setXLoc(int x) {
		this.x = x;
	}
	
	public int getYLoc() {
		return y;
	}
	
	public void setYLoc(int y) {
		this.y = y;
	}
	
	public Harbour getHarbour() {
		return harbour;
	}
	
	public void setHarbour(Harbour harbour) {
		this.harbour = harbour;
	}
	
	public City getCity() {
		if(building instanceof City) {
			return (City) building;
		}
		else {
			return null;
		}
	}
	
	public void setCity(City city) {
		this.building = city;
	}
	
	public Village getVillage() {
		if(building instanceof Village) {
			return (Village) building;
		}
		else {
			return null;
		}
	}
	
	public void setVillage(Village village) {
		this.building = village;
	}	
	
// 	public ArrayList<StreetLocation> getAdjacentStreets() {
	public ArrayList<StreetLocation> getAdjacentStreetLocations() {
		return adjecentStreets;
	}
	
	public void setAdjacentStreets(ArrayList<StreetLocation> adjecentStreets) {
		this.adjecentStreets = adjecentStreets;
	}
	
	public Building getBuilding() {
		return building;
	}

	public boolean hasBuilding() {
		if(building != null) {
			return true;
		}
		else {
			return false;
		}
	}
}