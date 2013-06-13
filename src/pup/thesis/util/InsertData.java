package pup.thesis.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import pup.thesis.helper.MysqlHelper;

public class InsertData {
	
	MysqlHelper sqlHelper;
	
	public static void main(String[] args) {
		InsertData data = new InsertData();
		data.insertData(1);
	}
	
	/**
	 * 
	 * Insert data from a file to the database
	 * 
	 * 1 -> insert adjective words(actions not included)
	 * 2 -> insert verb words(actions not included)
	 * 3 -> insert adverbs(actions not included)
	 * 4 -> insert nouns(actions not included)
	 * 
	 * @param dataCode
	 * @param file
	 * @return
	 */
	public boolean insertData(int dataCode) {
		sqlHelper = new MysqlHelper();
		String url = "";
		
		switch(dataCode) {
		
			case 1:
				url = "resources/Adjectives";
				break;
				
			case 2:
				url = "resources/Verb";
				break;
				
			case 3:
				url = "resources/Adverbs";
				break;
				
			case 4:
				url = "resources/Nouns";
				break;
		
			default:
				break;
		}
		
		boolean ctr = true;
		try {

			BufferedReader reader = new BufferedReader(new FileReader(url));
			String line = "";
			
			while((line = reader.readLine()) != null) {
				String statement = "insert into known_words values(null, '" +
						line + "', 'JJ', 0)";
				
				boolean flag = sqlHelper.updateDb(statement);
				
				if(flag) {
					ctr = true;
				}
				else {
					ctr = false;
				}
				System.out.println(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ctr;
		
	}
	
}
