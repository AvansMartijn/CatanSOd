package model;

import java.util.ArrayList;

public class Account {
	
	private String username;
	private String password;
	private ArrayList<Player> players;
	
	// Constructor
	public Account() {
		players = new ArrayList<Player>();
	}
	
	// Create a new player for this account
	public void createPlayer() {
		Player player = new Player(players.size()); // player.size() will assign the playerNr
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

	// Get Password
	public String getPassword() {
		return password;
	}

	// Set Password
	public void setPassword(String password) {
		this.password = password;
	}

	// get ArrayList of players
	public ArrayList<Player> getPlayers() {
		return players;
	}
}
