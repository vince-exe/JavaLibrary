package uiUtils;

import java.sql.SQLException;

public class AddBookChecker {
	public static final int TITLE_LEN_MAX = 40;
	public static final int TITLE_LEN_MIN = 1;

	public static final int ISBN_LEN_MAX = 22;
	public static final int ISBN_LEN_MIN = 5;
	
	public static final int MAX_AUTHOR_NAME = 20;
	public static final int MIN_AUTHOR_NAME = 5;
	
	public static final int PRICE_MIN = 0;
	public static final int PRICE_MAX = 100;
	
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
	
	public static boolean handlePrice(String s, int min, int max) {
		s = s.replace(',', '.');

		double price;
		try {
			price = Double.parseDouble(s);
		}
		catch(Exception e) {
			DialogsHandler.priceErr(null, min, max);
			return false;
		}
		
		if(price < min || price > max) {
			DialogsHandler.priceErr(null, min, max);
			return false;
		}
		
		return true;
	}
	
	public static boolean handleAddBook(String autFN, String autLN, String price, String ISBN, String title) {
		return handleName(autFN, "Author Name", "first", MIN_AUTHOR_NAME, MAX_AUTHOR_NAME) && 
			   handleName(autLN, "Author Surname", "last", MIN_AUTHOR_NAME, MAX_AUTHOR_NAME) && 
			   handlePrice(price, PRICE_MIN, PRICE_MAX) &&
			   handleIsbnTitle(ISBN, "ISBN", title, "Book Title", ISBN_LEN_MIN, ISBN_LEN_MAX, TITLE_LEN_MIN, TITLE_LEN_MAX);
	}
}
