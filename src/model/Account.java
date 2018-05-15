package model;

import java.util.ArrayList;

public class Account {

	private String username;
	private ArrayList<Player> players;
	private ArrayList<Catan> games;

	public Account(ArrayList<Player> players, String username) {
		this.players = players;
		this.username = username;
		games = new ArrayList<>();
	}

	// Create a new player for this account
	public void createPlayer() {
		Player player = new Player(username);
		players.add(player);
	}

	public void addGame(int idGame, String[] usernames, int[] followNrs) {
		games.add(new Catan(idGame, usernames, followNrs));
	}

	/**
	 * Get the Catan game with a certain idGame. Watch out that their exist no 2
	 * games with the same idGame, than this method is fucked.
	 * 
	 * @param idGame
	 *            the id of the game, as found in the database
	 * @return the Catan game with that id, if no such game exists returns
	 * 
	 */
	public Catan getGame(int idGame) {
		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).getIdGame() == idGame) {
				return games.get(i);
			}
		}
		// No game with this gameId exists in the model
		return null;
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