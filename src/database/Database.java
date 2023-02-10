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
	
	public static boolean isRoot(int userId) throws SQLException {
		PreparedStatement checkStmt = conn.prepareStatement("SELECT admins.id FROM admins WHERE admins.userId = ?;");
		checkStmt.setInt(1, userId);
				
		return checkStmt.executeQuery().next();
	}
	
	public static database.User getUser(int id) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("select "
				+ "persons.id,"
				+ "persons.first_name,"
				+ "persons.last_name,"
				+ "persons.birth,"
				+ "users.id,"
				+ "users.email,"
				+ "users.psw,"
				+ "users.username "
				+ "FROM users JOIN persons ON users.personId = persons.id WHERE users.id = ?;");
		/*
		PreparedStatement stmt = conn.prepareStatement("select "
				+ "persons.id,"
				+ "persons.first_name,"
				+ "persons.last_name,"
				+ "persons.birth,"
				+ "users.id,"
				+ "users.email,"
				+ "users.psw,"
				+ "users.username"
				+ "FROM users JOIN persons ON users.personId = persons.id WHERE users.id = ?;");
				*/
		stmt.setInt(1, id);
		
		ResultSet result = stmt.executeQuery();
		
		if(!result.next()) { return null; }
		
		return new database.User(result.getInt(1), result.getString(2), result.getString(3), result.getDate(4).toString(), result.getInt(5), result.getString(6), result.getString(7), result.getString(8));
	}
	
	public static int[] isLogged(String email, String password) throws SQLException {
		PreparedStatement loginStmt = conn.prepareStatement("SELECT users.id FROM users WHERE users.email = ? AND users.psw = ?;");
		
		loginStmt.setString(1,  email);
		loginStmt.setString(2, password);
		
		int[] returnValue = new int[2];
		ResultSet result = loginStmt.executeQuery(); 
		
		if(result.next()) {
			returnValue[0] = 1;
			returnValue[1] = result.getInt(1);
			return returnValue;
		}
		
		returnValue[0] = 0;
		return returnValue;
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
