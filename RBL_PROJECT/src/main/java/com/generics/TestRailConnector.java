package com.generics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.ITestResult;

public class TestRailConnector {
	String PROJECT_ID = "1";
	APIClient client;
	Long SUITE_ID = (long) 1;
	public Pojo objPojo;
	private String TestCaseID;
	public static String Tagname;

	public TestRailConnector(BaseTest pojo) {
		objPojo = pojo;
	}

	public TestRailConnector(String TCID, String Tagname) {
		this.Tagname = Tagname;

	}
	
	public TestRailConnector(ITestResult result) throws MalformedURLException, IOException, APIException {
		
		Map<String, Comparable> data = new HashMap<String, Comparable>();
		if (result.isSuccess()) {
			data.put("status_id", 1);
		} else {
			data.put("status_id", 5);
			data.put("comment", result.getThrowable().toString());
		}
		
		String TagName = Tagname.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("@", "");		
		Pattern p = Pattern.compile("(\\d+)");
		Matcher m = p.matcher(TagName);
		while (m.find()) {
			TestCaseID = m.group(1);
			break;
		}
		File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		FileInputStream fileInput = null;
		fileInput = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fileInput);
		if(prop.getProperty("TestRailEnable").equals("true")){
		String TestRailUrl = prop.getProperty("TestRailURL");
		APIClient client = new APIClient(TestRailUrl);
		String TestRailUserID = prop.getProperty("TestRailUserID");
		client.setUser(TestRailUserID);
		String TestRailPassword = prop.getProperty("TestRailPassword");
		client.setPassword(TestRailPassword);
		data.put("suite_id", SUITE_ID);
		data.put("include_all", true);
		data.put("include_all", true);
		result.setAttribute("suiteId", SUITE_ID);		
		String Suit_Id = prop.getProperty("TestRailSuitID");
		client.sendPost("add_result_for_case/" + Suit_Id + "/" + TestCaseID, data);
		String s = (String) client.sendGet("get_attachment/1", "D:\\Project\\RBLAUTOMATION\\target\\surefire-reports\\index");
		data.put(TagName, file);
	}
	}

}
