package com.stepDefination.ABHL.WEB;

import java.util.Collection;
import java.util.Hashtable;

import com.PageFactory.ABHL.PolicyCreationPage;
import com.generics.BaseTest;
import com.generics.Pojo;

import cucumber.api.java.en.Then;

public class PolicyCreationStepDefination {
	private Collection<String> Tagname;
	private String TCID = "";
	private Pojo objPojo;
	private PolicyCreationPage objPolicyCreationPage;
	private String testData;
	private Hashtable<String, String> objHashTable = new Hashtable<String, String>();

	public PolicyCreationStepDefination(BaseTest pojo) {
		objPojo = pojo.initializeWebEnvironment();
		objPolicyCreationPage = new PolicyCreationPage(objPojo);

	}
	@Then("Fill UserID and Password In {string} TCSBanks")
	public void fill_UserID_and_Password_In_TCSBanks(String string) {
		testData = objPojo.getObjUtilities().dpString("CORPORATEID-USERID");
		if (!testData.equals("")) {
			objPolicyCreationPage.setCorporateUserID(testData);
		}
	}
}
