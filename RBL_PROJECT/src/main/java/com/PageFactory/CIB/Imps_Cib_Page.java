package com.PageFactory.CIB;

import org.openqa.selenium.By;

import com.generics.Pojo;

public class Imps_Cib_Page {
	private Pojo objPojo;

	public Imps_Cib_Page(Pojo pojo) {
		objPojo = pojo;
	}

	// Input
	private By inpCorporateUserID = By.xpath("//input[@id='AuthenticationFG.CUSTOM_USER_PRINCIPAL']");
	private By inpCorporatePassword = By.xpath("//input[@id='AuthenticationFG.ACCESS_CODE']");
	private By inpCorporateAmt = By
			.xpath("//input[@id='PageConfigurationMaster_CMPSCW__1:TranRequestManagerFG.ENTRY_AMT']");
	private By drptransferform = By
			.xpath("//p[@id='PageConfigurationMaster_CMPSCW__1:DataEntry_LeftContainer_Stage39.Rb3']//input[@name='autocomplete-dropdown']");
	private By inpotherpayees = By
			.xpath("//span[@class='icon-dropdown autocomplete-icon active']");
	private By inpotherpayees1 = By
			.xpath("//li[text()='HIR - 1234']");
	
	private By inppaymentType = By
			.xpath("//p[@id='PageConfigurationMaster_CMPSCW__1:DataEntry_LeftContainer_Stage39.Ra3']//input[@name='autocomplete-dropdown']");
	private By inpRemark = By
			.xpath("//p[@id='PageConfigurationMaster_CMPSCW__1:DataEntry_LeftContainer_Stage39.Re3']//input[@name='TranRequestManagerFG.ENT_REMARKS']");
	
	
	public void setCorporateUserID(String value) {
		objPojo.getObjUtilities().logReporter("Set Input Corporate User ID in Corporate Internet Banking Login Page",
				value, objPojo.getObjWrapperFunctions().setText(inpCorporateUserID, value));
	}

	public void setCorporatePassword(String value) {
		objPojo.getObjUtilities().logReporter("Set Input Corporate Password in Corporate Internet Banking Login Page",
				value, objPojo.getObjWrapperFunctions().setText(inpCorporatePassword, value));
	}

	public void setCorporateAmount(String value) {
		objPojo.getObjUtilities().logReporter("Enter Amount in Initiate IMPS Payment", value,
				objPojo.getObjWrapperFunctions().setText(inpCorporateAmt, value));
	}
	public void SetTransferFormPaymentPage(String value) {
		objPojo.getObjUtilities().logReporter("Clear Text Value in Transfer Form", value,
				objPojo.getObjWrapperFunctions().clearText(drptransferform));
		objPojo.getObjUtilities().logReporter("Enter Transfer Form in Initiate IMPS Payment", value,
				objPojo.getObjWrapperFunctions().setText(drptransferform, value));
		
	}
	public void SetOtherPayeesPaymentPage(String value) {
		objPojo.getObjUtilities().logReporter("Enter Other Payees in Initiate IMPS Payment", value,
				objPojo.getObjWrapperFunctions().click(inpotherpayees));
		objPojo.getObjWrapperFunctions().click(inpotherpayees1);
		
	}
	public void SetTransferPaymentTypePaymentPage(String value) {
		objPojo.getObjUtilities().logReporter("Clear Text Value in Transfer Form", value,
				objPojo.getObjWrapperFunctions().clearText(inppaymentType));
		objPojo.getObjUtilities().logReporter("Enter Transfer PaymentType in Initiate IMPS Payment", value,
				objPojo.getObjWrapperFunctions().setText(inppaymentType, value));
		
	}
	public void SetRemarksPaymentPage(String value) {
		objPojo.getObjUtilities().logReporter("Enter Remarks in Initiate IMPS Payment", value,
				objPojo.getObjWrapperFunctions().setText(inpRemark, value));
		objPojo.getObjWrapperFunctions().waitFor(10);
	}
	public String VerifyTEst() {
		return objPojo.getObjWrapperFunctions().getText(drptransferform, "text");
		
	}
}
