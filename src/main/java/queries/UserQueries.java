package queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataTestObject.User;
import dddUtils.DBUtility;

public class UserQueries {
	static DBUtility dbUtility;
	
	
	public static void init() {
		dbUtility = new DBUtility();
	}
//when we create object from DBUtility class, the constructor of DBUtility will be initialized
	// the construction contains getConnection() to connect the database
	
  public static List<User> getUsers(){
	  List<User> listOfUsers = new ArrayList<>();
	  init();
	  String query = "SELECT * FROM MAY2024;";
	  dbUtility.executeQuery(query);
	  ResultSet resultSet = dbUtility.getResultSet();
	  
	  try {
		while(resultSet.next()) {
			String emailId = resultSet.getString(1);
			String password = resultSet.getString(2);
			User user = new User(emailId, password);
			listOfUsers.add(user);
		}
		
		
		
	} catch (SQLException e) {
		e.printStackTrace();
		
	}
	  dbUtility.closeConnection();
	  return listOfUsers;
	  
  }
	
	
	
}
