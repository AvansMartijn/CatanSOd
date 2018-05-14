package model;

public class StreetLocation {

	private BuildingLocation blStart;
	private BuildingLocation blEnd;
	private Player player;
//	private int xStart;
//	private int yStart;
//	
//	private int xEnd;
//	private int yEnd;
	
	

	public StreetLocation(BuildingLocation blStart, BuildingLocation blEnd) {
		
		this.blStart = blStart;
		this.blEnd = blEnd;
//		this.xStart = xStart;
//		this.yStart = yStart;
//		
//		this.xEnd = xEnd;
//		this.yEnd = yEnd;
		
	}

	public BuildingLocation getBlStart() {
		return blStart;
	}

	public void setBlStart(BuildingLocation blStart) {
		this.blStart = blStart;
	}

	public BuildingLocation getBlEnd() {
		return blEnd;
	}

	public void setBlEnd(BuildingLocation blEnd) {
		this.blEnd = blEnd;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
