package com.stepDefination.Common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Hashtable;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.PageFactory.ABHL.PolicyCreationPage;
import com.PageFactory.CIB.Imps_Cib_Page;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.generics.BaseTest;
import com.generics.Pojo;
import com.generics.TestRailConnector;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CommonConfiqurationStepDefination {

	private Collection<String> Tagname;
	private String TCID = "";
	private Pojo objPojo;
	private Imps_Cib_Page objImps_Cib_Page;
	private String testData;
	private Hashtable<String, String> objHashTable = new Hashtable<String, String>();
	private PolicyCreationPage objPolicyCreationPage;

	public CommonConfiqurationStepDefination(BaseTest pojo) {
		objPojo = pojo.initializeWebEnvironment();
		objImps_Cib_Page = new Imps_Cib_Page(objPojo);
		objPolicyCreationPage = new PolicyCreationPage(objPojo);
	}
	@Before
	public void initializeScenario(Scenario scenario) {
		objPojo.setTestCaseID(scenario.getName());
	}
	@After
	public void tearDown(Scenario scenario) {
		((BaseTest) objPojo).tearDownWebEnvironment(scenario);
	}	

	/**
	 * @Method : Load URL
	 * @Description : Use Following method to Navigate to Specific URL
	 * @param : moduleName : Name of app path to be navigated
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	@When("Navigate To Specific Portal : {string}")
	public void navigate_To_Specific_Portal(String string) {
		objPojo.getObjWrapperFunctions().loadBaseURL();
	}

	/**
	 * @Method : Load Data Provider
	 * @Description : Use Following method to Get excel data
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	@Given("Load {string} TestData in Specific Excel Sheet{string}.")
	public void load_TestData_in_Specific_Excel_Sheet(String string, String SheetName) {
		objPojo.getObjUtilities().loadDataProvider(SheetName);
		System.err.println("Test data Load for execution>>>>" + objPojo.getObjHashTable());
	}
	/**
	 * @author : vikash chandra thakur
	 * @Date of Creation : March
	 */
	@Then("Verify Latest Downloaded File Name as {string}")
	public void verify_Latest_Downloaded_File_Name_as(String string) {
		String testData = objPojo.getObjWrapperFunctions().getLatestDownloadedFileName();
		objPojo.getObjUtilities().logReporter("Verify Latest Downloaded File Name", string, testData,
				testData.contains(string));
	}
	/**
	 * @author : vikash chandra thakur
	 * @throws org.apache.poi.openxml4j.exceptions.InvalidFormatException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws org.apache.poi.openxml4j.exceptions.InvalidFormatException 
	 * @throws EncryptedDocumentException 
	 * @Date of Creation : March
	 */
	@Then("Read {string} Workbook")
	public void Read_WorkBook(String string) throws EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, FileNotFoundException, IOException  {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\AllDocs\\" + string;
			objPojo.setDataValidator((XSSFWorkbook) WorkbookFactory.create(new FileInputStream(filePath)));
			objPojo.getObjUtilities().logReporter("Read Specific Excel File : ", string, true);
			objPojo.getObjUtilities().logReporter("Read Specific Excel File : ", string, false);
	}

	/**
	 * @author : vikash chandra thakur
	 * @Date of Creation : March
	 */
	@Then("Set Runtime Data {string} in Row No {string} and Column No {string} for Sheet {string}")
	public void Set_Runtime_Data_in_Row_No_and_Column_No_for_Sheet(String string, String string1, String string2,
			String string3) {
		testData = objPojo.getObjUtilities().dpString(string);
		objPojo.getObjUtilities().logReporter(
				"Set value in Row No " + string1 + " and Column No " + string2 + " in Sheet " + string3, testData,
				objPojo.getObjWrapperFunctions().setValidatorSheetData(testData, string3, string1, string2));
	}

	/**
	 * @author : vikash chandra thakur
	 * @Date of Creation : March
	 */
	@Then("Set {string} in Row No {string} and Column No {string} for Sheet {string}")
	public void Set_in_Row_No_and_Column_No_for_Sheet(String string, String string1, String string2, String string3) {
		if (string.contains("dynamic")) {
			string = string.replace("dynamic", objPojo.getObjUtilities()
					.getRandomNumbers(Integer.parseInt("" + string.split("dynamic")[1].charAt(0))));
		}
		objPojo.getObjUtilities().logReporter(
				"Set value in Row No " + string1 + " and Column No " + string2 + " in Sheet " + string3, string,
				objPojo.getObjWrapperFunctions().setValidatorSheetData(string, string3, string1, string2));
	}

	/**
	 * @author : vikash chandra thakur
	 * @Date of Creation : March
	 */
	@Then("Save {string} Workbook")
	public void Save_Workbook(String string) {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\AllDocs\\" + string;
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(filePath);
			objPojo.getDataValidator().write(fileOutputStream);
			fileOutputStream.flush();
			fileOutputStream.close();
			objPojo.getObjUtilities().logReporter("Save Specific Excel File : ", filePath, true);
		} catch (Exception ex) {
			ex.printStackTrace();
			objPojo.getObjUtilities().logReporter("Save Specific Excel File : ", filePath, false);
		}
	}
	
}
