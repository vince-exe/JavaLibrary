package database;

public abstract class Person {
	protected int idPerson;
	
	protected String firstName, lastName, birdDDAte;
	
	public Person(int id_, String fN, String lN, String birdD) {
		idPerson = id_;
		firstName = fN;
		lastName = lN;
		birdDDAte = birdD;
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
}
