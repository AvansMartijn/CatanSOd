package model;

import java.util.ArrayList;

public class Account {
	
	private String username;
	private ArrayList<Player> players;
	
	public Account(ArrayList<Player> players) {
		this.players = players;
	}
	
	// Create a new player for this account
	public void createPlayer() {
		Player player = new Player(username); 
		players.add(player);
	}

	// Get username
	public String getUsername() {
		return username;
	}

	// Set username
	public void setUsername(String username) {
		this.username = username;
	}

	// get ArrayList of players
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
}
