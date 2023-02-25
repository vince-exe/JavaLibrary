package database;

public class User extends Person {
	protected int idUser;
	
	protected String email, password, username;
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
        if (!(o instanceof User)) {
            return false;
        }
        
        User c = (User) o;
        return idPerson == c.idPerson && 
        	   firstName.equals(c.firstName) &&
        	   lastName.equals(c.lastName) &&
        	   birdDDAte.equals(c.birdDDAte) && 
        	   email.equals(c.email) &&
        	   password.equals(c.password) &&
        	   username.equals(c.username) &&
        	   money == c.money &&
        	   idUser == c.idUser;


	}
	
	public User() {};
	
	public User(int idPerson, String fN, String lN, String birdD, int idUser_, String email_, String password_, String username_) {
		super(idPerson, fN, lN, birdD);
		idUser = idUser_;
		email = email_;
		password = password_;
		username = username_;
		money = 0;
	}
	
	public User(int idPerson, String fN, String lN, String birdD, double money, int idUser_, String email_, String password_, String username_) {
		super(idPerson, fN, lN, birdD, money);
		idUser = idUser_;
		email = email_;
		password = password_;
		username = username_;
	}
	
	public User(String fN, String lN, String birdD, String email_, String password_, String username_, double money) {
		super(fN, lN, birdD, money);
		email = email_;
		password = password_;
		username = username_;
	}
	
	public User(int userId, String fN, String lN, String birdD, String email_, String password_, String username_) {
		super(fN, lN, birdD);
		idUser = userId;
		email = email_;
		password = password_;
		username = username_;
		money = 0;
	}
	
	public User(int userId, String fN, String lN, String birdD, String email_, String password_, String username_, double money) {
		super(fN, lN, birdD, money);
		idUser = userId;
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
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
