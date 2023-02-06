package database;

import java.sql.SQLException;

public class ExceptionsHandler {
	public static final int CONNECTION_ERR = -5;
	
	public static boolean handleConnection() {
		try {
			database.Database.connect();
			return true;
		} 
		catch (ClassNotFoundException e) {
			System.out.print("\nThe system failed to load the class");
			return false;
		}
		catch( SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
