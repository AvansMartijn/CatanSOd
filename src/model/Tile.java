package model;

public class Tile {
	private int idTile;
	private int x;
	private int y;
	private ResourceType rsType;
	private int chipNumber;
	
	public Tile(int idTile, int xCord, int yCord, ResourceType rsType, int chipNumber) {
		this.idTile = idTile;
		this.x = xCord;
		this.y = yCord;
		this.rsType = rsType;
		this.chipNumber = chipNumber;			
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
	public ResourceType getRsType() {
		return rsType;
	}
	public void setRsType(ResourceType rsType) {
		this.rsType = rsType;
	}
	
	
}
