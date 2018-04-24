package dbaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.ResourceType;

public class BoardDA {
	private Connection myConn;
	private MainDA mainDA;

	public BoardDA(MainDA mainDA) {
		this.mainDA = mainDA;
		mainDA.notifyAll();
	}

	public void addTile(int idGame, int idTile, int xCord, int yCord, ResourceType resource, int idChipNumber) {
	/**
	 * Add a Tile
	 * 	
	 */
			String query = "INSERT INTO tegel (idspel, idtegel, x, y, idgrondstofsoort, idgetalfishe)" + " VALUES "
					+ "(" + idGame + ", " + idTile + ", " + xCord + ", " + yCord + ", " + resource.getResourceTypeCode() + ", "+ idChipNumber + ");";
			mainDA.insertUpdateQuery(query);
	}
	
	public void addTile(int idGame, int idTile, int xCord, int yCord, ResourceType resource) {
	/**
	 * Add a Tile without ChipNumber
	 */
		
		String query = "INSERT INTO tegel (idspel, idtegel, x, y, idgrondstofsoort, idgetalfishe)" + " VALUES "
				+ "(" + idGame + ", " + idTile + ", " + xCord + ", " + yCord + ", " + resource.getResourceTypeCode() + ");";
		mainDA.insertUpdateQuery(query);
}

	public void getMessages() {
		myConn = mainDA.makeConnection();
			Statement stmt = null;
			ResultSet myRs = null;
			String query = "SELECT tijdstip, speler.username, bericht FROM chatregel "
					+ "JOIN speler ON chatregel.idspeler = speler.idspeler";
			try {
				stmt = myConn.createStatement();
				myRs = stmt.executeQuery(query);
				while (myRs.next()) {
					String tijdstip = myRs.getString(1);
					String username = myRs.getString(2);
					String bericht = myRs.getString(3);
					System.out.println(tijdstip + " " + username + " : " + bericht);
				}
				myRs.close();
				stmt.close();
				myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
