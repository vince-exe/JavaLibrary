package database;

import java.sql.*;
import java.util.ArrayList;

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
	
	public static boolean updateBook(int id, Book book) {
		try {
			PreparedStatement updtStmt = conn.prepareStatement("UPDATE books SET "
					+ "price = ?,"
					+ "title = ?,"
					+ "ISBN = ?,"
					+ "authorFName = ?,"
					+ "authorLName = ? "
					+ "WHERE id = ?;");
			updtStmt.setDouble(1, book.getPrice());
			updtStmt.setString(2, book.getTitle());
			updtStmt.setString(3, book.getISBN());
			updtStmt.setString(4, book.getAuthorFName());
			updtStmt.setString(5, book.getAuthorLName());
			updtStmt.setInt(6, id);
			
			if(updtStmt.executeUpdate() < 0) {
				return false;
			}
			return true;
			
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static int getNOrders(int usrId) {
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("SELECT COUNT(*) FROM orders WHERE orders.userId = ?;");
			stmt.setInt(1, usrId);
			
			ResultSet res = stmt.executeQuery();
			res.next();
			return res.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}	
	}
	
	public static boolean existBook(String isbn, String title) throws SQLException {
		PreparedStatement bookStmt = conn.prepareStatement("SELECT id FROM books WHERE books.ISBN = ? OR books.title = ?;");
		bookStmt.setString(1, isbn);
		bookStmt.setString(2, title);
		
		return bookStmt.executeQuery().next();
	}
	
	public static boolean isRoot(int userId) throws SQLException {
		PreparedStatement checkStmt = conn.prepareStatement("SELECT admins.id FROM admins WHERE admins.userId = ?;");
		checkStmt.setInt(1, userId);
				
		return checkStmt.executeQuery().next();
	}
	
	public static int existUsername(String username) {
		try {
			PreparedStatement checkStmt = conn.prepareStatement("SELECT users.id FROM users WHERE users.username = ?;");
			checkStmt.setString(1, username);
			
			return (checkStmt.executeQuery().next()) ? 0 : 1;
			
		} catch (SQLException e) {
			return -1;
		}
		
	}
	
	public static boolean updateUsr(User user) {
		try {
			PreparedStatement personStmt = conn.prepareStatement("UPDATE persons SET"
					+ " first_name = ?, last_name = ?, birth = ?, money = ? WHERE persons.id = ?;");
			personStmt.setString(1, user.getFirstName());
			personStmt.setString(2, user.getLastName());
			personStmt.setString(3, user.getBDay());
			personStmt.setDouble(4, user.getMoney());
			personStmt.setInt(5, user.getIdPerson());
			
			if(personStmt.executeUpdate() < 0) {
				return false;
			}
			
		} catch (SQLException e) {
			return false;
		}
		
		try {
			PreparedStatement userStmt = conn.prepareStatement("UPDATE users SET"
					+ " username = ?, email = ?, psw = ? WHERE users.id = ?;");
			userStmt.setString(1, user.getUsername());
			userStmt.setString(2, user.getEmail());
			userStmt.setString(3, user.getPassword());
			userStmt.setInt(4, user.getIdUser());
			
			if(userStmt.executeUpdate() < 0) {
				return false;
			}
			
			return true;
			
		} catch (SQLException e) {
			return false;
		}
	}
	public static User getUser(int id) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("select "
				+ "persons.id,"
				+ "persons.first_name,"
				+ "persons.last_name,"
				+ "persons.birth,"
				+ "persons.money,"
				+ "users.id,"
				+ "users.email,"
				+ "users.psw,"
				+ "users.username "
				+ "FROM users JOIN persons ON users.personId = persons.id WHERE users.id = ?;");
		stmt.setInt(1, id);
		
		ResultSet result = stmt.executeQuery();
		
		if(!result.next()) { return null; }
		
		return new User(result.getInt(1), result.getString(2), result.getString(3), result.getDate(4).toString().replace('-', '/'), result.getDouble(5), result.getInt(6), result.getString(7), result.getString(8), result.getString(9));
	}
	
	public static User getUser(String email) {
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("select "
					+ "persons.id,"
					+ "persons.first_name,"
					+ "persons.last_name,"
					+ "persons.birth,"
					+ "persons.money,"
					+ "users.id,"
					+ "users.email,"
					+ "users.psw,"
					+ "users.username "
					+ "FROM users JOIN persons ON users.personId = persons.id WHERE users.email = ?;");
			stmt.setString(1, email);
			
			ResultSet result = stmt.executeQuery();
			if(!result.next()) { return null; }
			
			return new User(result.getInt(1), result.getString(2), result.getString(3), result.getDate(4).toString().replace('-', '/'), result.getDouble(5), result.getInt(6), result.getString(7), result.getString(8), result.getString(9));
		} 
		catch (SQLException e) {
			return null;
		}		
	}
	
	public static ArrayList<User> getUsers() {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT "
					+ "users.id,"
					+ "first_name,"
					+ "last_name,"
					+ "birth,"
					+ "email,"
					+ "psw,"
					+ "username"
					+ " FROM USERS JOIN persons ON users.personId = persons.id");
			
			ArrayList<User> usersList = new ArrayList<User>();
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				usersList.add(new User(
						result.getInt(1),
						result.getString(2),
						result.getString(3),
						result.getString(4),
						result.getString(5),
						result.getString(6),
						result.getString(7)));
			}
			return usersList;
			
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static ArrayList<Admin> getAdmins() {
		try {
			PreparedStatement adminStmt = conn.prepareStatement("SELECT "
					+ "users.id,"
					+ "admins.id,"
					+ "first_name,"
					+ "last_name,"
					+ "birth,"
					+ "email,"
					+ "psw,"
					+ "username"
					+ " FROM ADMINS JOIN users ON userId = users.id JOIN persons ON users.personId = persons.id");
			
			ArrayList<Admin> adminsList = new ArrayList<Admin>();
			ResultSet result = adminStmt.executeQuery();
			
			while(result.next()) {
				adminsList.add(new Admin(
						result.getInt(1),
						result.getInt(2),
						result.getString(3),
						result.getString(4),
						result.getString(5),
						result.getString(6),
						result.getString(7),
						result.getString(8)));
			}
			return adminsList;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
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
	
	public static int getLastUserID() {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT id FROM users ORDER BY users.id DESC LIMIT 1");
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("id");
			}
			return -1;
			
		} catch (SQLException e) {
			return -1;
		}
	}
	
	public static int getUserID(String email) {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT id FROM users WHERE users.email = ?");
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("id");
			}
			return -1;
			
		} catch (SQLException e) {
			return -1;
		}
	}
	
	public static boolean deleteUser(int id) {
		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE users.id = ?");
			stmt.setInt(1, id);
			
			if(stmt.executeUpdate() < 0) {
				return false;
			}
			return true;
			
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static boolean deletePerson(int id) {
		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM persons WHERE persons.id = ?");
			stmt.setInt(1, id);
			
			if(stmt.executeUpdate() < 0) {
				return false;
			}
			return true;
			
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static boolean registrationAdmin(int userID) {
		try {
			PreparedStatement adminStmt = conn.prepareStatement("INSERT INTO admins(userId) VALUES (?);");
			adminStmt.setInt(1, userID);
			
			if(adminStmt.executeUpdate() < 0) {
				return false;
			}
			return true;
		}
		catch(SQLException e) {
			return false;
		}
	}
	
	public static boolean registration(User usr) {
		try {
			PreparedStatement personStmt = conn.prepareStatement("INSERT INTO persons(first_name, last_name, birth, money) VALUES (?, ?, ?, ?);");
			personStmt.setString(1, usr.getFirstName());
			personStmt.setString(2, usr.getLastName());
			personStmt.setString(3, usr.getBDay());
			personStmt.setDouble(4, usr.getMoney());
			
			if(personStmt.executeUpdate() < 0) {
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

	public static boolean addBook(Book book) {
		try {
			PreparedStatement addBookStmt = conn.prepareStatement("INSERT INTO books(price, title, ISBN, authorFName, authorLName) VALUES (?, ?, ?, ?, ?);");
			addBookStmt.setDouble(1, book.getPrice());
			addBookStmt.setString(2, book.getTitle());
			addBookStmt.setString(3, book.getISBN());
			addBookStmt.setString(4, book.getAuthorFName());
			addBookStmt.setString(5, book.getAuthorLName());
			
			if(addBookStmt.executeUpdate() < 0) {
				return false;
			}
			return true;
		}
		catch(SQLException e) {
			return false;
		}
	}
	
	public static ArrayList<Book> getBooks() {
		try {
			PreparedStatement getBooksStmt = conn.prepareStatement("SELECT * FROM books");
			
			ResultSet result = getBooksStmt.executeQuery();
			ArrayList<Book> array = new ArrayList<Book>();
			
			while(result.next()) {
				array.add(new Book(
						result.getInt(1),
						result.getDouble(2),
						result.getString(3),
						result.getString(4),
						result.getString(5),
						result.getString(6)));
			}
			
			return array;
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	public static boolean deleteBook(int id) {
		try {
			PreparedStatement delStmt = conn.prepareStatement("DELETE FROM books WHERE books.id = ?;");
			delStmt.setInt(1, id);
			
			if(delStmt.executeUpdate() < 0) {
				return false;
			}
			return true;
		}
		catch(SQLException e) {
			return false;
		}
	}
}
