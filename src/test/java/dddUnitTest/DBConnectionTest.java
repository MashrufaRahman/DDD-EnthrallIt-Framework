package dddUnitTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

//This is an unit test to check the connectivity is working or not
// this class is not related to our framework
public class DBConnectionTest {

	@Test

	public void dbConnection() throws SQLException {
		String createTable = "CREATE TABLE MAY2024(EMAIL_ID VARCHAR(30) NOT NULL PRIMARY KEY,PASSWORD VARCHAR(30));";
		String insertData = "INSERT INTO MAY2024 (EMAIL_ID, PASSWORD)VALUES('tasnimchowdhury.qa@gmail.com', 'Darimee2010'),('Kaylith1@gmail.com', 'Abc12345%');";
		String runTheQuery = "SELECT * FROM MAY2024";

		/*
		 * INSERT INTO MAY 2024 (EMAIL_ID,
		 * PASSWORD)VALUES('tasnimchowdhury.qa@gmail.com',
		 * 'Darimee2010'),('Kaylith1@gmail.com', 'Abc12345%');
		 * 
		 */

		/*
		 * The steps need to be follow: - Step 1: Register the Database by DriverManager
		 * from java.sql - Note: We don't need to register separately -- when we added
		 * dependency - Step 2: Connection from java.sql - Step 3: Statement from
		 * java.sql, accept the query and execute the query - Step 4: ResultSet from
		 * java.sql
		 */

		/*
		 * To Find the local host, user id etc from below: PostgreSQL16 --- right click
		 * -- properties -- connection
		 */

		// getConnection returns the Connection
		// https://jdbc.postgresql.org/documentation/use/

		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/employees_db", "postgres",
				"hr");

		// Create statement
		Statement statement = connection.createStatement();
		// statement.execute(createTable);
		// statement.execute(insertData);
		statement.execute(runTheQuery);

		ResultSet resultSet = statement.getResultSet();
		resultSet.next();
		// System.out.println(resultSet.getString(1));
		System.out.println(resultSet.getString(2));

		connection.close();
	}
}
