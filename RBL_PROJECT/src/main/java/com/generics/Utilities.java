package com.generics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.Stream;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;

/**
 * @ScriptName : Utilities
 * @Description : This class contains utilities function
 * @Author : Vikash Thakur - AQM Technologies
 */
public class Utilities {
	private Pojo objPojo;

	public Utilities(Pojo pojo) {
		this.objPojo = pojo;
	}

	/**
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @param : Step - Step description, resultLog - result log pass/fail
	 *          (true/false), includeMobile - result for mobile(true/false)
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	// @Step("{0}")

	public void logReporter(String step, boolean resultLog) {
		String strLog = step;
		this.addAssertTakeScreenShot(step, strLog, "", "", "", resultLog);
	}


	/**
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @param : Step - Step description, inputValue - Input value, resultLog -
	 *          result log pass/fail (true/false), includeMobile - result for
	 *          mobile(true/false)
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	// @Step("{0} - {1}")
	public void logReporter(String step, String inputValue, boolean resultLog) {
		String strLog = step + " || Input Value : " + inputValue;
		this.addAssertTakeScreenShot(step, strLog, inputValue, "", "", resultLog);
	}

	/**
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @param : Step - Step description, expectedValue - verification point expected
	 *          value, actualValue - verification point actual value, resultLog -
	 *          result log pass/fail (true/false), includeMobile - result for
	 *          mobile(true/false)
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	// @Step("{0} - {1} - {2}")
	public void logReporter(String step, String expectedValue, String actualValue, boolean resultLog) {
		String strLog = step + " || Expected Result : " + expectedValue + " || Actual Result : " + actualValue;
		this.addAssertTakeScreenShot(step, strLog, "", expectedValue, actualValue, resultLog);
	}

	/**
	 * @Method : addAssertTakeScreenShot
	 * @Description :
	 * @param :
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public void addAssertTakeScreenShot(String step, String strLog, String inputValue, String expectedValue,
			String actualValue, boolean resultLog) {
		System.out.println(objPojo.getTestCaseID() + "--> " + strLog);
		Logger logger = LoggerFactory.getLogger(Utilities.class);
		if (resultLog) {
			Reporter.log("Step Description--> " + strLog);
			logger.info("Step Description--> " + objPojo.getTestCaseID() + "-->" + strLog);
			logger.info("Step Description--> " + strLog);
			Assert.assertTrue(true, "Step Description--> " + objPojo.getTestCaseID() + strLog);
			Assert.assertTrue(true, "Step Description--> " + strLog);
		} else {
			String fileName = getDateInSpecifiedFormat("dd_MMM_yyyy_HH_mm_ss") + "_TCID_" + objPojo.getTestCaseID()
					+ ".png";
			String fileWithPath = Pojo.reportPath + "\\target\\custom-reports\\surefire-reports\\ScreenShot\\"
					+ fileName;
			Reporter.log("Step Description--> " + strLog);
			logger.error("Step Description--> " + objPojo.getTestCaseID() + "-->" + strLog);
			logger.error("Step Description--> " + strLog);
			logger.error("Failure URl :-------------> " + objPojo.getDriver().getCurrentUrl());
			System.out.println("URl :-------------> " + objPojo.getDriver().getCurrentUrl());
			this.takeScreenShot(objPojo.getDriver(), fileWithPath);
			objPojo.setTestFailedStep(step);
			Assert.assertTrue(false, "Step Description--> " + step);
		}
	}

	/**
	 * @Method : takeScreenShot
	 * @Description : Take Screen shot for given web driver.
	 * @Author : Vikash Thakur - AQM Technologies
	 * 
	 */
	public boolean takeScreenShot(WebDriver webDriver, String fileWithPath) {
		TakesScreenshot scrShot = ((TakesScreenshot) webDriver);
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(fileWithPath);
		try {
			FileUtils.moveFile(srcFile, destFile);
			this.fileToByte(destFile);
			return true;
		} catch (IOException iOException) {
			iOException.printStackTrace();
			return false;

		}
	}

	/**
	 * @Method : getDateInSpecifiedFormat
	 * @Description : This method takes parameter of your required DateFormat Type
	 *              Like: dd-mm-YYYY DD.MM.YYYY and in return it will give you
	 *              today's date in specified date format
	 * @param : dateFormat like : dd-MM-YYYY
	 * @Author : Vikash Thakur - AQM Technologies
	 **/
	public String getDateInSpecifiedFormat(String dateFormat) {
		String current_date = "";
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		current_date = formatter.format(today);
		return current_date;
	}

