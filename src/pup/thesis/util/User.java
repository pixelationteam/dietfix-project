package pup.thesis.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import pup.thesis.helper.MysqlHelper;

public class User {
	
	private MysqlHelper helper;
	
	public boolean isUsernameLengthValid(String username) {
		return (username.length() > 7) ? true : false;
	}
	
	public Map<String, String> getUserId(String username) {
		
		ResultSet set = getUserInfo(username);
		Map<String, String> info = new HashMap<String, String>();
		
		try {
			while(set.next()) {
				info.put("userId", set.getString(1));
				info.put("firstName", set.getString("first_name"));
				info.put("middleName", set.getString("middle_name"));
				info.put("lastName", set.getString("last_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return info;
	}
	
	public ResultSet getUserInfo(String username) throws NullPointerException {
		ResultSet set = null;
		
		helper = new MysqlHelper();
		
		String query = "SELECT * FROM user";
		
		set = helper.executeQuery(query);
		
		return set;
	}
	
	/*
	public String getSalt(String username) {
		
	}
	
	public String hasUsername(String username) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		
	}
	
	public String hasSalt(String salt) {
		
	}
	*/
	
	public boolean validateUser(String username, String password) {
		
		if(!isUsernameLengthValid(username)) {
			return false;
		}
		
		try {
			ResultSet set = getUserInfo(username);
			while(set.next()) {
				if(set.getString("username").equals(username)) {
					if(set.getString("password").equals(password)) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
