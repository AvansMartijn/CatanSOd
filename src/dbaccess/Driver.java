package dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Driver {
	private String url = "jdbc:mysql://databases.aii.avans.nl:3306/mfghaneg_db?useSSL=false";
	private String user = "mfghaneg";
	private String password = "Ab12345";
	private Connection myConn;
	private Statement myStmt;
	private ResultSet myRs;

	public Driver() {
		myConn = null;
		myStmt = null;
		myRs = null;
	}
	
	public void loadDriver() {
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection(url, user, password);

			// 2. Create a statement
			myStmt = myConn.createStatement();

			// 3. Execute SQL query
			myRs = myStmt.executeQuery("select * from account");

			// 4. Process the result set
			while (myRs.next()) {
				String username = myRs.getString("username"); // Name of column
				String password = myRs.getString(2); // Number of column
				System.out.println(username + " pw: " + password);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			
			if (myRs != null) {
				try {
					myRs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (myStmt != null) {
				try {
					myStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (myConn != null) {
				try {
					myConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
