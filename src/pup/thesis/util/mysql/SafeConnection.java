package pup.thesis.util.mysql;

import java.sql.Connection;
import java.sql.SQLException;


public class SafeConnection {

	DBQuerier q;

	public SafeConnection(DBQuerier querier, String Host, String Port,
			String DBName, String Username, String Password)
			throws SQLException, InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		this.q = querier;
		q.createConnection(this, Host, Port, DBName, Username, Password);
	}

	public void destroy() {
		q.removeConnection(this);
	}

	public Connection getConnectionObject() {
		return q.getConnection(this);
	}
}
