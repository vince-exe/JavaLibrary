package main;

import java.io.IOException;
import org.json.simple.JSONObject;

public class Library {

	public static void main(String[] args) throws IOException {
		if(!jsonUtils.JsonExceptionHandler.readSettings("src/settings.json")) {
			System.exit(jsonUtils.JsonExceptionHandler.getCurrentErr());
		}
		
		JSONObject settings = jsonUtils.JsonReader.getSettingsObj();
		database.Database.init((String)settings.get("databaseUrl"), (String)settings.get("username"), (String)settings.get("password"));
		
		if(!database.ExceptionsHandler.handleConnection()) {
			System.exit(database.ExceptionsHandler.CONNECTION_ERR);
		}
		
		new ui.LoginWindow();
	}

}
