package slack.tests;

import java.util.HashMap;
import java.util.Iterator;
import slack.utils.FileUtility;

public class BaseTest {

	public FileUtility file;
	static protected HashMap<String,String> resultsBag = new HashMap<String, String>();

	public BaseTest() {
		beforeTest();
	}

	public void beforeTest() {
		file = new FileUtility();
		file.loadConfigFile();
	}

	// Retrieves Data from CSV based on the Section and CSV FileName
	public Iterator<Object[]> getTestData(String section, String csvFileName) {
		FileUtility file = new FileUtility();
		return file.readCSVFile(section, csvFileName);
	}
}
