package uiUtils;

public class BookFinals {
	public static final int TITLE_LEN_MAX = 40;
	public static final int TITLE_LEN_MIN = 1;

	public static final int ISBN_LEN_MAX = 22;
	public static final int ISBN_LEN_MIN = 5;
	
	public static final int MAX_AUTHOR_NAME = 20;
	public static final int MIN_AUTHOR_NAME = 1;
	
	public static final int PRICE_MIN = 0;
	public static final int PRICE_MAX = 100;
	
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
}
