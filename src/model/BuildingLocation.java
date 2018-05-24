package model;

import java.util.ArrayList;

import view.BuildingLocationButton;

public class BuildingLocation {
	private int x;
	private int y;	
	private Harbour harbour;
	private City city;
	private Village village;
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
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Village getVillage() {
		return village;
	}
	public void setVillage(Village village) {
		this.village = village;
	}	
	public ArrayList<StreetLocation> getAdjacentStreets() {
		return adjecentStreets;
	}
	public void setAdjacentStreets(ArrayList<StreetLocation> adjecentStreets) {
		this.adjecentStreets = adjecentStreets;
	}	

}
