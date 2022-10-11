package com.PageFactory.ABHL;

import org.openqa.selenium.By;

import com.generics.Pojo;

public class PolicyCreationPage {
	private Pojo objPojo;
	

	public PolicyCreationPage(Pojo pojo) {
		objPojo = pojo;
	}
	private By inpCorporateUserID = By.xpath("//div//input[@id='pUserName']");
	
	
	public void setCorporateUserID(String value) {
		objPojo.getObjUtilities().logReporter("Set Input Corporate User ID in Corporate Internet Banking Login Page",
				value, objPojo.getObjWrapperFunctions().setText(inpCorporateUserID, value));
	}
}
