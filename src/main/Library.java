package main;

import java.io.IOException;

public class Library {

	public static void main(String[] args) throws IOException {
		database.Database.init("jdbc:mysql://127.0.0.1:3306/javaDB", "root", "MySQL85.#(6@");
		
		if(!database.ExceptionsHandler.handleConnection()) {
			System.exit(database.ExceptionsHandler.CONNECTION_ERR);
		}
		
		new ui.LoginWindow();
	}

}
