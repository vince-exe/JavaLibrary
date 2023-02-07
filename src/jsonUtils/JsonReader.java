package jsonUtils;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {
	private static JSONObject settingsJson;
	
	public static void readSettings(String path) throws IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		
		FileReader reader = new FileReader(path);
		
		settingsJson = (JSONObject) jsonParser.parse(reader);
	}
	
	public static JSONObject getSettingsObj() {
		return settingsJson;
	}
}