	/**
	 * @Method : fileToByte
	 * @Description : Converts image file to byte array for allure.
	 * @Author : Vikash Thakur - AQM Technologies
	 * @throws : IOException
	 */
//	@Attachment(value = "Screenshot", type = "image/png")
	public byte[] fileToByte(File file) throws IOException {
		if (file != null)
			return Files.readAllBytes(Paths.get(file.getPath()));
		else
			return new byte[0];
	}

	/**
	 * Generate random string
	 * 
	 * @return String random string value
	 */
	public String getRandomString(int lenght) {
		String allowedChars = "abcdefghiklmnopqrstuvwxyz";
		String randomstring = "";
		for (int i = 0; i < lenght; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring.toUpperCase();
	}

	/**
	 * Generate random string with numbers
	 * 
	 * @return String random string value
	 */
	public String getRandomNumbers(int length) {
		String allowedChars = "123456789";
		String randomstring = "";
		for (int i = 0; i < length; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring;
	}

	/**
	 * Generate random string with numbers
	 * 
	 * @return String random string value
	 */

	public String getRandomNumbers(String generator) {
		int length = Integer.parseInt(generator.toLowerCase().replace("dynamic", ""));
		String allowedChars = "123456789";
		String randomstring = "";
		for (int i = 0; i < length; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring;
	}

	/**
	 * @author Vikash Thakur - AQM Technologies
	 * @Description : Use Below Method to get Random Number for Specific Range
	 */
	public int getRandomNumbersWithinRange(int minimumLimt, int maximumLimit) {
		try {
			Random rand = new Random();
			return rand.nextInt((maximumLimit - minimumLimt) + 1) + minimumLimt;
		} catch (Exception exception) {
			objPojo.setCustomException("Developer Side Issue");
			exception.printStackTrace();
			return 0;
		}
	}

	/**
	 * 
	 * NOTE : If you want future date : Pass No of Future day 's value as positive
	 * int i.e. 5 If you want Back date : Pass No of Future day 's value as Negative
	 * . i.e. -5
	 * 
	 * @Method : getFutureDateInSpecifiedFormat
	 * 
	 * @Description : This method takes parameter of your required DateFormat Type
	 *              Like: dd-mm-YYYY OR DD.MM.YYYY and in return it will give you
	 *              Future date in specified date format
	 * 
	 * @param1 : dateFormat like : dd-MM-YYYY
	 * 
	 * @param2 : No Of Future Day from now
	 * 
	 * @Author : Vikash Thakur - AQM Technologies
	 * 
	 *         i.e. : if you call getFutureDateInSpecifiedFormat("DD-MM-YYYY",5)
	 *         then it will return future date (current date + next 5 days)
	 * 
	 *         NOTE : If you want future date : Pass No of Future day 's value as
	 *         positive int i.e. 5 If you want Back date : Pass No of Future day 's
	 *         value as Negative . i.e. -5
	 * 
	 */
	public String getFutureOrBackDateInSpecifiedFormat(String dateFormat, int NoOfFutureDay) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, NoOfFutureDay);
		String futureDate = new SimpleDateFormat(dateFormat).format(c.getTime()).toString();
		return futureDate;
	}

	/**
	 * @Method: dpString
	 * @Description: this method returns data from the the previously loaded
	 *               datapool
	 * @param columnHeader - excel file header column name
	 * @return - value for corresponding header
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public String dpString(String columnHeader) {
		try {
			if (objPojo.getObjHashTable().get(columnHeader) == null)
				return "";
			else {
				return objPojo.getObjHashTable().get(columnHeader).trim();
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	/**
	 * @Method: dpInteger
	 * @Description: this method returns data from the the previously loaded
	 *               datapool
	 * @param columnHeader - excel file header column name
	 * @return - value for corresponding header
	 * @Author : Vikash Thakur - AQM Technologies
	 * 
	 */
	public Integer dpInteger(String columnHeader) {
		try {
			if (objPojo.getObjHashTable().get(columnHeader) == null)
				return 0;
			else {
				String value = objPojo.getObjHashTable().get(columnHeader).trim();
				StringTokenizer strTokenizer = new StringTokenizer(value, ".");
				value = strTokenizer.nextToken();
				return Integer.parseInt(value);
			}
		} catch (NoSuchElementException exception) {
			return 0;
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	/**
	 * @Method: setDataPool
	 * @Description: this method returns data from the the previously loaded
	 *               setDataPool
	 * @param columnHeader - excel file header column name
	 * @return - value for corresponding header
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public void setDataPool(String Key, String value) {
		try {
			objPojo.getObjHashTable().put(Key, value);
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	/**
	 * @author : Vikash Thakur
	 * @Date of Creation : 30-July-2019
	 * @return - ramdom values with alpha numeric values with special characters
	 */

	public String getRandomNumbers1(String generator) {
		int length = Integer.parseInt(generator.toLowerCase().replace("dynamic", ""));
		String allowedChars = "abcdefghijklmnopqrstuvxyz@#*$123456789";
		String randomstring = "";
		for (int i = 0; i < length; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring;
	}

	/**
	 * @author : Vikash Thakur
	 * @Date of Creation : 13-Aug-2019
	 */

	public static String readLineByLineJava8(String file) {
		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = java.nio.file.Files.lines(Paths.get(file), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contentBuilder.toString();
	}

	/**
	 * @author : Vikash Thakur
	 * @Date of Creation : 28-August-2019
	 * @return - ramdom values for PAN Number
	 */
	public String panNoGenerator() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String panCharacters = "CPHFATBLJG";
		String numbers = "1234567890";
		String panNumber = null;

		StringBuilder sb = new StringBuilder();

		char[] tempFirstThreeChars = characters.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			char firstThreeChars = tempFirstThreeChars[random.nextInt(tempFirstThreeChars.length)];
			sb.append(firstThreeChars);
		}
		char[] oneChar = panCharacters.toCharArray();
		for (int i = 0; i < 1; i++) {
			char oneCharLetter = oneChar[random.nextInt(oneChar.length)];
			sb.append(oneCharLetter);
		}

		char[] tempFifthChar = characters.toCharArray();
		for (int i = 0; i < 1; i++) {
			char fifthChar = tempFifthChar[random.nextInt(tempFifthChar.length)];
			sb.append(fifthChar);
		}

		char[] tempFourNumbers = numbers.toCharArray();
		for (int i = 0; i < 4; i++) {
			char fourNumbers = tempFourNumbers[random.nextInt(tempFourNumbers.length)];
			sb.append(fourNumbers);
		}

		char[] tempLastChar = characters.toCharArray();
		for (int i = 0; i < 1; i++) {
			char lastChar = tempLastChar[random.nextInt(tempLastChar.length)];
			sb.append(lastChar);
		}

		panNumber = sb.toString();
		return panNumber;

	}

	/**
	 * @author : Vikash Thakur
	 * @Date of Creation : 28-August-2019
	 * @return - ramdom values for TIN Number
	 */
	public String tinNoGenerator() {
		String numbers = "1234567890";
		String tinNumber = null;

		StringBuilder sb = new StringBuilder();
		sb.append("27");
		char[] tempNineNumbers = numbers.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 9; i++) {
			char nineNumbers = tempNineNumbers[random.nextInt(tempNineNumbers.length)];
			sb.append(nineNumbers);
		}
		tinNumber = sb.toString();
		return tinNumber;

	}

	/**
	 * @author : Vikash Thakur
	 * @Date of Creation : 28-August-2019
	 * @return - ramdom values for Address
	 */
	public String randomAddressGenerator() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numbers = "1234567890";
		String address = null;

		StringBuilder sb = new StringBuilder();

		char[] tempThreeNumbers = numbers.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			char threeNumbers = tempThreeNumbers[random.nextInt(tempThreeNumbers.length)];
			sb.append(threeNumbers);
		}
		sb.append("/");

		char[] tempFourNumbers = numbers.toCharArray();
		for (int i = 0; i < 4; i++) {
			char fourNumbers = tempFourNumbers[random.nextInt(tempFourNumbers.length)];
			sb.append(fourNumbers);
		}

		char[] tempEightChars = characters.toCharArray();
		for (int i = 0; i < 8; i++) {
			char eightChars = tempEightChars[random.nextInt(tempEightChars.length)];
			sb.append(eightChars);
		}

		sb.append(" ");

		char[] tempTenChars = characters.toCharArray();
		for (int i = 0; i < 10; i++) {
			char tenChars = tempTenChars[random.nextInt(tempTenChars.length)];
			sb.append(tenChars);
		}
		address = sb.toString();
		return address;

	}

