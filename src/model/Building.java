package model;


public abstract class Building {

	protected String idBuilding;
	protected int victoryPoint;
	protected Player player;
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public String getIdBuilding() {
		return idBuilding;
	}
	public void setIdBuilding(String idBuilding) {
		this.idBuilding = idBuilding;
	}
	
}
