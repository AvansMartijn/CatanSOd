package dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBaseApplication {
	private Connection m_Conn;
	private ResultSet res;

	public DataBaseApplication() {
		m_Conn = null;
		res = null;

	}

	public boolean loadDataBaseDriver(String driverName) {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFoundException: " + e.getMessage());
			return false;
		}
		return true;
	}

	public void performQuery(String query) {
		Statement stmt = null;
		try {
			stmt = m_Conn.createStatement();
			res = stmt.executeQuery(query);
			while (res.next()) {
				String username = res.getString(1);
				String wachtwoord = res.getString(2);
				System.out.println(username + ", wachtwoord: " + wachtwoord);
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean makeConnection() {
		try {
			m_Conn = DriverManager.getConnection("jdbc:mysql://databases.aii.avans.nl:3306/mfghaneg_db?useSSL=false", "mfghaneg", "Ab12345");
		} catch (SQLException ex) {
			System.out.println("Connection failed");
			System.out.println("SQLException " + ex.getMessage());
			System.out.println("SQLState " + ex.getSQLState());
			System.out.println("VendorError " + ex.getErrorCode());
			return false;
		}
		
		return true;
	}

}
