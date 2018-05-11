package model;

import java.util.ArrayList;

public class BuildingLocation {
	private int x;
	private int y;	
	private Harbour harbour;
	private ArrayList<StreetLocation> streetLocations;
	
	public BuildingLocation(int x, int y) {
		streetLocations = new ArrayList<>();
		this.x = x;
		this.y = y;
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
	
	public Harbour getHarbour() {
		return harbour;
	}
	
	public void setHarbour(Harbour harbour) {
		this.harbour = harbour;
	}

	public ArrayList<StreetLocation> getStreetLocations() {
		return streetLocations;
	}
}
