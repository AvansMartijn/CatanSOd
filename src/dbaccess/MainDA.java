package dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.PlayStatus;
import model.Player;
import model.PlayerColor;
import model.ResourceType;
import model.Tile;

public class MainDA {
	private static final String url = "jdbc:mysql://databases.aii.avans.nl:3306/mfghaneg_db?useSSL=false";
	private static final String user = "mfghaneg";
	private static final String password = "Ab12345";
	protected Connection myConn;

	public MainDA() {
		myConn = null;
	}

	public boolean loadDataBaseDriver() {
		/**
		 * Loads the JDBC driver
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to load Database Driver");
			return false;
		}
		return true;
	}

	public void makeConnection() {
		/**
		 * Initializes a connection
		 */
		try {
			myConn = DriverManager.getConnection(url, user, password);
		} catch (SQLException ex) {
			System.out.println("Connection failed");
		}
	}

	public boolean insertUpdateQuery(String query) {
		/**
		 * Executes an insert or update query
		 */
		makeConnection();
		Statement stmt = null;
		try {
			stmt = myConn.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public void testQuery() {
		/**
		 * Test
		 */
		makeConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT * FROM account";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				String username = myRs.getString("username"); // Name of column
				String password = myRs.getString(2); // Number of column
				System.out.println(username + " pw: " + password);
			}
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int createGame(boolean randomBoard) {
		/**
		 * Create a game record in the Database
		 */
		int idGame = 0;

		makeConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT idspel FROM spel ORDER BY idspel DESC LIMIT 1";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				idGame = myRs.getInt(1) + 1;
			}
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			System.out.println("Unable get last game ID from DB");
		}
		String insertquery = "INSERT INTO spel (idspel, israndomboard, eersteronde)" + " VALUES(" + idGame + ", "
				+ randomBoard + ", " + true + ");";
		if (!insertUpdateQuery(insertquery)) {
			System.out.println("Adding game to DB failed");
		}
		return idGame;
	}

	public void addMessage(String username, String bericht) {
		/**
		 * Add a message to the Database
		 */
		int idPlayer = 0;

		makeConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String searchquery = "SELECT idspeler FROM speler WHERE username = '" + username + "' ORDER BY idspeler DESC LIMIT 1";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(searchquery);
			while (myRs.next()) {
				idPlayer = myRs.getInt(1);
			}
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			System.out.println("Unable to get last player ID");
		}
		
