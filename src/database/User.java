package database;

public class User extends Person {
	private int idUser;
	
	private String email, password, username;
	
	public User(int idPerson, String fN, String lN, String birdD, int idUser_, String email_, String password_, String username_) {
		super(idPerson, fN, lN, birdD);
		idUser = idUser_;
		email = email_;
		password = password_;
		username = username_;
	}
	
	public User(String fN, String lN, String birdD, String email_, String password_, String username_) {
		super(fN, lN, birdD);
		email = email_;
		password = password_;
		username = username_;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getIdUser() {
		return idUser;
	}
}
