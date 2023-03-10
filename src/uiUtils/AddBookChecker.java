package uiUtils;

import java.sql.SQLException;

public class AddBookChecker {
	
	public static boolean handleName(String s, String ignore, String subject, int minLen, int maxLen) {
		if(s.equals(ignore) || s.length() < minLen || s.length() > maxLen) {
			DialogsHandler.authorName(null, subject, minLen, maxLen);
			return false;
		}
		
		return true;
	}
	
	public static boolean handleIsbnTitle(String s, String ignore, String s1, String ignore1, int minLenIsbn, int maxLenIsbn, int minLenTitle, int maxLenTitle) {
		/* ISBN */
		if(s.equals(ignore) || s.contains(" ") || s.length() < minLenIsbn || s.length() > maxLenIsbn) {
			DialogsHandler.isbnERR(null, minLenIsbn, maxLenIsbn);
			return false;
		}
		/* TITLE */
		if(s1.equals(ignore1) || (s1.length() < minLenTitle || s.length() > maxLenTitle)) {
			DialogsHandler.titleErr(null, minLenTitle, maxLenTitle);
			return false;
		}
	
		try {
			if(database.Database.existBook(s, s1)) {
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
	
	public static boolean handleAddBook(String autFN, String autLN, String price, String ISBN, String title) {
		return handleName(autFN, "Author Name", "first", BookFinals.MIN_AUTHOR_NAME, BookFinals.MAX_AUTHOR_NAME) && 
			   handleName(autLN, "Author Surname", "last", BookFinals.MIN_AUTHOR_NAME, BookFinals.MAX_AUTHOR_NAME) &&
			   BookFinals.handlePrice(price, BookFinals.PRICE_MIN, BookFinals.PRICE_MAX) &&
			   handleIsbnTitle(ISBN, "ISBN", title, "Book Title", BookFinals.ISBN_LEN_MIN, BookFinals.ISBN_LEN_MAX, BookFinals.TITLE_LEN_MIN, BookFinals.TITLE_LEN_MAX);
	}
}
