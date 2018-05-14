package dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.BuildingLocation;
import model.PlayStatus;
import model.Player;
import model.PlayerColor;
import model.ResourceType;
import model.StreetLocation;
import model.Tile;

public class MainDA {
	private static final String url = "jdbc:mysql://databases.aii.avans.nl:3306/mfghaneg_db?useSSL=false";
	private static final String user = "mfghaneg";
	private static final String password = "Ab12345";
	protected Connection myConn;

	public MainDA() {
		myConn = null;
	}

	/**
	 * Loads the JDBC driver
	 */
	public boolean loadDataBaseDriver() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to load Database Driver");
			return false;
		}
		return true;
	}

	/**
	 * Initializes a connection
	 */
	public void makeConnection() {

		try {
			myConn = DriverManager.getConnection(url, user, password);
		} catch (SQLException ex) {
			System.out.println("Connection failed");
		}
	}

	/**
	 * Executes an insert or update query
	 */
	public boolean insertUpdateQuery(String query) {

		makeConnection();
		Statement stmt = null;
		try {
			stmt = myConn.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Test
	 */
	public void testQuery() {

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

	/**
	 * Create a game record in the Database
	 */
	public int createGame(boolean randomBoard) {

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

	public int getPlayerID(String username, int idGame) {
		int idPlayer = 0;
		makeConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String searchquery = "SELECT idspeler FROM speler WHERE username = '" + username
				+ "' AND idspel = " + idGame + " ORDER BY idspeler DESC LIMIT 1";	
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
		return idPlayer;
	}
	
	/**
	 * Add a message to the Database
	 */
	public boolean addMessage(int idPlayer, int idGame, String bericht) {

		String query = "INSERT INTO chatregel (idspeler, bericht)" + " VALUES (" + idPlayer + ", " + "'" + bericht + "'"
				+ ");";
		if (!insertUpdateQuery(query)) {
			System.out.println("Adding message to DB failed");
			return false;
		}
		return true;
	}

	/**
	 * Get all messages from the Database
	 */
	public ArrayList<String> getMessages(int idGame) {

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
				Timestamp tijdstip = myRs.getTimestamp(1);
				String username = myRs.getString(2);
				String bericht = myRs.getString(3);
				String timestamp = tijdstip.toString().substring(11, tijdstip.toString().length() - 2);
				retList.add(timestamp + " - " + username + ": " + bericht);
			}
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// System.out.println("Failed to get messages from Database");
		}
		return retList;
	}

	/**
	 * Add a Tile to the Database
	 */
	public void addTile(int idGame, int idTile, int xCord, int yCord, ResourceType resource, int idChipNumber) {

		String query;
		if (idChipNumber == 0) {
			query = "INSERT INTO tegel (idspel, idtegel, x, y, idgrondstofsoort)" + " VALUES " + "(" + idGame + ", "
					+ idTile + ", " + xCord + ", " + yCord + ", '" + resource.getResourceTypeCode() + "');";
		} else {
			query = "INSERT INTO tegel (idspel, idtegel, x, y, idgrondstofsoort, idgetalfiche)" + " VALUES " + "("
					+ idGame + ", " + idTile + ", " + xCord + ", " + yCord + ", '" + resource.getResourceTypeCode()
					+ "', " + idChipNumber + ");";
		}

		if (!insertUpdateQuery(query)) {
			System.out.println("Unable to add tile");
		}
		;
	}

	/**
	 * Add a player piece
	 */
	public void addBuilding(String idPiece, int idPlayer, int x_From, int y_From) {

		String query = "INSERT INTO spelerstuk (idstuk, idspeler, x_van, y_van)" + " VALUES " + "('" + idPiece + "' , "
				+ idPlayer + ", " + x_From + ", " + y_From + ");";
		if (!insertUpdateQuery(query)) {
			System.out.println("Unable to add Building");
		}
		;

	}

	/**
	 * Get a tile from the Database
	 */
	public ArrayList<Tile> getTile(int idGame) {

		ArrayList<Tile> returnTile = new ArrayList<Tile>();

		makeConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT idtegel, x, y, grondstof, waarde " + "FROM tegels " + "WHERE idspel = " + idGame + " "
				+ "ORDER BY x ASC, y ASC;";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				int idTile = myRs.getInt(1);
				int xCord = myRs.getInt(2);
				int yCord = myRs.getInt(3);
				ResourceType idResource = ResourceType.fromString(myRs.getString(4));
				int chipNumber = myRs.getInt(5);
				returnTile.add(new Tile(idTile, xCord, yCord, idResource, chipNumber));
			}
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return returnTile;
	}

	/**
	 * Add an account to the Database
	 */
	public void createAccount(String username, String wachtwoord) {

		String query = "INSERT INTO account (username, wachtwoord)" + " " + "VALUES (" + "'" + username + "'" + ", "
				+ "'" + wachtwoord + "'" + ");";
		insertUpdateQuery(query);
	}

	/**
	 * Check if the account exists
	 */
	public boolean login(String username, String password) {

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

	/**
	 * Add a player to the Database
	 */
	public void createPlayer(int idGame, String username, String playerColor, int followNR, String playStatus) {

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

	/**
	 * Returns the last used followNumber in the game
	 */
	public int getLastPlayerFollowNumber(int idGame) {

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

	/**
	 * Get all players from an account from the database
	 */
	public ArrayList<Player> getPlayers(String username) {

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

	/**
	 * Check if the account name exists
	 */
	public boolean accountNameExists(String username) {

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

	public void changeRobberLocation(int idGame, int idTile) {

		String query = "UPDATE spel SET struikrover_idtegel = " + idTile + " WHERE idspel = " + idGame + ";";

		if (!insertUpdateQuery(query)) {
			System.out.println("Unable to change robberlocation");
		}
	}
	
	public int getRobberLocation(int idGame) {
		int streetRobberIdTile = 0;
		makeConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT struikrover_idtegel FROM spel WHERE idspel = " + idGame + ";";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				streetRobberIdTile = myRs.getInt(1);
			}
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			System.out.println("Unable to get players");
		}

		return streetRobberIdTile;
	}

	public void setLastThrow(int throw1, int throw2, int idGame) {
		String query = "UPDATE spel SET laatste_worp_steen1 = " + throw1 + ", laatste_worp_steen2 = " + throw2 + " WHERE idspel = " + idGame + ";";

		if (!insertUpdateQuery(query)) {			
			System.out.println("Unable to change last throw");
		}
	}
	
	public int[] getLastThrows(int idGame) {
		int[] lastThrows = new int[2];
		makeConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT laatste_worp_steen1, laatste_worp_steen2 FROM spel WHERE idspel = " + idGame + ";";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				lastThrows[0] = myRs.getInt(1);
				lastThrows[1] = myRs.getInt(2);
			}
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			System.out.println("Unable to get players");
		}

		return lastThrows;
	}
	
	//	public ArrayList<BuildingLocation> getBuildingLocations() {
//
//		makeConnection();
//		Statement stmt = null;
//		ResultSet myRs = null;
//		String query = "SELECT * FROM spelerstuk";
//		try {
//			stmt = myConn.createStatement();
//			myRs = stmt.executeQuery(query);
//			while (myRs.next()) {
//				String idStuk = myRs.getString("username"); // Name of column
//				String password = myRs.getString(2); // Number of column
//				System.out.println(username + " pw: " + password);
//			}
//			myRs.close();
//			stmt.close();
//			myConn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public ArrayList<StreetLocation> getStreetLocations() {
//
//		makeConnection();
//		Statement stmt = null;
//		ResultSet myRs = null;
//		String query = "SELECT * FROM account";
//		try {
//			stmt = myConn.createStatement();
//			myRs = stmt.executeQuery(query);
//			while (myRs.next()) {
//				String username = myRs.getString("username"); // Name of column
//				String password = myRs.getString(2); // Number of column
//				System.out.println(username + " pw: " + password);
//			}
//			myRs.close();
//			stmt.close();
//			myConn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
