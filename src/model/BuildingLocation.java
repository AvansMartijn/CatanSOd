package model;

import java.util.ArrayList;

import view.BuildingLocationButton;

public class BuildingLocation {
	private int x;
	private int y;	
	private Harbour harbour;
	private Settlement settlement;

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
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public boolean isCity() {
		return isCity;
	}
	public void setCity(boolean isCity) {
		this.isCity = isCity;
	}
	private boolean isCity;
}
