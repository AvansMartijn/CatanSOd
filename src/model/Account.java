package model;

import java.util.ArrayList;

public class Account {

	private String username;
	private ArrayList<Catan> games;

	public Account(String username) {
		this.username = username;
	}

	
	public void addGame(Catan catan) {
		games.add(catan);
	}

	public Catan getGame(int idGame) {
		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).getIdGame() == idGame) {
				return games.get(i);
			}
		}
		// No game with this gameId exists in the model
		return null;
	}
	
	public void setGame(ArrayList<Catan> games) {
		this.games = games;
	}

	// Get username
	public String getUsername() {
		return username;
	}

	// Set username
	public void setUsername(String username) {
		this.username = username;
	}
}