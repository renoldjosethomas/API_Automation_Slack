package slack.services;

import java.io.FileReader;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import slack.utils.FileUtility;

public class BaseService {
	
	protected RequestSpecification request;
	static final protected String accessToken = "xoxp-830515513683-841525594932-836391210577-16ed6392a046764b1846b9eda662e471";
	
	public void initializeRequest() {
		RestAssured.baseURI = FileUtility.configFile.getProperty("url");
		request = RestAssured.given()
				.header("Cache-Control","no-cache")
				.header("Content-Type","application/x-www-form-urlencoded")
				.when();
	}

	protected String getAPI(String section, String name) {
		initializeRequest();
		
		JSONParser parser = new JSONParser();
		String API = "";

		try {
			Reader reader = new FileReader(FileUtility.configFile.getProperty("apiPathFile"));
			JSONObject jsonObject = (JSONObject) parser.parse(reader);
			JSONObject module = (JSONObject) jsonObject.get(section);
			API = (String) module.get(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return API;
	}
}
