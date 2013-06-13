package pup.thesis.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import pup.thesis.util.ClientData;
import pup.thesis.util.mysql.SafeConnection;

/**
 * This class provides basic functionalities for sql.
 * It uses the mysql connector to connect with the 
 * mysql database.
 * 
 * since 05-20-2013
 * @author paulzed
 *
 */
public class MysqlHelper extends MysqlAuth {
	
	private Connection con = null;
	private Statement st = null;
	private ResultSet set = null;
	
	//SQL Statement Strings
	//----------------------------
	private String where = "";
	private String select = "";
	private String orderBy = "";
	private String groupBy = "";
	private String from = "";
	//----------------------------
	
	/**
	 * 
	 * @return
	 */
	private boolean initMySql() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
			if(!con.isValid(3)) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static Connection createDietfixConnection(ClientData clientdata) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String dbHost,dbPort,dbName,dbUn,dbPw;
        dbHost = "localhost";
        dbPort = "3306";
        dbUn = "root";
        dbPw = "";
        dbName = "dietfix";
        return new SafeConnection(clientdata.getQuerier(),dbHost, dbPort, dbName, dbUn, dbPw).getConnectionObject();
    }
	
	public static Connection createWordnetConnection(ClientData clientdata) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String dbHost,dbPort,dbName,dbUn,dbPw;
        dbHost = "localhost";
        dbPort = "3306";
        dbUn = "root";
        dbPw = "";
        dbName = "Wordnet30";
        return new SafeConnection(clientdata.getQuerier(),dbHost, dbPort, dbName, dbUn, dbPw).getConnectionObject();
    }
    public static Connection createFoodDBConnection(ClientData clientdata) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String dbHost,dbPort,dbName,dbUn,dbPw;
        dbHost = "localhost";
        dbPort = "3306";
        dbUn = "root";
        dbPw = "";
        dbName = "sr25food";
        return new SafeConnection(clientdata.getQuerier(),dbHost, dbPort, dbName, dbUn, dbPw).getConnectionObject();
    }
	
	/**
	 * Gateway for queries regarding updating the database
	 * (insert, delete and update)
	 * 
	 * @param statement
	 * @return
	 */
	public boolean updateDb(String statement) {
		statement = filterQuery(statement);
		initMySql();
		
		try {
			st = con.createStatement();
			st.executeUpdate(statement);
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param tableName
	 * @return
	 */
	public ResultSet selectAll(String tableName) {
		try {
			st = con.createStatement();
			set = st.executeQuery("SELECT * FROM " + tableName);
		} catch(Exception e) {
			e.printStackTrace();
		} 
		return set;
	}
	
	/**
	 * 
	 * 
	 * @param fields
	 */
	public void setSelect(String fields) {
		fields = filterQuery(fields);
		if(this.select.isEmpty()) {
			this.select = fields;
		} else {
			this.select += " " + fields;
		}
	}
	
	/**
	 * 
	 * @param groupings
	 */
	public void setGroupBy(String groupings) {
		if(this.groupBy.isEmpty()) {
			this.groupBy = groupings;
		} else {
			this.groupBy += " " + groupings;
		}
	}
	
	/**
	 * 
	 * @param order
	 */
	public void setOrderBy(String order) {
		if(this.orderBy.isEmpty()) {
			this.orderBy = order;
		} else {
			orderBy += " " + order;
		}
	}
	
	/**
	 * 
	 * @param statement
	 */
	public void setWhere(String statement) {
		statement = filterQuery(statement);
		if(this.where.isEmpty()) {
			this.where = statement;
		} else {
			this.where += " " + statement;
		}
	}
	
	/**
	 * 
	 * @param query
	 * @return
	 */
	public ResultSet executeQuery(String query) {
		try {
			initMySql();
			query = filterQuery(query);
			st = con.createStatement();
			set = st.executeQuery(query);
		} catch(Exception e) {
			e.printStackTrace();
		} 
		
		return set;
	}
	
	/**
	 * 
	 * @return
	 */
	public ResultSet executeQuery() {
		try {
			initMySql();
			String sql = select + " " + from + " " + where + " " + groupBy + " " + orderBy;
			sql = filterQuery(sql);
			st = con.createStatement();
			set = st.executeQuery(sql);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return set;
	}
	
	/**
	 * 
	 * 
	 */
	public void resetQuery() {
		select = "";
		where = "";
		orderBy = "";
		groupBy = "";
		from = "";
	}
	
	public void closeConnections() {
		try {
			set.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param query
	 * @return
	 */
	private String filterQuery(String query) {
		return query.replace("#", "").replace("--", "");
	}
}
