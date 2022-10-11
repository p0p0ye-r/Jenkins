package com.stepDefination.IMPS.WEB;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.PageFactory.CIB.Imps_Cib_Page;
import com.generics.BaseTest;
import com.generics.Pojo;

import cucumber.api.java.en.Then;

public class IMPS_CIB {
	private Pojo objPojo;
	private Imps_Cib_Page objImps_Cib_Page;
	private String testData;
	private String testData1;

	public IMPS_CIB(BaseTest pojo) {
		objPojo = pojo.initializeWebEnvironment();
		objImps_Cib_Page = new Imps_Cib_Page(objPojo);

	}

	@Then("Enter User name in Corporate Internet Banking Page")
	public void enter_Following_User_name_and_Password_in_Login_Page() {
		testData = objPojo.getObjUtilities().dpString("CORPORATEID-USERID");
		if (!testData.equals("")) {
			objImps_Cib_Page.setCorporateUserID(testData);
		}
	}

	@Then("Click on {string} button in Corporate Internet Banking LoginPage.")
	public void click_on_button_in_Corporate_Internet_Banking_LoginPage(String string) {
		By locator = By.xpath("//span//i//input[@value='" + string + "']");
		objPojo.getObjUtilities().logReporter("Click " + string + " Button on " + string,
				objPojo.getObjWrapperFunctions().click(locator));
	}

	@Then("Click On {string} CheckBox.")
	public void click_On_CheckBox(String string) {
		By locator = By.xpath("//span[@class='span-checkbox']");
		objPojo.getObjUtilities().logReporter("Click " + string + " Button on " + string,
				objPojo.getObjWrapperFunctions().click(locator));
	}

	@Then("Enter Password in Corporate Internet Banking Page.")
	public void enter_Password_in_Corporate_Internet_Banking_Page() {
		testData = objPojo.getObjUtilities().dpString("CORPORATEID-PASSWORD");
		if (!testData.equals("")) {
			objImps_Cib_Page.setCorporatePassword(testData);
		}
	}

	@Then("Click on {string}  button in Corporate Internet Banking PasswordPage.")
	public void click_on_button_in_Corporate_Internet_Banking_PasswordPage(String string) {
		By locator = By.xpath("//input[@type='Submit']");
		objPojo.getObjUtilities().logReporter("Click " + string + " Button on " + string,
				objPojo.getObjWrapperFunctions().click(locator));
	}

	@Then("Click on {string} Feild On Quick Links")
	public void click_on_Feild_On_Quick_Links(String string) {
		By locator = By.xpath(
				"//a[@id='HREF_CorporateUserDashboardUX5_WMST__1:CustomMySchortcutsFG.CONTEXTUAL_MENU_TEXT_ARRAY[4]']");
		objPojo.getObjUtilities().logReporter("Click " + string + " Button on " + string,
				objPojo.getObjWrapperFunctions().click(locator));
	}

	@Then("Fill All Iniated {string} Payement In Corporate Internet Banking fund Transfer.")
	public void fill_All_Iniated_Payement_In_Corporate_Internet_Banking_fund_Transfer(String string)
			throws InterruptedException {
		testData = objPojo.getObjUtilities().dpString("CORPORATEID-AMOUNT");
		if (!testData.equals("")) {
			objImps_Cib_Page.setCorporateAmount(testData);
		}
//		testData = objPojo.getObjUtilities().dpString("TRASFER-FORM");
//		if (!testData.equals("")) {
//			objImps_Cib_Page.SetTransferFormPaymentPage(testData);
//		}
//		testData = objPojo.getObjUtilities().dpString("OTHER-PAYEES");
//		if (!testData.equals("")) {
//			objImps_Cib_Page.SetOtherPayeesPaymentPage(testData);
//		}
		testData = objPojo.getObjUtilities().dpString("PAYMENT-TYPE");
		if (!testData.equals("")) {
			objImps_Cib_Page.SetTransferPaymentTypePaymentPage(testData);
		}
		testData = objPojo.getObjUtilities().dpString("REMARKS");
		if (!testData.equals("")) {
			objImps_Cib_Page.SetRemarksPaymentPage(testData);
		}
	}

	@Then("Verify Error Message{string} in Trasfer Forn In Payment Initiate Page.")
	public void verify_Error_Message_in_Trasfer_Forn_In_Payment_Initiate_Page(String string) {
		By locator = By.xpath("//span//p[text()='" + string + "']");
		objPojo.getObjUtilities().logReporter("Verify " + string + " Button Displayed on " + string,
				objPojo.getObjWrapperFunctions().checkElementDisplayed(locator));
	}
	@Then("Verify HomePage Name {string} in Rbl Home Page")
	public void verify_Home_Page_Name_Page(String string) {
		By locator = By.xpath("//h3//strong[text()='" + string + "']");
		objPojo.getObjUtilities().logReporter("Verify Name is  Displayed on Home Page =" + string,
				objPojo.getObjWrapperFunctions().checkElementDisplayed(locator));
	}
	@Then("Verify HomePage Name1 {string} in Rbl Home Page")
	public void verify1_Hodssdfme_Paged_Name1_Page(String string) {
		testData=objPojo.getObjUtilities().dpString("SheetName");
		if(!testData.equals("")) {
			testData1=objImps_Cib_Page.VerifyTEst();
			objPojo.getObjUtilities().logReporter("", testData,testData1,testData1.trim().equals(testData.trim()));
		}
		By locator = By.xpath("//h3//strong[text()='" + string + "']");
		objPojo.getObjUtilities().logReporter("Verify Name is  Displayed on Home Page =" + string,
				objPojo.getObjWrapperFunctions().checkElementDisplayed(locator));
	}

}
