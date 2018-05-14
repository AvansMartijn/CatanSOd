package model;

import java.util.ArrayList;

import view.BuildingLocationButton;

public class BuildingLocation {
	private int x;
	private int y;	
	private Harbour harbour;
	private Settlement settlement;
	private boolean isCity;
	private Player player;
	

	
	
	public BuildingLocation(int x, int y) {
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
	public Settlement getSettlement() {
		return settlement;
	}
	public void setSettlement(Settlement settlement) {
		this.settlement = settlement;
	}
	
}
