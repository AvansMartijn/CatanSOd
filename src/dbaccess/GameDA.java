package dbaccess;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDA {
	public GameDA() {

	}

	public void doInsertQuery(String Query) {
		DataBaseApplication dba = new DataBaseApplication();
		if ((dba.loadDataBaseDriver("com.mysql.jdbc.Driver")) && (dba.makeConnection())) {
			dba.insertQuery(Query);
		}
	}

	public void doQuery(String Query) {
		DataBaseApplication dba = new DataBaseApplication();
		if ((dba.loadDataBaseDriver("com.mysql.jdbc.Driver")) && (dba.makeConnection())) {
			ResultSet res = dba.performQuery(Query);
			try {
				while (res.next()) {
					// Make strings of the results. (See examples)
					String name = res.getString("objectnaam"); // Name of column
					String satOf = res.getString(2); // Number of column
					System.out.println(" - " + name + ", satellite of " + satOf + ".");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void addThrows(int firstThrow, int secondThrow) {
		String throwQuery = "INSERT INTO spel (idspel, gedobbeld, laatste_worp_steen1, laatste_worp_steen2) "
				+ "VALUES (1, true, " + firstThrow + ", " + secondThrow + ");";
		doQuery(throwQuery);
	}
}
