package model;

import java.util.Vector;

public class Robber {

	
	Tile tile;
	Vector victims;
	Robber robber;
	public Robber() {
		tile = new Tile();
		victims = null;
	}

	public void setresourceType() {
		tile.setResourceType(null);
	}

	public Vector getVictims() {
		return victims;
	}

	public void setVictims(Vector victims) {
		this.victims = victims;
	}

	public Robber getRobber() {
		return robber;
	}

	public void setRobber(Robber robber) {
		this.robber = robber;
	}



}
