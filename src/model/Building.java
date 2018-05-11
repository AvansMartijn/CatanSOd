package model;

public abstract class Building {

	protected int victoryPoint;
	protected PlayerColor playerColor;
	
	public Building(PlayerColor playerColor) {
		this.playerColor = playerColor;
	}
}