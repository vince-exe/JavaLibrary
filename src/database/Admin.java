package database;

public class Admin extends User {
	private int id;
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
        if (!(o instanceof Admin)) {
            return false;
        }
        
        Admin c = (Admin) o;

        return idPerson == c.idPerson && 
        	   id == c.id &&
        	   firstName.equals(c.firstName) &&
        	   lastName.equals(c.lastName) &&
        	   birdDDAte.equals(c.birdDDAte) && 
        	   email.equals(c.email) &&
        	   password.equals(c.password) &&
        	   username.equals(c.username) &&
        	   money == c.money &&
        	   idUser == c.idUser;
	}
	
	public Admin() {};
	
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
