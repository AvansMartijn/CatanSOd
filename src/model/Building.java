package model;


public abstract class Building {

	protected int victoryPoint;
	protected Player player;
	
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
