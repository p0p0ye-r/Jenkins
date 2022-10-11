package com.generics;

import java.io.IOException;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.PageFactory.API.JSonPage;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.Issue.FluentCreate;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

public class JiraServiceProvider {
	private String project;
	private Pojo objPojo;
	private String name;
	private JiraClient jira;
	public static String Tagname;
	public JiraServiceProvider(Pojo pojo) {
		objPojo = pojo;
	}
	public JiraServiceProvider(String TCID, String Tagname) {
		this.Tagname = Tagname;

	}
	public JiraServiceProvider(String jiraUrl, String username, String password, String project) {
		// create basic authentication object
		BasicCredentials creds = new BasicCredentials(username, password);
		// initialize the jira client with the url and the credentials
		jira = new JiraClient(jiraUrl, creds);
		this.project = project;
	}

	public void createJiraIssue(String issueType, String summary, String description, String Priority) {
		
		/* Create a new issue. */
		try {
			FluentCreate newIssueFluentCreate = jira.createIssue(project, issueType);
			// Add the summary
			newIssueFluentCreate.field(Field.SUMMARY, summary);
			// Add the description
			newIssueFluentCreate.field(Field.DESCRIPTION, description);
			// Add the reporter
//			newIssueFluentCreate.field(Field.REPORTER, Reporter);
			// Add the Priority
			newIssueFluentCreate.field(Field.PRIORITY, Priority);
			
			
			// create the issue in the jira server
			Issue newIssue = newIssueFluentCreate.execute();

			System.out.println("New issue created. Jira ID : " + newIssue);

		} catch (JiraException e) {
			e.printStackTrace();
		}
	}
}
