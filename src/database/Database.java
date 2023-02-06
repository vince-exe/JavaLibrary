package database;

import java.sql.*;

public class Database {
	private static Connection conn;
	
	private static String dbUrl, username, password;
	
	public static void init(String dbUrl_, String username_, String password_) {
		dbUrl = dbUrl_;
		username = username_;
		password = password_;
	}
	
	public static void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		conn = DriverManager.getConnection(dbUrl, username, password);
	}
	
	public static boolean isLogged(String email, String password) throws SQLException {
		PreparedStatement loginStmt = conn.prepareStatement("SELECT admins.id FROM admins WHERE admins.email = ? AND admins.psw = ?;");
		
		loginStmt.setString(1,  email);
		loginStmt.setString(2, password);
		
		return loginStmt.executeQuery().next();
	}
}
