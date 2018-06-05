package dbaccess;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import model.BuildingLocation;
import model.City;
import model.DevelopmentCard;
import model.PlayStatus;
import model.Player;
import model.PlayerColor;
import model.Resource;
import model.ResourceType;
import model.Street;
import model.Tile;
import model.TradeRequest;
import model.Village;

public class MainDA {
	private static final String url = "jdbc:mysql://databases.aii.avans.nl:3306/mfghaneg_db?useSSL=false";
	private static final String user = "mfghaneg";
	private static final String password = "Ab12345";
	// protected Connection myConn;
	protected C3P0DataSource connectionPool;

	public MainDA() {
		// myConn = null;
		connectionPool = C3P0DataSource.getInstance();
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

	public static class C3P0DataSource {
		private static C3P0DataSource dataSource;
		private ComboPooledDataSource comboPooledDataSource;

		private C3P0DataSource() {
			try {
				comboPooledDataSource = new ComboPooledDataSource();
				comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
				comboPooledDataSource.setJdbcUrl(url);
				comboPooledDataSource.setUser(user);
				comboPooledDataSource.setInitialPoolSize(2);
				comboPooledDataSource.setMinPoolSize(2);
				comboPooledDataSource.setMaxPoolSize(2);
				// comboPooledDataSource.setMaxIdleTimeExcessConnections(3600);

				comboPooledDataSource.setPassword(password);
			} catch (PropertyVetoException ex1) {
				ex1.printStackTrace();
			}
		}

		public static C3P0DataSource getInstance() {
			if (dataSource == null)
				dataSource = new C3P0DataSource();
			return dataSource;
		}

		public Connection getConnection() {
			Connection con = null;
			try {
				con = comboPooledDataSource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return con;
		}
	}

	/**
	 * Executes an insert or update query
	 */
	public boolean insertUpdateQuery(String query) {
		boolean queryResult = false;
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		try {
			stmt = myConn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			queryResult = false;
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		queryResult = true;
		return queryResult;
	}

	/**
	 * Test
	 */
	public void testQuery() {

		Connection myConn = connectionPool.getConnection();
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
	}

	/**
	 * Create a game record in the Database
	 */
	public int createGame(boolean randomBoard) {

		int idGame = 0;

		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT idspel FROM spel ORDER BY idspel DESC LIMIT 1";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				idGame = myRs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			System.out.println("Unable get last game ID from DB");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
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
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String searchquery = "SELECT idspeler FROM speler WHERE username = '" + username + "' AND idspel = " + idGame
				+ " ORDER BY idspeler DESC LIMIT 1";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(searchquery);
			while (myRs.next()) {
				idPlayer = myRs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Unable to get last player ID");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return idPlayer;
	}

	/**
	 * Add a message to the Database
	 */
	public boolean addMessage(int idPlayer, String bericht) {
		boolean differentTimestamp = true;
		String lastTimeStamp = null;
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String searchquery = "SELECT (tijdstip < CURRENT_TIMESTAMP), tijdstip FROM chatregel WHERE idspeler = "
				+ idPlayer + " ORDER BY tijdstip DESC LIMIT 1";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(searchquery);
			while (myRs.next()) {
				differentTimestamp = myRs.getBoolean(1);
				lastTimeStamp = myRs.getString(2);
			}
		} catch (SQLException e) {
			System.out.println("Unable to get last player ID");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		String query = null;
		if (differentTimestamp) {
			query = "INSERT INTO chatregel (idspeler, bericht)" + " VALUES (" + idPlayer + ", '" + bericht + "');";
		} else {
			query = "INSERT INTO chatregel (tijdstip, idspeler, bericht) VALUES (DATE_ADD(\"" + lastTimeStamp
					+ "\", INTERVAL 1 second), " + idPlayer + ", '" + bericht + "');";
		}
		System.out.println(query);
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
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT tijdstip, bericht FROM chatregel "
				+ "JOIN speler ON chatregel.idspeler = speler.idspeler "
				+ "WHERE chatregel.idspeler IN(SELECT idspeler FROM speler WHERE idspel = " + idGame
				+ ") ORDER BY tijdstip ASC;";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				Timestamp tijdstip = myRs.getTimestamp(1);
				String bericht = myRs.getString(2);
				String timestamp = tijdstip.toString().substring(11, tijdstip.toString().length() - 2);
				retList.add(timestamp + " " + bericht);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// System.out.println("Failed to get messages from Database");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
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
	public void addPlayerPiece(String idPiece, int idPlayer) {

		String query = "INSERT INTO spelerstuk (idstuk, idspeler, x_van, y_van, x_naar, y_naar)" + " VALUES " + "('"
				+ idPiece + "' , " + idPlayer + ", null, null, null, null);";
		System.out.println(query);
		if (!insertUpdateQuery(query)) {
			System.out.println("Unable to add Building");
		}
		;

	}

	public void addResourceCard(String idResourceCard, int idGame) {

		String query = "INSERT INTO spelergrondstofkaart (idspel, idgrondstofkaart, idspeler)" + " VALUES " + "("
				+ idGame + ", '" + idResourceCard + "' , null);";
		if (!insertUpdateQuery(query)) {
			System.out.println("Unable to add resourceCard");
		}
		;

	}

	public void addDevelopmentCard(String idDevelopmentCard, int idGame) {

		String query = "INSERT INTO spelerontwikkelingskaart (idspel, idontwikkelingskaart, idspeler, gespeeld)"
				+ " VALUES " + "(" + idGame + ", '" + idDevelopmentCard + "' , null, 0);";
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

		Connection myConn = connectionPool.getConnection();
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
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

		Connection myConn = connectionPool.getConnection();
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
			if (password.equals(wachtwoord)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return false;
	}

	/**
	 * Add a player to the Database
	 */
	public void createPlayer(int idPlayer, int idGame, String username, String playerColor, int followNR,
			String playStatus) {

		String insertquery = "INSERT INTO speler (idspeler, idspel, username, kleur, speelstatus, shouldrefresh, volgnr)"
				+ " " + "VALUES (" + idPlayer + ", " + idGame + ", '" + username + "', '" + playerColor + "', '"
				+ playStatus + "', " + false + ", " + followNR + ");";

		if (!insertUpdateQuery(insertquery)) {
			System.out.println("Adding player to DB failed");
		}
	}

	public void setTurn(int idPlayer, int idGame) {
		String insertquery = "UPDATE spel SET beurt_idspeler = '" + idPlayer + "' WHERE idspel = " + idGame + ";";

		if (!insertUpdateQuery(insertquery)) {
			System.out.println("Changing turn to DB failed");
		}
	}

	public int getTurn(int idGame) {
		int idPlayer = 0;
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String searchquery = "SELECT beurt_idspeler FROM spel WHERE idspel = " + idGame + ";";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(searchquery);
			while (myRs.next()) {
				idPlayer = myRs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Unable to get last player ID");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return idPlayer;
	}

	public void setThrownDice(int thrown, int idGame) {
		String insertquery = "UPDATE spel SET gedobbeld = '" + thrown + "' WHERE idspel = " + idGame + ";";

		if (!insertUpdateQuery(insertquery)) {
			System.out.println("Changing thrownDice to DB failed");
		}
	}

	public void setFirstRound(int firstRound, int idGame) {
		String insertquery = "UPDATE spel SET eersteronde = '" + firstRound + "' WHERE idspel = " + idGame + ";";

		if (!insertUpdateQuery(insertquery)) {
			System.out.println("Changing firstround to DB failed");
		}
	}

	public int getFirstRound(int idGame) {

		int firstround = 0;
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT eersteronde FROM spel WHERE idspel = " + idGame + ";";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				firstround = myRs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Unable to get first round");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return firstround;
	}

	public int getLastUsedPlayerID() {
		int idPlayer = 0;

		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT idspeler FROM speler ORDER BY idspeler DESC LIMIT 1";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				idPlayer = myRs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Unable to get last player ID");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return idPlayer;
	}

	/**
	 * Returns the last used followNumber in the game
	 */
	public int getLastPlayerFollowNumber(int idGame) {

		Statement stmt = null;
		ResultSet myRs = null;
		int lastNR = 0;

		Connection myConn = connectionPool.getConnection();
		String followquery = "SELECT volgnr FROM speler WHERE idspel = '" + idGame + "' ORDER BY volgnr DESC LIMIT 1";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(followquery);
			while (myRs.next()) {
				lastNR = myRs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Unable to get last follownumber from database");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return lastNR;
	}

	/**
	 * Get all players from an account from the database
	 */
	public ArrayList<Integer> getGameIDsFromPlayer(String username, boolean finished) {

		ArrayList<Integer> retList = new ArrayList<Integer>();

		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = null;

		if (finished) {
			query = "SELECT idspel FROM speler WHERE username = '" + username
					+ "' AND speelstatus = 'uitgespeeld' ORDER BY idspel DESC;";
		} else {
			query = "SELECT idspel FROM speler WHERE username = '" + username
					+ "' AND (speelstatus = 'geaccepteerd' OR speelstatus = 'uitdager') ORDER BY idspel DESC;";
		}
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				int idGame = myRs.getInt(1);
				retList.add(idGame);
			}
		} catch (SQLException e) {
			System.out.println("Unable to get GameId's");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}

		return retList;

	}

	public ArrayList<Integer> getInvitesFromPlayer(String username) {

		ArrayList<Integer> retList = new ArrayList<Integer>();

		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT idspel FROM speler WHERE username = '" + username
				+ "' AND speelstatus = 'uitgedaagde' ORDER BY idspel DESC;";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				int idGame = myRs.getInt(1);
				retList.add(idGame);
			}
		} catch (SQLException e) {
			System.out.println("Unable to get GameId's");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}

		return retList;

	}

	public ArrayList<Player> getPlayersFromGame(int idGame) {

		ArrayList<Player> playerList = new ArrayList<Player>();

		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT idspeler, username, kleur, speelstatus, volgnr FROM speler WHERE idspel = '" + idGame
				+ "' ORDER BY volgnr ASC;";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				int idplayer = myRs.getInt(1);
				String username = myRs.getString(2);
				String color = myRs.getString(3).toUpperCase();
				String playStatus = myRs.getString(4).toUpperCase();
				int follownr = myRs.getInt(5);
				playerList.add(new Player(idplayer, idGame, username, PlayerColor.valueOf(color), follownr,
						PlayStatus.valueOf(playStatus)));
			}
		} catch (SQLException e) {
			System.out.println("Unable to get gameplayers");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}

		return playerList;

	}

