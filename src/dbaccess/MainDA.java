package dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainDA {
	private static final String url = "jdbc:mysql://databases.aii.avans.nl:3306/mfghaneg_db?useSSL=false";
	private static final String user = "mfghaneg";
	private static final String password = "Ab12345";
	protected Connection myConn;
	private ChatDA chatDA;
	private AccountDA accountDA;

	public MainDA() {
		myConn = null;
	}


	public boolean loadDataBaseDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFoundException: " + e.getMessage());
			return false;
		}
		return true;
	}

	public Connection makeConnection() {
		try {
			myConn = DriverManager.getConnection(url, user, password);
		} catch (SQLException ex) {
			System.out.println("Connection failed");
			System.out.println("SQLException " + ex.getMessage());
			System.out.println("SQLState " + ex.getSQLState());
			System.out.println("VendorError " + ex.getErrorCode());
			return null;
		}
		return myConn;
	}

	public void insertUpdateQuery(String query) {
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
	}

	public void selectQuery() {
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

	public void createChatDA() {
		chatDA = new ChatDA();
	}
	
	public ChatDA getChatDA() {
		return chatDA;
	}
	
	public void createAccountDA() {
		accountDA = new AccountDA();
	}
	
	public AccountDA getAccountDA() {
		return accountDA;
	}

}
