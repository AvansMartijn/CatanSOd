package dbaccess;

public class MainDA {
	public MainDA() {
		DataBaseApplication databeest = new DataBaseApplication();
		if ((databeest.loadDataBaseDriver("com.mysql.jdbc.Driver"))
			&& (databeest.makeConnection())) {
			databeest.performQuery("select * from account LIMIT 5");;
		}
	}
}
