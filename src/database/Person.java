package database;

public abstract class Person {
	protected int idPerson;
	
	protected String firstName, lastName, birdDDAte;
	
	protected double money;
	
	public Person() {}
	
	public Person(int id_, String fN, String lN, String birdD) {
		idPerson = id_;
		firstName = fN;
		lastName = lN;
		birdDDAte = birdD;
	}
	
	public Person(String fN, String lN, String birdD, double my) {
		firstName = fN;
		lastName = lN;
		birdDDAte = birdD;
		money = my;
	}
	
	public Person(int id_, String fN, String lN, String birdD, double my) {
		idPerson = id_;
		firstName = fN;
		lastName = lN;
		birdDDAte = birdD;
		money = my;
	}
	
	public Person(String fN, String lN, String birdD) {
		firstName = fN;
		lastName = lN;
		birdDDAte = birdD;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getBDay() {
		return birdDDAte;
	}
	
	public int getIdPerson() {
		return idPerson;
	}
	
	public double getMoney() {
		return money;
	}
	
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBirdDDAte(String birdDDAte) {
		this.birdDDAte = birdDDAte;
	}

	public void setMoney(double money) {
		this.money = money;
	}
}
