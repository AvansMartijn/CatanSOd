package model;

public class BuildingLocation {
	private int x;
	private int y;	
	private Harbour harbour;
	
	
	public BuildingLocation(int x, int y, Harbour harbour) {
		this.x = x;
		this.y = y;
		this.harbour = harbour;
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