	/**
	 * @author : Gaurav kumar
	 * @Date of Creation : 10-October-2019
	 * @return - ramdom values for Universal ID Number
	 */
	public String universalIdNumberGenerator() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numbers = "1234567890";
		String universalIdNumber = null;

		StringBuilder sb = new StringBuilder();

		char[] tempFirstThreeChars = characters.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			char firstThreeChars = tempFirstThreeChars[random.nextInt(tempFirstThreeChars.length)];
			sb.append(firstThreeChars);
		}

		char[] tempFourNumbers = numbers.toCharArray();
		for (int i = 0; i < 9; i++) {
			char fourNumbers = tempFourNumbers[random.nextInt(tempFourNumbers.length)];
			sb.append(fourNumbers);
		}
		universalIdNumber = sb.toString();
		return universalIdNumber;
	}

	/**
	 * @author : Vikash Thakur
	 * @Date of Creation : 25-September-2019
	 * @Updated by : Vikash Thakur
	 * @Updated on: 09-October-2019
	 * @return - Random Name generator
	 */
	public String getRandomNameGenerator() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String randomname = null;

		StringBuilder sb = new StringBuilder();

		char[] tempFirstEightChars = characters.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			char firstEightChars = tempFirstEightChars[random.nextInt(tempFirstEightChars.length)];
			sb.append(firstEightChars);
		}
		sb.append(" ");

		char[] tempFirstSevenChars = characters.toCharArray();
		for (int i = 0; i < 7; i++) {
			char firstSevenChars = tempFirstSevenChars[random.nextInt(tempFirstSevenChars.length)];
			sb.append(firstSevenChars);
		}

		randomname = sb.toString();
		return randomname;

	}

	/**
	 * @author : Vikash Thakur
	 * @Date of Creation : 28-August-2019
	 * @return - ramdom values for CIN Number
	 * @Date of Updation : 01-11-2019
	 */
	public String cinNoGenerator() {
		String numbers = "1234567890";
		String cinNumber = null;

		StringBuilder sb = new StringBuilder();
		sb.append("U");

		char[] tempFiveNumbers = numbers.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			char fiveNumbers = tempFiveNumbers[random.nextInt(tempFiveNumbers.length)];
			sb.append(fiveNumbers);
		}
		sb.append("MH");
		sb.append("2014");
		sb.append("PTC");

		char[] tempSixNumbers = numbers.toCharArray();
		for (int i = 0; i < 6; i++) {
			char sixNumbers = tempSixNumbers[random.nextInt(tempSixNumbers.length)];
			sb.append(sixNumbers);
		}

		cinNumber = sb.toString();
		return cinNumber;

	}
	
	
	/**
	 * Generate random stringNO
	 * 
	 * @return String random string value
	 */
	public String getRandomStringNO(int lenght) {
		String allowedChars = "abcdefghiklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String randomstring = "";
		for (int i = 0; i < lenght; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring;
	}
	/**
	 * @author : Vikash Thakur
	 * @Date of Creation : 09-July-2019
	 * @param :
	 *            Sheet Name from the excel file
	 * @return - Load the test-data according to the scenario name.
	 */

	synchronized public void loadDataProvider(String fileName) {
		XSSFWorkbook workBook;
		try {
			String testDataPath = System.getProperty("user.dir") + "/src/test/resources/testData/TestData.xlsx";
			workBook = new XSSFWorkbook(new FileInputStream(testDataPath));
			XSSFSheet sheet = workBook.getSheet(fileName);
			for (int j = 0; j <= sheet.getLastRowNum(); j++)
				if (sheet.getRow(j).getCell(1).getStringCellValue().equalsIgnoreCase(objPojo.getTestCaseID())) {
					for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
						try {
							String value = sheet.getRow(j).getCell(i).getStringCellValue();
							if (!value.trim().equals(""))
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										sheet.getRow(j).getCell(i).getStringCellValue());
						} catch (IllegalStateException e) {
							try {
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										""+sheet.getRow(j).getCell(i).getNumericCellValue());
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
}	