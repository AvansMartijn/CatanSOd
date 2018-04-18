package dbaccess;

public class PlayerDA {
	public PlayerDA() {
		
	}
	
	public void doQuery (String Query) {
		DataBaseApplication databeest = new DataBaseApplication();
		if ((databeest.loadDataBaseDriver("com.mysql.jdbc.Driver")) && (databeest.makeConnection())) {
			databeest.performQuery(Query);
		}
	}
	
	public void addThrows(int firstThrow, int secondThrow) {
		String throwQuery = "INSERT INTO spel (gedobbeld, laatste_worp_steen1, laatste_worp_steen2) "
				+ "VALUES (true, " + firstThrow + ", " + secondThrow + ");";
		doQuery(throwQuery);
	}
}
