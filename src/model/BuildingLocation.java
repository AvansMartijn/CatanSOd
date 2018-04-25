package model;

import java.util.ArrayList;

public class BuildingLocation {
	private int x;
	private int y;	
	private Harbour harbour;
	private ArrayList<Tile> tileArr;
	
	
	public BuildingLocation(int x, int y, Tile tile1, Tile tile2, Tile tile3, Harbour harbour) {
		this.x = x;
		this.y = y;
		this.harbour = harbour;
		if(tile1 != null) {
			tileArr.add(tile1);
		}
		if(tile2 != null) {
			tileArr.add(tile2);
		}
		if(tile3 != null) {
			tileArr.add(tile3);
		}
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

	
}
