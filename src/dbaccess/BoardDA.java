package dbaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.ResourceType;

public class BoardDA extends GameDA {
	private Connection myConn;

	public BoardDA() {
	}

	public void addTile(int idGame, int idTile, boolean port, ResourceType portResource) {
		if(portResource != null) {
			String query = "INSERT INTO chatregel (x, y, haven, portResource)" + " VALUES (" + xCord + ", " + yCord + ", " + port + ", "+ "'" + portResource.toString() + "'"
					+ ");";
			insertUpdateQuery(query);

		} else { 
			String query = "INSERT INTO chatregel (x, y, haven)" + " VALUES (" + xCord + ", " + yCord + ", " + port + ");";
			insertUpdateQuery(query);
		}
	}

	public void getMessages() {
		myConn = makeConnection();
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
