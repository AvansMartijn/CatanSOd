package dbaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.ResourceType;
import model.Tile;

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

	public Tile getTile(int tileID) {
		/**
		 * Get a tile
		 */
		Tile returnTile = null;
		
		myConn = mainDA.makeConnection();
			Statement stmt = null;
			ResultSet myRs = null;
			String query = "SELECT idtegel, x, y, idgrondstofsoort, idgetalfishe FROM tegel ;";
			try {
				stmt = myConn.createStatement();
				myRs = stmt.executeQuery(query);
				while (myRs.next()) {
					int idTile = myRs.getInt(1);
					int xCord = myRs.getInt(2);
					int yCord = myRs.getInt(3);
					ResourceType idResource = ResourceType.fromString(myRs.getString(4));
					int idChipNumber = myRs.getInt(5);
					returnTile = new Tile(idTile, xCord, yCord, idResource, idChipNumber);
				}
				myRs.close();
				stmt.close();
				myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
			return returnTile;
		}
}
