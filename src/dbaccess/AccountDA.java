package dbaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDA extends MainDA {

	public void createAccount(String username, String wachtwoord) {
		makeConnection();
		String query = "INSERT INTO account (username, wachtwoord)" + " "
				+ "VALUES (" + "'" + username + "'" + ", " + "'" + wachtwoord + "'" + ");";
		insertUpdateQuery(query);
	}

	public boolean login(String username, String password) {
		myConn = makeConnection();
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
}
