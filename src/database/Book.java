package database;

public class Book {
	private int id;
	private double price;
	private String title;
	private String ISBN;
	private String authorFName;
	private String authorLName;
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
        if (!(o instanceof Book)) {
            return false;
        }
        
        Book c = (Book) o;
        return 
        	   id == c.id && 
        	   price == c.price && 
        	   title.equals(c.title) && 
        	   ISBN.equals(c.ISBN) && 
        	   authorFName.equals(c.authorFName) && 
        	   authorLName.equals(c.authorLName);
	}
	
	public Book(int id_, Double price_, String title_, String ISBN_, String aFN_, String aLN_) {
		id = id_;
		price = price_;
		title = title_;
		ISBN = ISBN_;
		authorFName = aFN_;
		authorLName = aLN_;
	}
	
	public Book(Double price_, String title_, String ISBN_, String aFN_, String aLN_) {
		price = price_;
		title = title_;
		ISBN = ISBN_;
		authorFName = aFN_;
		authorLName = aLN_;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getAuthorFName() {
		return authorFName;
	}

	public void setAuthorFName(String authorFName) {
		this.authorFName = authorFName;
	}

	public String getAuthorLName() {
		return authorLName;
	}

	public void setAuthorLName(String authorLName) {
		this.authorLName = authorLName;
	}
}
