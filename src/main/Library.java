package main;

import java.io.IOException;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import ui.AddBookWindow;

public class Library {

	public static void main(String[] args) throws IOException {
		if(!jsonUtils.JsonExceptionHandler.readSettings("src/settings.json")) {
			System.exit(jsonUtils.JsonExceptionHandler.getCurrentErr());
		}
		
		JSONObject settings = jsonUtils.JsonReader.getSettingsObj();
		database.Database.init((String)settings.get("databaseUrl"), (String)settings.get("username"), (String)settings.get("password"));
		
		try {
			database.Database.connect();
		} 
		catch (ClassNotFoundException e) {
			System.out.print("\nThe system failed to load the class");
			System.exit(1);
		}
		catch( SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		new ui.LoginWindow();
	}

}
