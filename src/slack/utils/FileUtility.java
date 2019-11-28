package slack.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FileUtility {

	public static Properties configFile;

	public Iterator<Object[]> readCSVFile(String section, String csvFileName) {
		try {
			List<Object[]> testCases = new ArrayList<>();

			String[] data = null;
			String line = "";
			String cvsSplitBy = ",";

			String path = getCSVFile(section, csvFileName);
			FileReader file = new FileReader(path);
			BufferedReader reader = new BufferedReader(file);
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				// use comma as separator
				data = line.split(cvsSplitBy);
				// Hack to remove BOM character from the UTF-8 CSV File""
				data[0] = data[0].contains("﻿") ? data[0].replace("﻿", "") : data[0];
				testCases.add(data);
			}
			reader.close();

			return testCases.iterator();
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public String getCSVFile(String section, String csvName) {

		JSONParser parser = new JSONParser();
		String csvPath = "";

		try {
			FileReader reader = new FileReader(configFile.getProperty("csvPathFile"));
			JSONObject jsonObject = (JSONObject) parser.parse(reader);
			JSONObject module = (JSONObject) jsonObject.get(section);
			csvPath = (String) module.get(csvName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return csvPath;
	}

	public void loadConfigFile() {
		try {
			configFile = new Properties();
			InputStream file = new FileInputStream("./src/slack/config.properties");
			configFile.load(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
