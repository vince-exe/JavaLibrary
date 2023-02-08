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
		PreparedStatement loginStmt = conn.prepareStatement("SELECT users.id FROM users WHERE users.email = ? AND users.psw = ?;");
		
		loginStmt.setString(1,  email);
		loginStmt.setString(2, password);
		
		return loginStmt.executeQuery().next();
	}
	
	public static boolean emailExist(String email) throws SQLException {
		PreparedStatement checkStmt = conn.prepareStatement("SELECT users.id FROM users WHERE users.email = ?");
		checkStmt.setString(1, email);
		
		return checkStmt.executeQuery().next();
	}
	
	private static int getLastPersonID() {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT id FROM persons ORDER BY id DESC LIMIT 1;");
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("id");
			}
			return -1;
			
		} catch (SQLException e) {
			return -1;
		}
	}
	
	public static boolean registration(User usr) {
		try {
			PreparedStatement personStmt = conn.prepareStatement("INSERT INTO persons(first_name, last_name, birth) VALUES (?, ?, ?);");
			personStmt.setString(1, usr.getFirstName());
			personStmt.setString(2, usr.getLastName());
			personStmt.setString(3, usr.getBDay());
			
			if(personStmt.executeUpdate() < 0) {
				System.out.print("\nPersona Fail");
				return false;
			}
			
			int personId = Database.getLastPersonID();
			if(personId == -1) {
				return false;
			}
			
			
			PreparedStatement userStmt = conn.prepareStatement("INSERT INTO users(personId, username, email, psw) VALUES (?, ?, ?, ?);");
			userStmt.setInt(1, personId);
			userStmt.setString(2, usr.getUsername());
			userStmt.setString(3, usr.getEmail());
			userStmt.setString(4, usr.getPassword());
			
			if(userStmt.executeUpdate() < 0) {
				return false;
			}
			
			return true;
			
		} catch (SQLException e) {
			return false;
		}
	}
}
