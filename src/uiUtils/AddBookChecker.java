package uiUtils;

import java.sql.SQLException;

public class AddBookChecker {
	public static final int MAX_LEN_TITLE = 20;
	
	public static final int ISBN_LEN = 22;
	
	public static final int authorName = 20;
	
	public static final double priceMax = 100;
	
	public static boolean handleName(String s, int minLen, int maxLen) {
		if(s.length() < minLen || s.length() > maxLen) {	
			return false;
		}
		
		return true;
	}
	
	public static boolean handleISBN(String s, int minLen, int maxLen) {
		if(s.length() < minLen || s.length() > maxLen) {
			return false;
		}
		if(s.contains(" ")) {
			return false;
		}
		try {
			if(database.Database.existISBN(s)) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
}
