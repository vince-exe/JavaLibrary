package jsonUtils;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class JsonExceptionHandler {
	private static final int PARSE_ERROR = -7;
	private static final int IO_ERROR = -6;
	
	private static int currentError = 0;
	
	public static boolean readSettings(String path) {
		try {
			JsonReader.readSettings(path);
			return true;
		} 
		catch (IOException e) {
			ui.DialogsHandler.readSettingsJsonIO();
			currentError = IO_ERROR;
			return false;
		}
		catch (ParseException e) {
			ui.DialogsHandler.readSettingsJson();
			currentError = PARSE_ERROR;
			return false;
		}
	}
	
	public static int getCurrentErr() {
		return currentError;
	}
}
