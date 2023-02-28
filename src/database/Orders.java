package database;

public class Orders {
	private int id, quantity;

	private String date;
	
	private User user;
	
	private Book book;
	
	public Orders(int quantity, int bookId, int userId, String date) {
		user = new User();
		book = new Book();
		
		this.quantity = quantity;
		this.book.setId(bookId);
		this.user.setIdUser(userId);
		this.date = date;
	}
	
	public Orders(int id, String title, int qtnt, double price, String isbn) {
		user = new User();
		book = new Book();
		
		this.id = id;
		this.book.setTitle(title);
		this.quantity = qtnt;
		this.book.setPrice(price);
		this.book.setISBN(isbn);
	}
	
	public int getId() {
		return id;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public int getBookId() {
		return book.getId();
	}
	
	public int getUserId() {
		return user.getIdUser();
	}
	
	public String getDate() {
		return date;
	}
	
	public User getUserObj() {
		return user;
	}
	
	public Book getBookObj() {
		return book;
	}
}
