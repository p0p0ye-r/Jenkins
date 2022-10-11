package com.generics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.codehaus.plexus.util.ExceptionUtils;
import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

/**
 * @ScriptName : CustomeReporter
 * @Description : Excel Based report creation for regression execution
 * @Author : Vikash Thakur.
 */

public class CustomReporter implements ITestListener {
	private static final TestRailConnector Connector = null;
	private String name;

	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {
		try {
			TestRailConnector connect = new TestRailConnector(result);
		} catch (IOException | APIException e) {
			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult result) {
		try {
			TestRailConnector connect = new TestRailConnector(result);
		} catch (IOException | APIException e1) {
			e1.printStackTrace();
		}
		File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String JiraCloudURL = prop.getProperty("JiraURL");
		String JiraCloudUserID = prop.getProperty("JiraUserID");
		String JiraCloudApiToken = prop.getProperty("JiraApiToken");
		String JiraCloudProjectName = prop.getProperty("JiraProjectName");
		JiraServiceProvider jira = new JiraServiceProvider(JiraCloudURL, JiraCloudUserID, JiraCloudApiToken,
				JiraCloudProjectName);
		String issueSummary = result.getMethod().getConstructorOrMethod().getMethod().getName()
				+ " get Failed due to assertion and Exception";
		String issueDescription = result.getThrowable().getMessage() + "\\n";
		issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));
		String JiraCloudIssue = prop.getProperty("JiraIssueType");
		jira.createJiraIssue(JiraCloudIssue, issueSummary, issueDescription, "High");
	}

	public void onTestSkipped(ITestResult result) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
		new CustomReporterHelper(null, null).startExcelReport();
	}
}