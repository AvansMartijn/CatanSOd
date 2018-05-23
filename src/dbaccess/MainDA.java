package dbaccess;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import model.BuildingLocation;
import model.Catan;
import model.City;
import model.DevelopmentCard;
import model.DevelopmentCardType;
import model.PlayStatus;
import model.Player;
import model.PlayerColor;
import model.Resource;
import model.ResourceType;
import model.Street;
import model.StreetLocation;
import model.Tile;
import model.Village;

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

	public static class C3P0DataSource {
		   private static C3P0DataSource dataSource;
		   private ComboPooledDataSource comboPooledDataSource;

		   private C3P0DataSource() {
		      try {
		         comboPooledDataSource = new ComboPooledDataSource();
		         comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
		         comboPooledDataSource.setJdbcUrl(url);
		         comboPooledDataSource.setUser(user);
		         comboPooledDataSource.setPassword(password);}
		      catch (PropertyVetoException ex1) {
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
	 * Initializes a connection
	 */
	public void makeConnection() {
		myConn = C3P0DataSource.getInstance().getConnection();
//		try {
//			myConn = DriverManager.getConnection(url, user, password);
//		} catch (SQLException ex) {
//			System.out.println("Connection failed");
//		}
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
		String searchquery = "SELECT idspeler FROM speler WHERE username = '" + username + "' AND idspel = " + idGame
				+ " ORDER BY idspeler DESC LIMIT 1";
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

		String query = "INSERT INTO spelergrondstofkaart (idspel, idgrondstofkaart, idspeler)" + " VALUES " + "(" + idGame + ", '"+ idResourceCard + "' , null);";
		if (!insertUpdateQuery(query)) {
			System.out.println("Unable to add resourceCard");
		}
		;

	}
	
	public void addDevelopmentCard(String idDevelopmentCard, int idGame) {

		String query = "INSERT INTO spelerontwikkelingskaart (idspel, idontwikkelingskaart, idspeler, gespeeld)" + " VALUES " 
		+ "(" + idGame + ", '"+ idDevelopmentCard + "' , null, 0);";
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
	public void createPlayer(int idPlayer, int idGame, String username, String playerColor, int followNR, String playStatus) {

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
		makeConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String searchquery = "SELECT beurt_idspeler FROM spel WHERE idspel = " + idGame + ";";
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

	public int getLastUsedPlayerID() {
		int idPlayer = 0;

		makeConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT idspeler FROM speler ORDER BY idspeler DESC LIMIT 1";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
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
	public ArrayList<Integer> getGameIDsFromPlayer(String username) {

		ArrayList<Integer> retList = new ArrayList<Integer>();

		makeConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT idspel FROM speler WHERE username = '" + username + "';";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				int idGame = myRs.getInt(1);
				retList.add(idGame);
			}
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			System.out.println("Unable to get GameId's");
		}

		return retList;

	}

	public ArrayList<Player> getPlayersFromGame(int idGame) {

		ArrayList<Player> playerList = new ArrayList<Player>();

		makeConnection();
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
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			System.out.println("Unable to get gameplayers");
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
		String query = "UPDATE spel SET laatste_worp_steen1 = " + throw1 + ", laatste_worp_steen2 = " + throw2
				+ " WHERE idspel = " + idGame + ";";

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
			System.out.println("Unable to get last throws");
		}

		return lastThrows;
	}

	public boolean getShouldRefresh(int idPlayer) {
		boolean shouldRefresh = false;
		makeConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT shouldrefresh FROM speler WHERE idspeler = " + idPlayer + ";";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				shouldRefresh = myRs.getBoolean(1);
			}
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			System.out.println("Unable to get shouldRefresh");
		}

		return shouldRefresh;
	}

	public void setShouldRefresh(int playerID, boolean shouldRefresh) {
		String query = "UPDATE speler SET shouldrefresh = " + shouldRefresh + " WHERE idspeler = " + playerID + ";";

		if (!insertUpdateQuery(query)) {
			System.out.println("Unable to change shouldRefresh");
		}
	}

	public void updateBuilding(String idPiece, int idPlayer, int x_From, int y_From) {
		String query;
		if(x_From == 0 && y_From == 0) {
			 query = "UPDATE spelerstuk SET x_van = null, y_van = null WHERE idspeler = '" + idPlayer + "' AND idstuk = '" + idPiece + "';";
		}else {
			 query = "UPDATE spelerstuk SET x_van = " + x_From + ", y_van = " + y_From
					+ " WHERE idspeler = '" + idPlayer + "' AND idstuk = '" + idPiece + "';";
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
		makeConnection();
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
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retArr;
	}

	public ArrayList<Village> getVillageFromPlayer(int playerID) {
		ArrayList<Village> retArr = new ArrayList<Village>();
		makeConnection();
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
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retArr;
	}

	public ArrayList<City> getCityFromPlayer(int playerID) {
		ArrayList<City> retArr = new ArrayList<City>();
		makeConnection();
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
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retArr;
	}

	public ArrayList<Street> getStreetsFromPlayer(int playerID) {
		ArrayList<Street> retArr = new ArrayList<Street>();
		makeConnection();
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
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retArr;
	}

	public ArrayList<String> getAllAccounts() {

		ArrayList<String> retList = new ArrayList<String>();
		makeConnection();
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
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to get accounts from Database");
		}
		return retList;
	}

	public ArrayList<Resource> updateResources(int idGame, int idPlayer) {

		ArrayList<Resource> retList = new ArrayList<Resource>();
		makeConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = null;
		if(idPlayer == 0) {
			query = "SELECT idgrondstofkaart FROM spelergrondstofkaart WHERE idspel = " + idGame + " AND idspeler IS NULL;";
		} else {
			query = "SELECT idgrondstofkaart FROM spelergrondstofkaart WHERE idspel = " + idGame + " AND idspeler = '" + idPlayer + "';";
		}
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				String resourceID = myRs.getString(1);
				retList.add(new Resource(resourceID));
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
	
	public ArrayList<DevelopmentCard> updateDevelopmentCards(int idGame, int idPlayer) {

		ArrayList<DevelopmentCard> retList = new ArrayList<DevelopmentCard>();
		makeConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = null;
		if(idPlayer == 0) {
			query = "SELECT idontwikkelingskaart, gespeeld FROM spelerontwikkelingskaart WHERE idspel = " + idGame + " AND idspeler IS NULL;";
		} else {
			query = "SELECT idontwikkelingskaart, gespeeld FROM spelerontwikkelingskaart WHERE idspel = " + idGame + " AND idspeler = " + idPlayer + ";";
		}
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				String developmentCardID = myRs.getString(1);
				boolean played = myRs.getBoolean(2);
				retList.add(new DevelopmentCard(developmentCardID, played));
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

	public boolean hasThrown(int idGame) {
		boolean shouldRefresh = false;
		makeConnection();
		Statement stmt = null;
		ResultSet myRs = null;
		String query = "SELECT gedobbeld FROM spel WHERE idspel = " + idGame + ";";
		try {
			stmt = myConn.createStatement();
			myRs = stmt.executeQuery(query);
			while (myRs.next()) {
				shouldRefresh = myRs.getBoolean(1);
			}
			myRs.close();
			stmt.close();
			myConn.close();
		} catch (SQLException e) {
			System.out.println("Unable to get has Thrown");
		}

		return shouldRefresh;
	}

}
