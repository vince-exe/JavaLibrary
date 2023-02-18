package uiUtils;

import java.sql.SQLException;

public class UpdtBookChecker {
	public static boolean handleName(String s, String subject, int minLen, int maxLen) {
		if(s.length() < minLen || s.length() > maxLen) {
			DialogsHandler.authorName(null, subject, minLen, maxLen);
			return false;
		}
		
		return true;
	}
	
	public static boolean handleIsbnTitle(String s, String s1, int minLenIsbn, int maxLenIsbn, int minLenTitle, int maxLenTitle) {
		/* ISBN */
		if(s.contains(" ") || s.length() < minLenIsbn || s.length() > maxLenIsbn) {
			DialogsHandler.isbnERR(null, minLenIsbn, maxLenIsbn);
			return false;
		}
		/* TITLE */
		if((s1.length() < minLenTitle || s.length() > maxLenTitle)) {
			DialogsHandler.titleErr(null, minLenTitle, maxLenTitle);
			return false;
		}
		
		return true;
	}
	
	public static boolean existBook(String isbn, String title) {
		try {
			if(database.Database.existBook(isbn, title)) {
				DialogsHandler.bookExist(null);
				return false;
			}
			return true;
		} 
		catch (SQLException e) {
			DialogsHandler.SQLErr(null, e.getMessage());
			return false;
		}
	}
}
