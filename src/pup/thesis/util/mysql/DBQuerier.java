package pup.thesis.util.mysql;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import pup.thesis.logging.App;
import pup.thesis.logging.Modules;

public class DBQuerier {

	HashMap<SafeConnection, Connection> cons = new HashMap<SafeConnection, Connection>();
	
	public static void start() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver).newInstance();
	}
	public static void stop() throws SQLException{
		 Enumeration<Driver> drivers = DriverManager.getDrivers();
         App.log(Modules.SYSTEM,"");
	        while (drivers.hasMoreElements()) {
	            Driver driver = drivers.nextElement();
	            try {
	                DriverManager.deregisterDriver(driver);
	                App.log(Modules.SYSTEM,String.format("deregistering jdbc driver: %s", driver));
	            } catch (SQLException e) {
	                App.log(Modules.SYSTEM,String.format("Error deregistering driver %s", driver), e);
	            }

	        }
	}
	
	void createConnection(SafeConnection sc, String Host, String Port,
			String DBName, String Username, String Password)
			throws SQLException, ClassNotFoundException {
		String url2 = "jdbc:mysql://" + Host + ":" + Port + "/";
		// String url2 = "jdbc:mysql://localhost:3306/";
		
		Connection ccon = DriverManager.getConnection(url2 + DBName, Username,
				Password);
		cons.put(sc, ccon);

	}

	public int closeAllConnection() throws SQLException {

		int c = 0;
		for (Connection con : cons.values()) {
			con.close();
			c++;
		}

		return c;

	}

	Connection getConnection(SafeConnection sc) {
		return cons.get(sc);
	}

	void removeConnection(SafeConnection sc) {
		if (cons.containsKey(sc)) {
			try {
				if (!cons.get(sc).isClosed()) {
					cons.get(sc).close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				App.log(Modules.SYSTEM,"MysqlQuerier:", e.getMessage());
			}
			cons.remove(sc);
		}
	}

}