		String query = "INSERT INTO chatregel (idspeler, bericht)" + " VALUES (" + idPlayer + ", " + "'" + bericht + "'"
				+ ");";
		if (!insertUpdateQuery(query)) {
			System.out.println("Adding message to DB failed");
		}
	}

	public ArrayList<String> getMessages(int idGame) {
		/**
		 * Get all messages from the Database
		 */
		ArrayList<String> retList = new ArrayList<String>();
		makeConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT tijdstip, speler.username, bericht FROM chatregel "
				+ "JOIN speler ON chatregel.idspeler = speler.idspeler "
				+ "WHERE chatregel.idspeler IN(SELECT idspeler FROM speler WHERE idspel = " + idGame + ");";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				String tijdstip = myRs.getString(1);
				String username = myRs.getString(2);
				String bericht = myRs.getString(3);
				retList.add(tijdstip + " " + username + ": " + bericht);
			}
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.println("Failed to get messages from Database");
		}
		return retList;
	}

	public void addTile(int idGame, int idTile, int xCord, int yCord, ResourceType resource, int idChipNumber) {
		/**
		 * Add a Tile to the Database
		 */
		String query;
		if(idChipNumber == 0) {
			query = "INSERT INTO tegel (idspel, idtegel, x, y, idgrondstofsoort)" + " VALUES " + "("
					+ idGame + ", " + idTile + ", " + xCord + ", " + yCord + ", '" + resource.getResourceTypeCode() + "');";
		} else {
			query = "INSERT INTO tegel (idspel, idtegel, x, y, idgrondstofsoort, idgetalfiche)" + " VALUES " + "("
					+ idGame + ", " + idTile + ", " + xCord + ", " + yCord + ", '" + resource.getResourceTypeCode() + "', "
					+ idChipNumber + ");";
		}
		
		
		System.out.println(query);
		if(!insertUpdateQuery(query)) {
			System.out.println("Unable to add tile");
		};
	}
	
	public void addBuilding(String idPiece, int idPlayer, int x_From, int y_From) {
		/**
		 * Add a player piece
		 */
		String query = "INSERT INTO spelerstuk (idstuk, idspeler, x_van, y_van)" + " VALUES " + "('"
				+ idPiece + "' , " + idPlayer + ", " + x_From + ", " + y_From + ");";
		if(!insertUpdateQuery(query)) {
			System.out.println("Unable to add Building");
		};
		
	}

	public Tile getTile(int tileID) {
		/**
		 * Get a tile from the Database
		 */
		Tile returnTile = null;

		makeConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT x, y, idgrondstofsoort, idgetalfishe FROM tegel ;";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				int xCord = myRs.getInt(1);
				int yCord = myRs.getInt(2);
				ResourceType idResource = ResourceType.fromString(myRs.getString(3));
				int idChipNumber = myRs.getInt(4);
				returnTile = new Tile(xCord, yCord, idResource, idChipNumber);
			}
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return returnTile;
	}

	public void createAccount(String username, String wachtwoord) {
		/**
		 * Add an account to the Database
		 */
		String query = "INSERT INTO account (username, wachtwoord)" + " " + "VALUES (" + "'" + username + "'" + ", "
				+ "'" + wachtwoord + "'" + ");";
		insertUpdateQuery(query);
	}

	public boolean login(String username, String password) {
		/**
		 * Check if the account exists
		 */
		makeConnection();
		String wachtwoord = null;
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT wachtwoord FROM account " + "WHERE username = " + "'" + username + "'" + ";";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				wachtwoord = myRs.getString(1);
			}
			myRs.close();
			stmt.close();
			myConn.close();
			if (password.equals(wachtwoord)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void createPlayer(int idGame, String username, String playerColor, int followNR, String playStatus) {
		/**
		 * Add a player to the Database
		 */

		int idPlayer = 0;

		makeConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT idspeler FROM speler ORDER BY idspeler DESC LIMIT 1";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				idPlayer = myRs.getInt(1) + 1;
			}
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			System.out.println("Unable to get last player ID");
		}

		String insertquery = "INSERT INTO speler (idspeler, idspel, username, kleur, speelstatus, shouldrefresh, volgnr)"
				+ " " + "VALUES (" + idPlayer + ", " + idGame + ", '" + username + "', '" + playerColor + "', '"
				+ playStatus + "', " + false + ", " + followNR + ");";

		if (!insertUpdateQuery(insertquery)) {
			System.out.println("Adding player to DB failed");
		}
	}

	public int getLastPlayerFollowNumber(int idGame) {
		/**
		 * Returns the last used followNumber in the game
		 */
		Statement stmt = null;
		ResultSet myRs = null;
		int lastNR = 0;

		makeConnection();
		String followquery = "SELECT volgnr FROM speler WHERE idspel = '" + idGame + "' ORDER BY volgnr DESC LIMIT 1";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(followquery);
			while (myRs.next()) {
				lastNR = myRs.getInt(1);
			}
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			System.out.println("Unable to get last follownumber from database");
		}
		return lastNR;
	}

	public ArrayList<Player> getPlayers(String username) {
		/**
		 * Get all players from an account from the database
		 */
		ArrayList<Player> playerList = new ArrayList<Player>();

		makeConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT idspel, kleur, speelstatus, volgnr FROM speler WHERE username = '" + username + "';";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				int idGame = myRs.getInt(1);
				String color = myRs.getString(2).toUpperCase();
				String playStatus = myRs.getString(3).toUpperCase();
				int follownr = myRs.getInt(4);
				playerList.add(new Player(idGame, username, PlayerColor.valueOf(color), follownr,
						PlayStatus.valueOf(playStatus)));
			}
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			System.out.println("Unable to get players");
		}

		return playerList;

	}

	public boolean accountNameExists(String username) {
		/**
		 * Check if the account name exists
		 */
		makeConnection();
		String retusername = null;
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT username FROM account " + "WHERE username = " + "'" + username + "'" + ";";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				retusername = myRs.getString(1);
			}
			myRs.close();
			stmt.close();
			myConn.close();
			if (retusername == null) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Unable to check if username exists");
		}
		return false;
	}
}