	/**
	 * Check if the account name exists
	 */
	public boolean accountNameExists(String username) {

		Connection myConn = connectionPool.getConnection();
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

			if (retusername == null) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Unable to check if username exists");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
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
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT struikrover_idtegel FROM spel WHERE idspel = " + idGame + ";";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				streetRobberIdTile = myRs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Unable to get players");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}

		return streetRobberIdTile;
	}

	public void setLastThrow(int throw1, int throw2, int idGame) {
		String query = "UPDATE spel SET laatste_worp_steen1 = " + throw1 + ", laatste_worp_steen2 = " + throw2
				+ " WHERE idspel = " + idGame + ";";

		if (!insertUpdateQuery(query)) {
			System.out.println("Unable to change last throw");
		}
	}

	public int[] getLastThrows(int idGame) {
		int[] lastThrows = new int[2];
		Connection myConn = connectionPool.getConnection();
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
		} catch (SQLException e) {
			System.out.println("Unable to get last throws");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}

		return lastThrows;
	}

	public boolean getShouldRefresh(int idPlayer) {
		boolean shouldRefresh = true;
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT shouldrefresh FROM speler WHERE idspeler = " + idPlayer + ";";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				shouldRefresh = myRs.getBoolean(1);
			}
		} catch (SQLException e) {
			System.out.println("Unable to get shouldRefresh");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}

		if (shouldRefresh) {
			this.setShouldRefresh(idPlayer, false);
		}
		return shouldRefresh;
	}

	public boolean setShouldRefresh(int playerID, boolean shouldRefresh) {
		String query = "UPDATE speler SET shouldrefresh = " + shouldRefresh + " WHERE idspeler = " + playerID + ";";

		if (!insertUpdateQuery(query)) {
			System.out.println("Unable to change shouldRefresh");
			return false;
		}
		return true;
	}

	public void updateBuilding(String idPiece, int idPlayer, int x_From, int y_From) {
		String query;
		if (x_From == 0 && y_From == 0) {
			query = "UPDATE spelerstuk SET x_van = null, y_van = null WHERE idspeler = '" + idPlayer
					+ "' AND idstuk = '" + idPiece + "';";
		} else {
			query = "UPDATE spelerstuk SET x_van = " + x_From + ", y_van = " + y_From + " WHERE idspeler = '" + idPlayer
					+ "' AND idstuk = '" + idPiece + "';";
		}
		System.out.println(query);
		if (!insertUpdateQuery(query)) {
			System.out.println("Unable to update Building");
		}

	}

	public void updateStreet(String idPiece, int idPlayer, int x_From, int y_From, int x_to, int y_to) {

		String query = "UPDATE spelerstuk SET x_van = " + x_From + ", y_van = " + y_From + ", x_naar = " + x_to
				+ ", y_naar = " + y_to + " WHERE idspeler ='" + idPlayer + "' AND idstuk = '" + idPiece + "';";
		if (!insertUpdateQuery(query)) {
			System.out.println("Unable to add Street");
		}

	}

	public ArrayList<City> getCitiesFromPlayer(int playerID) {
		ArrayList<City> retArr = new ArrayList<City>();
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT idstuk, x_van, y_van FROM spelerstuk WHERE idstuk LIKE 'c%' AND idspeler = " + playerID
				+ ";";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				String idpiece = myRs.getString(1);
				int x_from = myRs.getInt(2);
				int y_from = myRs.getInt(3);

				City city = new City(idpiece);
				city.setBuildingLocation(new BuildingLocation(x_from, y_from));
				retArr.add(city);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return retArr;
	}

	public ArrayList<Village> getVillageFromPlayer(int playerID) {
		ArrayList<Village> retArr = new ArrayList<Village>();
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT idstuk, x_van, y_van FROM spelerstuk WHERE idstuk LIKE 'd%' AND idspeler = " + playerID
				+ ";";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				String idpiece = myRs.getString(1);
				int x_from = myRs.getInt(2);
				int y_from = myRs.getInt(3);
				Village village = new Village(idpiece);
				village.setBuildingLocation(new BuildingLocation(x_from, y_from));
				retArr.add(village);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return retArr;
	}

	public ArrayList<City> getCityFromPlayer(int playerID) {
		ArrayList<City> retArr = new ArrayList<City>();
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT idstuk, x_van, y_van FROM spelerstuk WHERE idstuk LIKE 'c%' AND idspeler = " + playerID
				+ ";";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				String idpiece = myRs.getString(1);
				int x_from = myRs.getInt(2);
				int y_from = myRs.getInt(3);
				City city = new City(idpiece);
				city.setBuildingLocation(new BuildingLocation(x_from, y_from));
				retArr.add(city);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return retArr;
	}

	public ArrayList<Street> getStreetsFromPlayer(int playerID) {
		ArrayList<Street> retArr = new ArrayList<Street>();
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT idstuk, x_van, y_van, x_naar, y_naar FROM spelerstuk WHERE idstuk LIKE 'r%' AND idspeler = "
				+ playerID + ";";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				String idpiece = myRs.getString(1);
				int x_from = myRs.getInt(2);
				int y_from = myRs.getInt(3);
				int x_to = myRs.getInt(4);
				int y_to = myRs.getInt(5);

				retArr.add(new Street(idpiece, x_from, y_from, x_to, y_to));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return retArr;
	}

	public ArrayList<String> getAllAccounts() {

		ArrayList<String> retList = new ArrayList<String>();
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT username FROM account;";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				String username = myRs.getString(1);
				retList.add(username);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to get accounts from Database");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return retList;
	}

	public ArrayList<Resource> updateResources(int idGame, int idPlayer) {

		ArrayList<Resource> retList = new ArrayList<Resource>();
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = null;
		if (idPlayer == 0) {
			query = "SELECT idgrondstofkaart FROM spelergrondstofkaart WHERE idspel = " + idGame
					+ " AND idspeler IS NULL;";
		} else {
			query = "SELECT idgrondstofkaart FROM spelergrondstofkaart WHERE idspel = " + idGame + " AND idspeler = '"
					+ idPlayer + "';";
		}
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				String resourceID = myRs.getString(1);
				retList.add(new Resource(resourceID));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// System.out.println("Failed to get messages from Database");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return retList;
	}

	public void removeResource(String idResource, int idGame) {
		String insertquery = "UPDATE spelergrondstofkaart SET idspeler = null WHERE idgrondstofkaart = '" + idResource
				+ "' AND idspel = " + idGame + ";";

		if (!insertUpdateQuery(insertquery)) {
			System.out.println("Removing resource in DB failed");
		}
	}

	public void addResourceToPlayer(String idResource, int idGame, int idPlayer) {
		String insertquery = "UPDATE spelergrondstofkaart SET idspeler = '" + idPlayer + "' WHERE idgrondstofkaart = '"
				+ idResource + "' AND idspel = " + idGame + ";";

		if (!insertUpdateQuery(insertquery)) {
			System.out.println("adding resource to player in DB failed");
		}
	}

	public ArrayList<DevelopmentCard> updateDevelopmentCards(int idGame, int idPlayer) {

		ArrayList<DevelopmentCard> retList = new ArrayList<DevelopmentCard>();
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = null;
		if (idPlayer == 0) {
			query = "SELECT idontwikkelingskaart, gespeeld FROM spelerontwikkelingskaart WHERE idspel = " + idGame
					+ " AND idspeler IS NULL;";
		} else {
			query = "SELECT idontwikkelingskaart, gespeeld FROM spelerontwikkelingskaart WHERE idspel = " + idGame
					+ " AND idspeler = " + idPlayer + ";";
		}
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				String developmentCardID = myRs.getString(1);
				boolean played = myRs.getBoolean(2);
				retList.add(new DevelopmentCard(developmentCardID, played));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// System.out.println("Failed to get messages from Database");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return retList;
	}

	public boolean hasThrown(int idGame) {
		boolean shouldRefresh = false;
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT gedobbeld FROM spel WHERE idspel = " + idGame + ";";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				shouldRefresh = myRs.getBoolean(1);
			}
		} catch (SQLException e) {
			System.out.println("Unable to get has Thrown");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return shouldRefresh;
	}

	public void createTradeRequest(TradeRequest tR) {
		String query;
		if (tR.getAccepted() == 1) {
			query = "INSERT INTO ruilaanbod (idspeler, geeft_baksteen, geeft_wol, geeft_erts, geeft_graan, geeft_hout, vraagt_baksteen, vraagt_wol, vraagt_erts, vraagt_graan, vraagt_hout, geaccepteerd)"
					+ " VALUES " + "(" + tR.getIdPlayer() + ", " + tR.getW_brick() + ", " + tR.getW_wool() + ", "
					+ tR.getW_iron() + ", " + tR.getW_wheat() + ", " + tR.getW_wood() + ", " + tR.getG_brick() + ", "
					+ tR.getG_wool() + ", " + tR.getG_iron() + ", " + tR.getG_wheat() + ", " + tR.getG_wood() + ", 1);";
		} else if (tR.getAccepted() == 0) {
			query = "INSERT INTO ruilaanbod (idspeler, geeft_baksteen, geeft_wol, geeft_erts, geeft_graan, geeft_hout, vraagt_baksteen, vraagt_wol, vraagt_erts, vraagt_graan, vraagt_hout, geaccepteerd)"
					+ " VALUES " + "(" + tR.getIdPlayer() + ", " + tR.getW_brick() + ", " + tR.getW_wool() + ", "
					+ tR.getW_iron() + ", " + tR.getW_wheat() + ", " + tR.getW_wood() + ", " + tR.getG_brick() + ", "
					+ tR.getG_wool() + ", " + tR.getG_iron() + ", " + tR.getG_wheat() + ", " + tR.getG_wood() + ", 0);";
		} else {
			query = "INSERT INTO ruilaanbod (idspeler, geeft_baksteen, geeft_wol, geeft_erts, geeft_graan, geeft_hout, vraagt_baksteen, vraagt_wol, vraagt_erts, vraagt_graan, vraagt_hout)"
					+ " VALUES " + "(" + tR.getIdPlayer() + ", " + tR.getG_brick() + ", " + tR.getG_wool() + ", "
					+ tR.getG_iron() + ", " + tR.getG_wheat() + ", " + tR.getG_wood() + ", " + tR.getW_brick() + ", "
					+ tR.getW_wool() + ", " + tR.getW_iron() + ", " + tR.getW_wheat() + ", " + tR.getW_wood() + ");";
		}
		System.out.println(query);
		if (!insertUpdateQuery(query)) {
			System.out.println("Unable to add tradeRequest");
		}
	}

	public void deleteTradeRequests(int idGame) {

		String query = "DELETE FROM ruilaanbod WHERE idspeler IN(SELECT idspeler FROM speler WHERE idspel = " + idGame
				+ ");";
		System.out.println(query);
		if (!insertUpdateQuery(query)) {
			System.out.println("Unable to delete tradeRequest");
		}
	}

