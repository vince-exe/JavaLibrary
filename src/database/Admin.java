package database;

public class Admin extends User {
	private int id;

	public Admin(int usrId, int adminId, String fN, String lN, String birth, String email, String password, String username) {
		super(usrId, fN, lN, birth, email, password, username);
		id = adminId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
