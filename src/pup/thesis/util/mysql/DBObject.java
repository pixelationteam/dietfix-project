package pup.thesis.util.mysql;

import java.sql.ResultSet;
import java.util.List;

public interface DBObject {

	
	public String getType();
	public void insert();
	public void update();
	public void delete();
	public String toString();
	public String toJSON();
}