//	public ArrayList<DevelopmentCard> getTradeRequests(int idGame, int idPlayer) {
//
//		ArrayList<DevelopmentCard> retList = new ArrayList<DevelopmentCard>();
//		Connection myConn = connectionPool.getConnection();
//		Statement stmt = null;
//		ResultSet myRs = null;
//		String query = "SELECT * FROM ruilaanbod WHERE idspel = " + idGame + " AND idspeler IS NULL;";
//		try {
//			stmt = myConn.createStatement();
//			myRs = stmt.executeQuery(query);
//			while (myRs.next()) {
//				String developmentCardID = myRs.getString(1);
//				boolean played = myRs.getBoolean(2);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			// System.out.println("Failed to get messages from Database");
//		} finally {
//			try {
//				myRs.close();
//			} catch (Exception e) {
//				/* ignored */ }
//			try {
//				stmt.close();
//			} catch (Exception e) {
//				/* ignored */ }
//			try {
//				myConn.close();
//			} catch (Exception e) {
//				/* ignored */ }
//		}
//		return retList;
//	}

	public void acceptInvite(int playerId) {
		String query = "UPDATE speler SET speelstatus = 'geaccepteerd' WHERE idspeler = " + playerId + ";";

		if (!insertUpdateQuery(query)) {
			System.out.println("Unable to change playstatus");
		}
	}

	public void declineInvite(int playerId) {
		String query = "UPDATE speler SET speelstatus = 'geweigerd' WHERE idspeler = " + playerId + ";";

		if (!insertUpdateQuery(query)) {
			System.out.println("Unable to change playstatus");
		}
	}

	public void switchPlayer(String originalUsername, String newUsername, int gameId) {
		String query = "UPDATE speler SET username = '" + newUsername
				+ "', speelstatus = 'uitgedaagde'  WHERE username = '" + originalUsername + "' AND idspel = " + gameId
				+ ";";

		if (!insertUpdateQuery(query)) {
			System.out.println("Unable to switch player");
		}
	}

	public TradeRequest getInitialTradeRequest(int idGame) {

		TradeRequest tr = null;
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT * FROM ruilaanbod WHERE idspeler IN(SELECT idspeler FROM speler WHERE idspel = " + idGame
				+ ") AND geaccepteerd IS NULL;";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				int playerID = myRs.getInt(1);
				int brickgive = myRs.getInt(2);
				int woolgive = myRs.getInt(3);
				int irongive = myRs.getInt(4);
				int wheatgive = myRs.getInt(5);
				int woodgive = myRs.getInt(6);
				int brickreceive = myRs.getInt(7);
				int woolreceive = myRs.getInt(8);
				int ironreceive = myRs.getInt(9);
				int wheatreceive = myRs.getInt(10);
				int woodreceive = myRs.getInt(11);
				tr = new TradeRequest(playerID, brickgive, woolgive, irongive, wheatgive, woodgive, brickreceive,
						woolreceive, ironreceive, wheatreceive, woodreceive);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// System.out.println("Failed to get messages from Database");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return tr;
	}

	public TradeRequest getSingleTradeRequest(int idPlayer) {

		TradeRequest tr = null;
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT * FROM ruilaanbod WHERE idspeler = " + idPlayer + ";";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				int playerID = myRs.getInt(1);
				int brickgive = myRs.getInt(2);
				int woolgive = myRs.getInt(3);
				int irongive = myRs.getInt(4);
				int wheatgive = myRs.getInt(5);
				int woodgive = myRs.getInt(6);
				int brickreceive = myRs.getInt(7);
				int woolreceive = myRs.getInt(8);
				int ironreceive = myRs.getInt(9);
				int wheatreceive = myRs.getInt(10);
				int woodreceive = myRs.getInt(11);
				int accepted = myRs.getInt(12);
				tr = new TradeRequest(playerID, brickgive, woolgive, irongive, wheatgive, woodgive, brickreceive,
						woolreceive, ironreceive, wheatreceive, woodreceive, accepted);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// System.out.println("Failed to get messages from Database");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return tr;
	}

	public int getAmountOfOpenRequests(int idGame) {
		int amount = 0;
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT count(*) FROM ruilaanbod WHERE idspeler IN(SELECT idspeler FROM speler WHERE idspel = "
				+ idGame + ") ;";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				amount = myRs.getInt(1);
			}
			System.out.println("amount retrieved from DB: " + amount);
		} catch (SQLException e) {
			e.printStackTrace();
			// System.out.println("Failed to get messages from Database");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return amount;
	}

	public void abortGame(int[] playerids) {
		for (int playerId : playerids) {
			String query = "UPDATE speler SET speelstatus = 'afgebroken' WHERE idspeler = " + playerId + ";";

			if (!insertUpdateQuery(query)) {
				System.out.println("Unable to change playstatus");
			}
		}
	}

	public void finishGame(int idGame) {
		String query = "UPDATE speler SET speelstatus = 'uitgespeeld' WHERE idspel = " + idGame + ";";

		if (!insertUpdateQuery(query)) {
			System.out.println("Unable to change playstatus");
		}
	}

	public void addDevelopmentCardToPlayer(String developmentCardID, int idPlayer, int idGame) {
		String insertquery = "UPDATE spelerontwikkelingskaart SET idspeler = '" + idPlayer + "' WHERE idontwikkelingskaart = '"
				+ developmentCardID + "' AND idspel = " + idGame + ";";

		if (!insertUpdateQuery(insertquery)) {
			System.out.println("adding resource to player in DB failed");
		}
		
	}
	
	public void useDevelopmentCard(String developmentCardID, int idGame) {
		String insertquery = "UPDATE spelerontwikkelingskaart SET gespeeld = '1' WHERE idontwikkelingskaart = '"
				+ developmentCardID + "' AND idspel = " + idGame + ";";

		if (!insertUpdateQuery(insertquery)) {
			System.out.println("updating resource to player in DB failed");
		}
	}
	
	public void updateLargestArmy(int idGame, int idPlayer) {
		String insertquery = "UPDATE spel SET grootste_rm_idspeler = " + idPlayer + "  WHERE idspel = "
				+ idGame + ";";

		if (!insertUpdateQuery(insertquery)) {
			System.out.println("updating largest army in DB failed");
		}
	}
	
	public void updateLongestTradeRoute(int idGame, int idPlayer) {
		String insertquery = "UPDATE spel SET langste_hr_idspeler = " + idPlayer + "  WHERE idspel = "
				+ idGame + ";";

		if (!insertUpdateQuery(insertquery)) {
			System.out.println("updating largest army in DB failed");
		}
	}
	
	public int getPlayerWithLongestTradeRoute(int idGame) {

		int playerID = 0;
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT langste_hr_idspeler FROM spel WHERE idspel = " + idGame + ";";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				 playerID = myRs.getInt(1);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// System.out.println("Failed to get messages from Database");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return playerID;
	}
	
	public int getPlayerWithLargestArmy(int idGame) {

		int playerID = 0;
		Connection myConn = connectionPool.getConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT grootste_rm_idspeler FROM spel WHERE idspel = " + idGame + ";";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				 playerID = myRs.getInt(1);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// System.out.println("Failed to get messages from Database");
		} finally {
			try {
				myRs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				stmt.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				myConn.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return playerID;
	}

}
