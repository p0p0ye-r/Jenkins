package com.stepDefination.Common;

import com.PageFactory.API.JSonPage;
import com.generics.BaseTest;
import com.generics.Pojo;
import com.generics.TestRailConnector;
import com.generics.Utilities;
import com.generics.ValidationUtils;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import java.util.Properties;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.Reporter;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import cucumber.api.java.en.Then;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

public class IMPS_RTGS_NEFT_COMMON_Json {
	private String BearerToken;
	private Pojo objPojo;
	private String TCID = "";
	private JSonPage objJSonPage;
	String JsonPath = (System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\");
	String XmlSinglePath = (System.getProperty("user.dir") + "\\src\\test\\resources\\ApiTestData\\TC_01.xml");
	private String BaseUri;
	private File filepath;
	private String testData;
	private Response response;
	private Hashtable<String, String> objHashTable = new Hashtable<String, String>();
	private com.jayway.restassured.path.json.JsonPath jsonPathEvaluator;
	private String testData1;
	private String msg;
	RequestSpecification httprequest;
	private Collection<String> Tagname;

	public IMPS_RTGS_NEFT_COMMON_Json(BaseTest pojo) {
		objPojo = pojo.initializeWebEnvironment();
		objJSonPage = new JSonPage(objPojo);

	}

	@Before
	public void initializeScenario(Scenario scenario) {
		TCID = scenario.getName();
		TCID = scenario.getName();
		Tagname = scenario.getSourceTagNames();
		String name = Tagname.toString();
		TestRailConnector tag = new TestRailConnector(TCID, name);
	}

	/**
	 * @ScriptName : Excel Sheet Test Data Loader
	 * @Description :Get url From Config properties file
	 * @Author : Vikash Thakur.
	 **/
	@Given("I am requesting {string}")
	public void i_am_requesting(String arg1) {
		File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BaseUri = prop.getProperty(arg1);
		System.out.println(BaseUri);
	}

	/**
	 * @ScriptName : Excel Sheet Test Data Loader
	 * @Description :Load test Data and get testcase details under excel sheet
	 * @Author : Vikash Thakur.
	 **/
	@Given("Load TestData Form Specific Api sheet {string}")
	public void load_TestData_Form_Specific_Api_sheet(String string) {
		XSSFWorkbook workBook;
		try {
			String testDataPath = System.getProperty("user.dir") + "/src/test/resources/testData/TestData.xlsx";
			workBook = new XSSFWorkbook(new FileInputStream(testDataPath));
			XSSFSheet sheet = workBook.getSheet(string);
			for (int j = 0; j <= sheet.getLastRowNum(); j++)
				if (sheet.getRow(j).getCell(1).getStringCellValue().equalsIgnoreCase(TCID)) {
					for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
						try {
							objHashTable.put(sheet.getRow(0).getCell(i).getStringCellValue(),
									objHashTable.put(sheet.getRow(0).getCell(i).getStringCellValue(),
											sheet.getRow(j).getCell(i).getStringCellValue()));
							System.out.print(sheet);
						} catch (IllegalStateException e) {
							try {
								objHashTable.put(sheet.getRow(0).getCell(i).getStringCellValue(),
										"" + sheet.getRow(j).getCell(i).getNumericCellValue());
							} catch (IllegalStateException ex) {
								System.out.println(sheet.getRow(0).getCell(j).getStringCellValue());
							}
						} catch (NullPointerException e) {
							objPojo.getObjHashTable().put("", "");
						}
					}
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @ScriptName : Replace base json file to run time json file
	 * @Date :29-08-2022
	 * @Author : Vikash Thakur.
	 **/
	@Given("Base Test {string} data Replace with RunTime Test Data{string}")
	public void base_Test_data_Replace_with_RunTime_Test_Data(String string, String string2) throws IOException {
		String DynamicfilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\";
		String Filepath1 = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\";
		filepath = new File(DynamicfilePath + string);
		FileInputStream FileRead = new FileInputStream(filepath);
		FileOutputStream FileWrite = new FileOutputStream(Filepath1 + string2);
		System.out.print("File Is Copied");
		int c;
		while ((c = FileRead.read()) != -1)
			FileWrite.write((char) c);
		FileRead.close();
		FileWrite.close();
	}

	/**
	 * @ScriptName : Create input file for json using excdel sheet
	 * @Date :29-08-2022
	 * @Author : Vikash Thakur.
	 **/
	@Then("Create Input File for json from Excel Sheet")
	public void create_Input_File_for_json_from_Excel_Sheet() throws IOException {
//		testData = objHashTable.get("DELIVERY-FLAG");
//		if (!testData.equals("")) {
//			objJSonPage.readRequiredDataFromGenrateDeliveryFlagJsonInputFile(testData);
//		}
//
//		testData = objHashTable.get("SERVICE-TYPE");
//		if (!testData.equals("")) {
//			objJSonPage.readRequiredDataFromGenrateSERVICETYPEJsonInputFile(testData);
//		}
//		testData = objHashTable.get("IS-NRI");
//		if (!testData.equals("")) {
//			objJSonPage.readRequiredDataFromGenrateISNRIJsonInputFile(testData);
//		}
		testData = objHashTable.get("REQUEST-ID");
		if (!testData.equals("")) {

			String requestId = objPojo.getObjUtilities().getRandomStringNO(21);
			objPojo.getObjUtilities().setDataPool("RuntimeGenratedRequestId", requestId);
			String runtimeRequestId = objPojo.getObjUtilities().dpString("RuntimeGenratedRequestId");
			objJSonPage.readRequiredDataFromJsonInputFile(requestId);
		}

		testData = objHashTable.get("GENRATEOTP-REQUESTUUID");
		if (!testData.equals("")) {
			String RuntimeUUID = objPojo.getObjUtilities().dpString("RuntimeGenratedRequestUUID");
			objJSonPage.readRequiredDataFromJsonRequestUUIDInputFile(RuntimeUUID);
		}
		testData = objHashTable.get("MOBILE-NO");
		if (!testData.equals("")) {
			objJSonPage.readRequiredDataFromGenrateMOBILENOJsonInputFile(testData);
		}

		testData = objHashTable.get("EMAIL");
		if (!testData.equals("")) {
			objJSonPage.readRequiredDataFromGenrateEMAILJsonInputFile(testData);
		}

		// Created By vinayak

		testData = objHashTable.get("CHANNEL-ID");
		if (!testData.equals("")) {
			objJSonPage.readRequiredDataFromGenrateChannelIdJsonInputFile(testData);
		}

//		testData = objHashTable.get("SERVICEREQUEST-ID");
//		if (!testData.equals("")) {
//			objJSonPage.readRequiredDataFromGenrateSERVICEREQUESTIDJsonInputFile(testData);
//		}

		testData = objHashTable.get("MESSAGEKEY-CHANNELID");
		if (!testData.equals("")) {
			objJSonPage.readRequiredDataFromGenrateMESSAGEKEYCHANNELIDsonInputFile(testData);
		}

		testData = objHashTable.get("PASSWORDTOKEN-USERID");
		if (!testData.equals("")) {
			objJSonPage.readRequiredDataFromGenratePASSWORDTOKENUSERIDsonInputFile(testData);
		}

		testData = objHashTable.get("BANK-ID");
		if (!testData.equals("")) {
			objJSonPage.readRequiredDataFromGenrateBANKIDsonInputFile(testData);
		}

		testData = objHashTable.get("OPTIONAL-FIELD1");
		if (!testData.equals("")) {
			objJSonPage.readRequiredDataFromGenrateOPTIONALFIELD1sonInputFile(testData);
		}

		testData = objHashTable.get("OPTIONAL-FIELD2");
		if (!testData.equals("")) {
			objJSonPage.readRequiredDataFromGenrateOPTIONALFIELD2sonInputFile(testData);
		}

		testData = objHashTable.get("OPTIONAL-FIELD3");
		if (!testData.equals("")) {
			objJSonPage.readRequiredDataFromGenrateOPTIONALFIELD3sonInputFile(testData);
		}

		// Created By vinayak
		/*
		 * testData = objHashTable.get("SERVICEREQUEST-ID"); if (!testData.equals("")) {
		 * //String serviceRequestId = objPojo.getObjUtilities().getRandomString(5);
		 * objJSonPage.readRequiredDataFromGenrateSERVICEREQUESTIDJsonInputFile(testData
		 * );
		 * 
		 * }
		 */

		/*
		 * testData = objHashTable.get("SERVICEREQUEST-VERSION"); if
		 * (!testData.equals("")) {
		 * objJSonPage.readRequiredDataFromGenrateSERVICEREQUESTVERSIONJsonInputFile(
		 * testData); }
		 */

		/*
		 * testData = objHashTable.get("CHANNEL-ID"); if (!testData.equals("")) {
		 * //String ChannelID = objPojo.getObjUtilities().getRandomStringNO(5); //
		 * objPojo.getObjUtilities().setDataPool("RuntimeGenratedChannelID", ChannelID);
		 * // String RuntimeChannelID =
		 * objPojo.getObjUtilities().dpString("RuntimeGenratedChannelID"); //
		 * System.out.println(RuntimeChannelID);
		 * 
		 * objJSonPage.readRequiredDataFromGenrateChannelIdJsonInputFile(testData); }
		 */

		testData = objHashTable.get("TRANSACTION-REF-ID");
		if (!testData.equals("")) {

			String TransactionRefID = objPojo.getObjUtilities().getRandomStringNO(16);
			objPojo.getObjUtilities().setDataPool("RuntimeGenratedTransactionRefID", TransactionRefID);
			String runtimeTransactionRefID = objPojo.getObjUtilities().dpString("RuntimeGenratedTransactionRefID");
			objJSonPage.readRequiredDataFromTransactionRefIDJsonInputFile(runtimeTransactionRefID);
		}

	}

	/**
	 * @ScriptName : Create input file for json using excel sheet
	 * @Date :29-08-2022
	 * @Author : Vikash Thakur.
	 **/
	@Then("I go to {string} and {string} Api Request Saved in Json File {string}without charset")
	public void i_go_to_and_Api_Request_Saved_in_Json_File_without_charset(String string, String string2,
			String string3) {
		String jsonfilepath = System.getProperty("user.dir") + "/src/test/resources/ApiJsonData//" + string3;
		String file = Utilities.readLineByLineJava8(jsonfilepath);
		RestAssuredConfig config = RestAssured.config().encoderConfig(
				(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
		System.out.print(BaseUri + string);

		httprequest = RestAssured.given().config(config).accept("application/json")
				.header("Content-Type", "application/json").body(file).log().all();

		String P = "POST";
		String P1 = "PUT";
		String D = "DELETE";
		String G = "GET";
		if (P.equalsIgnoreCase(string2)) {
			response = httprequest.relaxedHTTPSValidation().post(BaseUri + string);
		}
		if (P1.equalsIgnoreCase(string2)) {
			response = httprequest.put(BaseUri + string);
		}
		if (D.equalsIgnoreCase(string2)) {
			response = httprequest.delete(BaseUri + string);
		}
		if (G.equalsIgnoreCase(string2)) {
			response = httprequest.get(BaseUri + string);
		}

		System.out.print(response.getStatusCode());
		System.out.print(response.headers());
		System.out.print("Repose is :" + response.asString());
		jsonPathEvaluator = response.jsonPath();
	}

	@Then("I go to {string} and {string} Api Request Saved in Json File {string}with charset")
	public void i_go_to_and_Api_Request_Saved_in_Json_File_with_charset(String string, String string2, String string3) {

		String jsonfilepath = System.getProperty("user.dir") + "/src/test/resources/ApiJsonData//" + string3;
		String file = Utilities.readLineByLineJava8(jsonfilepath);
		RestAssuredConfig config = RestAssured.config().encoderConfig(
				(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
		System.out.print(BaseUri + string);

		httprequest = RestAssured
				.given().config(config).accept("application/json").headers("Content-Type", "application/json",
						"x-api-key", "dVi1CAyGnRinGXdpI3jLySHfl1jk3lGn", "Authorization", "Bearer " + BearerToken)
				.body(file).log().all();

		String P = "POST";
		String P1 = "PUT";
		String D = "DELETE";
		String G = "GET";
		if (P.equalsIgnoreCase(string2)) {
			response = httprequest.relaxedHTTPSValidation().post(BaseUri + string);
			int statuscode = response.getStatusCode();
			String status = Integer.toString(statuscode);

			objPojo.getObjUtilities().setDataPool("RuntimeGenratedStatuscode", status);
			String stsCode = objPojo.getObjUtilities().dpString("RuntimeGenratedStatuscode");
		}
		if (P1.equalsIgnoreCase(string2)) {
			response = httprequest.put(BaseUri + string);
		}
		if (D.equalsIgnoreCase(string2)) {
			response = httprequest.delete(BaseUri + string);
		}
		if (G.equalsIgnoreCase(string2)) {
			response = httprequest.get(BaseUri + string);
		}

		System.out.print(response.getStatusCode());
		System.out.print(response.headers());
		System.out.print("Repose is :" + response.asString());
		jsonPathEvaluator = response.jsonPath();
	}

	/**
	 * @ScriptName : save final output in response.json file
	 * @Date :29-08-2022
	 * @Author : Vikash Thakur.
	 **/
	@Then("Save Final Response in {string} output File.")
	public void save_Final_Response_in_output_File(String string) throws IOException {
		FileWriter file = new FileWriter(JsonPath + string);
		System.out.print(response.asString());
		String json = response.asString();
		System.out.print(json);
		file.write(json);
		file.flush();
		file.close();
	}

	/**
	 * @ScriptName : compare response with schema using matches json schema
	 * @Date :29-08-2022
	 * @Author : Vikash Thakur.
	 **/
	@Then("I compare Json Schema file {string} with Response File {string}.")
	public void i_compare_Json_Response_file_with_Schema_File(String Schema, String JsonReponse) throws IOException {
		File Schemafile = new File(JsonPath + Schema);
		File jsonFile = new File(JsonPath + JsonReponse);
		String Value = "This Is ";
		BufferedReader reader = new BufferedReader(new FileReader(Schemafile));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String Is = System.getProperty("line.seprator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(Is);
			System.out.print(line);
		}
		// delete last new line seprator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();
		String content = stringBuilder.toString();
		// response.then().assertThat().body(matchesJsonSchema(content));
		try {
			if (ValidationUtils.isJsonValid(Schemafile, jsonFile)) {
				Value = Value + "Valid";
				System.out.print(Value);
			} else {
				Value = Value + "No Valid";
				System.out.print("Not Valid");
			}
		} catch (ProcessingException | IOException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(Value, "This Is Valid");
	}

	/**
	 * @ScriptName : Verify response value
	 * @Date :29-08-2022
	 * @Author : Vikash Thakur.
	 **/

	@Then("I am validating object {string} for string value {string} For {string}.")
	public void i_am_validating_object_for_string_value_For(String xpath, String data, String Module) {
		String value = jsonPathEvaluator.get(xpath);
		System.out.print(value);
		Assert.assertEquals(data, value.trim());
		objPojo.getObjUtilities().logReporter("Verify the response value in" + Module + " Api", data, value.trim(),
				value.trim().equals(data));
	}

	/**
	 * @ScriptName : Storing the Bearer Token
	 * @Date :10-09-2022
	 * @Author : Vinayak Waghchaure
	 **/

	@Then("I am saving Bearer Token from response available on {string}")
	public void i_am_saving_Bearer_Token_from_response_available_on(String xpath) {
		BearerToken = jsonPathEvaluator.get(xpath);
		System.out.print("This is Bear " + BearerToken);

	}

	/**
	 * @throws IOException
	 * @ScriptName : Create directory in local drive with datewise folder and save
	 *             request and response value
	 * @Date :01-09-2022
	 * @Author : Vikash Thakur.
	 **/
	@Then("Save the Request {string} and Response {string}.")
	public void save_the_Request_and_Response(String Request, String Response) throws IOException {
		String foldername = TCID;
		testData = objPojo.getObjConfig().getProperty("driverPathForRBL");
		testData1 = objPojo.getObjConfig().getProperty("RBLProjectName");
		Date date = new Date();
		SimpleDateFormat DateFor = new SimpleDateFormat("dd-MM-yyyy");
		String stringDate = DateFor.format(date);
		File files3 = new File(testData + testData1 + "\\" + stringDate);
		if (!files3.exists()) {
			if (files3.mkdir()) {
				System.out.print("Folder Created Successfully");
			} else {
				System.out.print("Failed to Created Folder");
			}
		}
		FileInputStream instream = null;
		FileOutputStream outstream = null;
		FileInputStream instreamResponse = null;
		FileOutputStream outstreamResponse = null;
		File files = new File(testData + testData1 + "\\" + stringDate + "\\" + foldername);
		if (!files.exists()) {
			if (files.mkdir()) {
				System.out.print("Folder Created Successfully");
			} else {
				System.out.print("Failed to Created Folder");
			}
		}
		File files1 = new File(testData + testData1 + "\\" + stringDate + "\\" + foldername + "\\Request.txt");
		if (!files1.exists()) {
			if (files1.createNewFile()) {
				System.out.print("File Created: " + files1.getName());
			} else {
				System.out.print("File already Exits");
			}
		}
		File files2 = new File(testData + testData1 + "\\" + stringDate + "\\" + foldername + "\\Response.txt");
		if (!files2.exists()) {
			if (files2.createNewFile()) {
				System.out.print("File Created: " + files2.getName());
			} else {
				System.out.print("File already Exits");
			}
		}
		try {
			File infile = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\" + Request);
			File outfile = new File(testData + testData1 + "\\" + stringDate + "\\" + foldername + "\\Request.txt");
			File inResponsefile = new File(
					System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\" + Response);
			File outResponsefile = new File(
					testData + testData1 + "\\" + stringDate + "\\" + foldername + "\\Response.txt");
			instream = new FileInputStream(infile);
			outstream = new FileOutputStream(outfile);
			instreamResponse = new FileInputStream(inResponsefile);
			outstreamResponse = new FileOutputStream(outResponsefile);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = instream.read(buffer)) > 0) {
				outstream.write(buffer, 0, length);
			}
			while ((length = instreamResponse.read(buffer)) > 0) {
				outstreamResponse.write(buffer, 0, length);
			}
			instream.close();
			outstream.close();
			instreamResponse.close();
			outstreamResponse.close();
			System.out.print("File Copied Successfully");
		} catch (IOException ioe) {
			ioe.printStackTrace();

		}
	}

	@Then("Fetch OTP In Your Registered Mobile.")
	public void fetch_OTP_In_Your_Regesterd_Mobile() {
		((JavascriptExecutor) objPojo.getDriver()).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(objPojo.getDriver().getWindowHandles());
		objPojo.getDriver().switchTo().window(tabs.get(1));
		objPojo.getDriver().get("http://65.0.200.26/online_food_ordering_system/online_food_ordering_system/test.php");
		objPojo.getObjWrapperFunctions().waitFor(10);
		objPojo.getDriver().navigate().refresh();
		msg = objPojo.getDriver().findElement(By.xpath("/html/body")).getText();
		objPojo.getObjUtilities().setDataPool("RuntimeGenratedOTPMessage", msg);
		String RuntimeOtpMessage = objPojo.getObjUtilities().dpString("RuntimeGenratedOTPMessage");
		String AfterTrimm = RuntimeOtpMessage.substring(21);
		String Splittwo = AfterTrimm.substring(6);
		String otp = msg.split(" ")[1].split("=")[1];
		String VerificationOTPMessage = otp + Splittwo;
		System.out.println(VerificationOTPMessage);
		objPojo.getObjUtilities().setDataPool("RuntimeGenratedOTP", otp);
		String TestData = objPojo.getObjUtilities().dpString("RuntimeGenratedOTP");
		objPojo.getDriver().close();
		objPojo.getDriver().switchTo().window(tabs.get(0));
	}

	@Then("Create Input File for ValidateOTP json from Excel Sheet")
	public void create_Input_File_for_ValidateOTP_json_from_Excel_Sheet() throws IOException, NoSuchAlgorithmException {
		testData = objHashTable.get("VALITATEOTP-REQUESTUUID");
		if (!testData.equals("")) {
			String UUID = objPojo.getObjUtilities().dpString("RuntimeGenratedRequestUUID");
			objJSonPage.readRequiredDataFromValidateUUIDJsonInputFile(UUID);
		}

		testData = objHashTable.get("REQUEST-ID");
		if (!testData.equals("")) {
			String requestId = objPojo.getObjUtilities().dpString("RuntimeGenratedRequestId");
			objJSonPage.readRequiredDataFromValidateRequestIdJsonInputFile(requestId);
		}

		testData = objHashTable.get("OTP");
		if (!testData.equals("")) {
			String TestData = objPojo.getObjUtilities().dpString("RuntimeGenratedOTP");
			String otp = TestData;
			byte[] data = otp.getBytes();
			MessageDigest digester = MessageDigest.getInstance("SHA-256");
			digester.update(data);
			String base64EncodedEncryptOtp = Base64.getEncoder().encodeToString(digester.digest());
			objJSonPage.readRequiredDataFromValidateOTPJsonInputFile(base64EncodedEncryptOtp);
		}
	}

	@Then("Create Input File for Get OTP json from Excel Sheet")
	public void create_Input_File_for_Get_OTP_json_from_Excel_Sheet() throws IOException {
		testData = objHashTable.get("GETTOKEN-REQUESTUUID");
		if (!testData.equals("")) {
			String UUID = objPojo.getObjUtilities().getRandomNumbers(17);
			objPojo.getObjUtilities().setDataPool("RuntimeGenratedRequestUUID", UUID);
			String RuntimeUUID = objPojo.getObjUtilities().dpString("RuntimeGenratedRequestUUID");
			objJSonPage.readRequiredDataFromJsonGETTOKENInputFile(RuntimeUUID);
		}
	}

	/**
	 * @ScriptName : verify msg RBI format
	 * @Date :10-09-2022
	 * @Author : Vinayak Waghchaure
	 **/
	@Then("Verify OTP mobile message is in RBI format In Your Registered Mobile.")
	public void verify_OTP_mobile_message_is_in_RBI_format_In_Your_Registered_Mobile() {
		String RuntimeOtpMessage = objPojo.getObjUtilities().dpString("RuntimeGenratedOTPMessage");
		String OtpString = RuntimeOtpMessage;
		String otp = OtpString.split(" ")[1].split("=")[1];
		String[] verifyotp = OtpString.split("=");
		String phone = verifyotp[1].split(" ")[0];
		String msg2 = verifyotp[2];
		String withoutOtpMsg = msg2.split(otp)[1];
		
		testData = objHashTable.get("OTPMessage");
		String transctionMode = objHashTable.get("OPTIONAL-FIELD1");
		String Amount = objHashTable.get("OPTIONAL-FIELD2");
		String AccountNumber = objHashTable.get("OPTIONAL-FIELD3");
		String OtpMessageFormat = otp + " OTP for " + transctionMode + " txn of INR " + Amount + " to " + AccountNumber
				+ testData;
		if (!testData.equals("")) {
			Assert.assertEquals(msg2, OtpMessageFormat);
		}
	}

	/**
	 * @ScriptName : Verify OTP mobile message size
	 * @Date :10-09-2022
	 * @Author : Vinayak Waghchaure
	 **/
	@Then("Verify OTP mobile message size is not morethan {int} character.")
	public void verify_OTP_mobile_message_size_is_not_morethan_character(Integer size) {

		((JavascriptExecutor) objPojo.getDriver()).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(objPojo.getDriver().getWindowHandles());
		objPojo.getDriver().switchTo().window(tabs.get(1));
		objPojo.getDriver().get("http://65.0.200.26/online_food_ordering_system/online_food_ordering_system/test.php");
		objPojo.getObjWrapperFunctions().waitFor(10);
		objPojo.getDriver().navigate().refresh();
		msg = objPojo.getDriver().findElement(By.xpath("/html/body")).getText();
		objPojo.getObjUtilities().setDataPool("RuntimeGenratedOTPMessage", msg);
		String RuntimeOtpMessage = objPojo.getObjUtilities().dpString("RuntimeGenratedOTPMessage");
		String otp = RuntimeOtpMessage.split(" ")[1].split("=")[1];
		String[] verifyotp = RuntimeOtpMessage.split("=");
		String phone = verifyotp[1].split(" ")[0];
		String msg2 = verifyotp[2];
		String withoutOtpMsg = msg2.split(otp)[1];
		int fullmsgLength = msg2.length();
		// int fullmsgLength=RuntimeOtpMessage.length();
		if (fullmsgLength < size) {
			System.out.println("message size is lessthan 154 character");
		} else {
			Assert.fail("message size is morethan 154 character");
		}
	}

	/**
	 * @ScriptName : Verify Generated OTP lenght 6 digit
	 * @Date :10-09-2022
	 * @Author : Vinayak Waghchaure
	 **/
	@Then("Verify Generated OTP should be {int} digit numeric value.")
	public void verify_Generated_OTP_should_be_digit_numeric_value(Integer size) {
		((JavascriptExecutor) objPojo.getDriver()).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(objPojo.getDriver().getWindowHandles());
		objPojo.getDriver().switchTo().window(tabs.get(1));
		objPojo.getDriver().get("http://65.0.200.26/online_food_ordering_system/online_food_ordering_system/test.php");
		objPojo.getObjWrapperFunctions().waitFor(5);
		objPojo.getDriver().navigate().refresh();
		msg = objPojo.getDriver().findElement(By.xpath("/html/body")).getText();
		objPojo.getObjUtilities().setDataPool("RuntimeGenratedOTPMessage", msg);
		String RuntimeOtpMessage = objPojo.getObjUtilities().dpString("RuntimeGenratedOTPMessage");
		String otp = RuntimeOtpMessage.split(" ")[1].split("=")[1];
		String[] verifyotp = RuntimeOtpMessage.split("=");
		String phone = verifyotp[1].split(" ")[0];
		String msg2 = verifyotp[2];
		String withoutOtpMsg = msg2.split(otp)[1];
		int otpsize = otp.length();
		if (otpsize == size) {
			System.out.println("OTP size is 6 Digit");
			Assert.assertEquals(otpsize, otpsize);
		} else {
			Assert.fail("OTP size is not 6 Digit");
		}
	}

	/**
	 * @ScriptName : Verify Generated OTP 200 ok Status code
	 * @Date :10-09-2022
	 * @Author : Vinayak Waghchaure
	 **/
	@Then("Verify Generate Otp {int} ok Status code.")
	public void verify_Generate_Otp_ok_Status_code(Integer statusCode) {
		String responseStatusCode = objPojo.getObjUtilities().dpString("RuntimeGenratedStatuscode");
		int responseStatus = Integer.parseInt(responseStatusCode);
		System.out.println("Response StatusCode :" + responseStatusCode);
		Assert.assertEquals(responseStatus, responseStatus);
	}

	@Then("I go to {string} and {string} Api Request with Invalid APIKey and Token Saved in Json File {string}with charset")
	public void i_go_to_and_Api_Request_with_Invalid_APIKey_and_Token_Saved_in_Json_File_with_charset(String string,
			String string2, String string3) {

		String jsonfilepath = System.getProperty("user.dir") + "/src/test/resources/ApiJsonData//" + string3;
		String file = Utilities.readLineByLineJava8(jsonfilepath);
		RestAssuredConfig config = RestAssured.config().encoderConfig(
				(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
		System.out.print(BaseUri + string);
		testData = objHashTable.get("Invalid-APIKEY");
		String invalidapiKey = testData;
		// String invalidBearerToken=BearerToken+"er43";
		httprequest = RestAssured
				.given().config(config).accept("application/json").headers("Content-Type", "application/json",
						"x-api-key", invalidapiKey, "Authorization", "Bearer " + BearerToken + "2we")
				.body(file).log().all();

		String P = "POST";
		String P1 = "PUT";
		String D = "DELETE";
		String G = "GET";
		if (P.equalsIgnoreCase(string2)) {
			response = httprequest.relaxedHTTPSValidation().post(BaseUri + string);
			int statuscode = response.getStatusCode();
			String status = Integer.toString(statuscode);
			objPojo.getObjUtilities().setDataPool("RuntimeGenratedStatuscode", status);
			String stsCode = objPojo.getObjUtilities().dpString("RuntimeGenratedStatuscode");
		}
		if (P1.equalsIgnoreCase(string2)) {
			response = httprequest.put(BaseUri + string);
		}
		if (D.equalsIgnoreCase(string2)) {
			response = httprequest.delete(BaseUri + string);
		}
		if (G.equalsIgnoreCase(string2)) {
			response = httprequest.get(BaseUri + string);
		}
		System.out.print(response.getStatusCode());
		System.out.print(response.headers());
		System.out.print("Repose is :" + response.asString());
		jsonPathEvaluator = response.jsonPath();
	}

	@Then("I go to {string} and {string} Api Request with Invalid APIKey Saved in Json File {string}with charset")
	public void i_go_to_and_Api_Request_with_Invalid_APIKey_Saved_in_Json_File_with_charset(String string,
			String string2, String string3) {
		String jsonfilepath = System.getProperty("user.dir") + "/src/test/resources/ApiJsonData//" + string3;
		String file = Utilities.readLineByLineJava8(jsonfilepath);
		RestAssuredConfig config = RestAssured.config().encoderConfig(
				(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
		System.out.print(BaseUri + string);
		testData = objHashTable.get("Invalid-APIKEY");
		String invalidapiKey = testData;
		// String invalidBearerToken=BearerToken+"er43";
		httprequest = RestAssured.given().config(config).accept("application/json").headers("Content-Type",
				"application/json", "x-api-key", invalidapiKey, "Authorization", "Bearer " + BearerToken).body(file)
				.log().all();

		String P = "POST";
		String P1 = "PUT";
		String D = "DELETE";
		String G = "GET";
		if (P.equalsIgnoreCase(string2)) {
			response = httprequest.relaxedHTTPSValidation().post(BaseUri + string);
			int statuscode = response.getStatusCode();
			String status = Integer.toString(statuscode);

			objPojo.getObjUtilities().setDataPool("RuntimeGenratedStatuscode", status);
			String stsCode = objPojo.getObjUtilities().dpString("RuntimeGenratedStatuscode");
		}
		if (P1.equalsIgnoreCase(string2)) {
			response = httprequest.put(BaseUri + string);
		}
		if (D.equalsIgnoreCase(string2)) {
			response = httprequest.delete(BaseUri + string);
		}
		if (G.equalsIgnoreCase(string2)) {
			response = httprequest.get(BaseUri + string);
		}

		System.out.print(response.getStatusCode());
		System.out.print(response.headers());
		System.out.print("Repose is :" + response.asString());
		jsonPathEvaluator = response.jsonPath();
	}

	@Then("Verify Generate Otp {int} Forbidden Status code.")
	public void verify_Generate_Otp_Forbidden_Status_code(Integer int1) {
		String responseStatusCode = objPojo.getObjUtilities().dpString("RuntimeGenratedStatuscode");
		int responseStatus = Integer.parseInt(responseStatusCode);
		System.out.println("Response StatusCode :" + responseStatusCode);
		Assert.assertEquals(responseStatus, responseStatus);
	}

	@Then("Verify Generate Otp {int} Unauthorized Status code.")
	public void verify_Generate_Otp_Unauthorized_Status_code(Integer int1) {
		String responseStatusCode = objPojo.getObjUtilities().dpString("RuntimeGenratedStatuscode");
		int responseStatus = Integer.parseInt(responseStatusCode);
		System.out.println("Response StatusCode :" + responseStatusCode);
		Assert.assertEquals(responseStatus, responseStatus);
	}

	@Then("I go to {string} and {string} timeout Api Request Saved in Json File {string}with charset")
	public void i_go_to_and_timeout_Api_Request_Saved_in_Json_File_with_charset(String string, String string2,
			String string3) {

		objPojo.getObjWrapperFunctions().waitFor(105);
		String jsonfilepath = System.getProperty("user.dir") + "/src/test/resources/ApiJsonData//" + string3;
		String file = Utilities.readLineByLineJava8(jsonfilepath);
		RestAssuredConfig config = RestAssured.config().encoderConfig(
				(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
		System.out.print(BaseUri + string);

		httprequest = RestAssured
				.given().config(config).accept("application/json").headers("Content-Type", "application/json",
						"x-api-key", "dVi1CAyGnRinGXdpI3jLySHfl1jk3lGn", "Authorization", "Bearer " + BearerToken)
				.body(file).log().all();

		String P = "POST";
		String P1 = "PUT";
		String D = "DELETE";
		String G = "GET";
		if (P.equalsIgnoreCase(string2)) {
			response = httprequest.relaxedHTTPSValidation().post(BaseUri + string);
			int statuscode = response.getStatusCode();
			String status = Integer.toString(statuscode);

			objPojo.getObjUtilities().setDataPool("RuntimeGenratedStatuscode", status);
			String stsCode = objPojo.getObjUtilities().dpString("RuntimeGenratedStatuscode");
		}
		if (P1.equalsIgnoreCase(string2)) {
			response = httprequest.put(BaseUri + string);
		}
		if (D.equalsIgnoreCase(string2)) {
			response = httprequest.delete(BaseUri + string);
		}
		if (G.equalsIgnoreCase(string2)) {
			response = httprequest.get(BaseUri + string);
		}

		System.out.print(response.getStatusCode());
		System.out.print(response.headers());
		System.out.print("Repose is :" + response.asString());
		jsonPathEvaluator = response.jsonPath();
	}

	@Then("I wait for a minute")
	public void i_wait_for_a_minute() {
		objPojo.getObjWrapperFunctions().waitFor(60);
	}
	@Then("I want to wait for {string} Seconds.")
	public void i_want_to_wait_for_Seconds(String Second) {
	    int Time = Integer.parseInt(Second);
	    objPojo.getObjWrapperFunctions().waitFor(Time);
	}

}