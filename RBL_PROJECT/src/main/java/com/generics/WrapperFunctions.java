package com.generics;

import java.io.File;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.text.WordUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;
//import com.rbl.util.CommonCode;

/**
 * @ScriptName : WrapperFunctions
 * @Description : Core wrapper function required for framework
 * @Author : Vikash Thakur - AQM Technologies
 */
public class WrapperFunctions extends LoadableComponent<WrapperFunctions> {
	private Pojo objPojo;
	Boolean flag = false;
	
	public WrapperFunctions(Pojo pojo) {
		this.objPojo = pojo;
		
	}

	/**
	 * @Description : This is wrapper method wait for element presence located
	 * @param : locator - By identification of element
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public void waitForElementPresence(By locator) throws NotFoundException {
		objPojo.getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * @Description : This is wrapper method wait for visibility of element located
	 * @param :
	 *            locator - By identification of element
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public void waitForElementVisibilityLocated(By locator) throws NotFoundException {
		objPojo.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForElementVisibility(WebElement webElement) throws NotFoundException {
		objPojo.getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement));
	}

	/**
	 * @Description : This is wrapper method wait for element to be clickable
	 * @param :
	 *            locator - By identification of element
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public void waitForElementToBeClickable(By locator) throws NotFoundException {
		objPojo.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * @Description : This is wrapper method wait for visibility of element located
	 * @param :
	 *            locator - By identification of element
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public void waitForElementInVisibilityLocated(By locator) throws NotFoundException {
		objPojo.getWebDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	/**
	 * @Description : This is wrapper method to check the web element is displayed
	 *              on the page
	 * @param :
	 *            locator - By identification of element
	 * @return : - true if element present
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public boolean checkElementDisplayedWithoutWait(By locator) {
		try {
			return objPojo.getDriver().findElement(locator).isDisplayed();
		} catch (Exception exception) {
			return false;
		}
	}

	public boolean openNewWindow() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) objPojo.getDriver();
			js.executeScript("window.open('','_blank');");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean closeDriver() {
		try {
			objPojo.getDriver().close();
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/** @Author : Vikash Thakur - AQM Technologies */
	public void waitAfterEachClick() {
		waitFor(objPojo.getAfterClickwait());
	}

	/**
	 * @Method : waitFor
	 * @Description : Waits for the specified amount of [timeInMilliseconds].
	 * @param :
	 *            timeUnitSeconds - wait time seconds
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public boolean waitFor(int timeUnitSeconds) {
		try {
			Thread.sleep(TimeUnit.MILLISECONDS.convert(timeUnitSeconds, TimeUnit.SECONDS));
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * Scroll element to view using javascript This script is used for scrolling
	 * down.
	 * 
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public boolean scrollToViewDown(By locator) {
		try {
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			((JavascriptExecutor) objPojo.getDriver()).executeScript("arguments[0].scrollIntoView(false);", webElement);
			return true;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : elementHighlight
	 * @Description : Highlight element
	 * @param :
	 *            locator - By identification of element
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public void elementHighlight(By locator) {
		WebElement element = objPojo.getDriver().findElement(locator);
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) objPojo.getDriver();
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"color: yellow; border: 3px solid yellow;");
			waitFor(1);
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		}
	}

	/**
	 * @Method : selectDropDownOption
	 * @Description : This is wrapper method select drop down element
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            option - drop down element (user may specify text/value/index)
	 * @param :
	 *            selectType - select dorp down element by Text/Value/Index
	 * @Author : Vikash Thakur - AQM Technologies
	 */

	public boolean selectDropDownOption(By locator, String option, String... selectType) {
		try {
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			waitForElementVisibility(webElement);
			Select sltDropDown = new Select(webElement);
			if (selectType.length > 0 && !selectType[0].equals("")) {
				if (selectType[0].equalsIgnoreCase("Value"))
					sltDropDown.selectByValue(option);
				else if (selectType[0].equalsIgnoreCase("Text"))
					sltDropDown.selectByVisibleText(option);
				else if (selectType[0].equalsIgnoreCase("Index"))
					sltDropDown.selectByIndex(Integer.parseInt(option));
				return true;
			} else {
				List<WebElement> options = sltDropDown.getOptions();
				boolean blnOptionAvailable = false;
				int iIndex = 0;
				for (WebElement weOptions : options) {
					if (weOptions.getText().trim().equals(option)) {
						sltDropDown.selectByIndex(iIndex);
						blnOptionAvailable = true;
						break;
					} else
						iIndex++;
				}
				return blnOptionAvailable;
			}

		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	@Override
	protected void load() {
	}

	@Override
	protected void isLoaded() throws Error {
	}

	/**
	 * @Author : Vikash Thakur - AQM Technologies
	 * @Description : This method is for getting the text on the alert box.
	 */
	public String alertGetText() {
		Alert alert = objPojo.getDriver().switchTo().alert();
		return alert.getText();

	}

	/**
	 * @Method : click
	 * @Description : This is wrapper method to click on web element
	 * @param :
	 *            WebElement -By identification of Locator
	 * @return : - true if click successful
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public boolean click(WebElement element) {
		try {
			waitForElementVisibility(element);
			element.click();
			waitAfterEachClick();
			return true;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description : index start with 0 , Hence we have to do "n-1" Also month
	 *              value does not have prefix "0" hence we have to replace "0" with
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public boolean datePicker(By locator, String date) {
		try {
			String[] dateArray = date.split("/");
			String month = dateArray[0];
			int tempIntMonth = 0;
			try {
				tempIntMonth = Integer.parseInt(month);
				if (tempIntMonth >= 1) {
					if (((dateArray[0].startsWith("0")))) {
						tempIntMonth = tempIntMonth - 1;
						if (tempIntMonth == 0)
							dateArray[0] = tempIntMonth + "";
						else {
							dateArray[0] = tempIntMonth + "";
							dateArray[0] = dateArray[0].replaceAll("0", "");
						}
					} else {
						tempIntMonth = tempIntMonth - 1;
						dateArray[0] = tempIntMonth + "";
					}
				} else {
					dateArray[0] = tempIntMonth + "";
				}
			} catch (Exception e) {
			}
			objPojo.getObjUtilities().logReporter("Date to Select in Calendar Datepicker", date, true);
			By divCalendarPopup = By.xpath("//div[@id='ui-datepicker-div']");
			By lnkDate = By.xpath(
					"//div[@id='ui-datepicker-div']/table[@class='ui-datepicker-calendar']/tbody/tr/td/a[contains(text(),'"
							+ (dateArray[1].startsWith("0") ? dateArray[1].substring(dateArray[1].indexOf("0") + 1)
									: dateArray[1])
							+ "')]");
			By drpMonth = By.xpath(
					"//div[@id='ui-datepicker-div']/div/div[@class='ui-datepicker-title']/select[@class='ui-datepicker-month']");
			By drpYear = By.xpath(
					"//div[@id='ui-datepicker-div']/div/div[@class='ui-datepicker-title']/select[@class='ui-datepicker-year']");

			objPojo.getObjUtilities().logReporter("Click on calendar icon", this.click(locator));
			objPojo.getObjUtilities().logReporter("Verify calendar popup displayed",
					this.checkElementDisplayed(divCalendarPopup));
			objPojo.getObjUtilities().logReporter("Select year from calendar popup", dateArray[2],
					this.selectDropDownOption(drpYear, dateArray[2], "Value"));
			objPojo.getObjUtilities().logReporter("Select month from calendar popup", dateArray[0],
					this.selectDropDownOption(drpMonth, (dateArray[0].toString()), "Value"));
			objPojo.getObjUtilities().logReporter("Select date from calendar popup", dateArray[1], this.click(lnkDate));
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : switchToWindowUsingTitle
	 * @Description : This is wrapper method used switch to window using the given
	 *              title
	 * @param :
	 *            locator - Window title
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public boolean switchToWindowUsingTitle(String windowTitle) {
		try {
			String mainWindowHandle = objPojo.getDriver().getWindowHandle();
			Set<String> openWindows = objPojo.getDriver().getWindowHandles();

			if (!openWindows.isEmpty()) {
				for (String windows : openWindows) {
					String window = objPojo.getDriver().switchTo().window(windows).getTitle();
					if (windowTitle.equals(window))
						return true;

					if (window.equalsIgnoreCase("New Tab") || window.equals("")) {
						return true;
					} else
						objPojo.getDriver().switchTo().window(mainWindowHandle);
				}
			}
			return false;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : switchToWindowUsingURL
	 * @Description : This is wrapper method used switch to window using the given
	 *              URL
	 * @param :
	 *            locator - Window title
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public boolean switchToWindowContainsURL(String windowURL) {
		try {
			Set<String> openWindows = objPojo.getDriver().getWindowHandles();
			if (!openWindows.isEmpty()) {
				for (String windows : openWindows) {
					String currentWindowURL = objPojo.getDriver().switchTo().window(windows).getCurrentUrl();
					if (currentWindowURL.contains(windowURL))
						return true;
				}
			}
			return false;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Author : Vikash Thakur - AQM Technologies
	 * @Description : Accept Alert Handle Pop up
	 */
	public boolean alertBoxAccept() {
		Alert alert = objPojo.getDriver().switchTo().alert();
		alert.accept();
		return true;
	}

	/**
	 * @Method : selectRadioButton
	 * @Description : This is wrapper method select/deselect radio button
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            status - select/deselect
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public boolean selectRadioButton(By locator, boolean status) {
		try {
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			if (webElement.getAttribute("type").equals("radio")) {
				if ((webElement.isSelected() && !status) || (!webElement.isSelected() && status))
					webElement.click();
				if ((webElement.isSelected() && !status) || (!webElement.isSelected() && status))
					this.click(locator);
				return true;
			} else
				return false;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : isAlertPresent
	 * @Description : This is wrapper method used for Alert Present or not on Page
	 * @param :
	 *            WebElement - By identification of Locator
	 * @return : - true if Alert is Present
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public boolean isAlertPresent() {
		try {
			if (this.verifyIsAlertPresent())
				System.out.println("Alert is present on webpage.");
			else {
				System.out.println("Alert not present on webpage.");
				return false;
			}
			return true;
		} catch (UnhandledAlertException exception) {
			objPojo.setCustomException("UnhandledAlertException");
			System.out.println("I got no such " + exception.getMessage());
			return false;
		} catch (NoAlertPresentException exception) {
			objPojo.setCustomException("NoAlertPresentException");
			System.out.println("No Alert Present");
			return false;
		} catch (Exception exception) {
			System.out.println(exception.toString());
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : verifyIsAlertPresent
	 * @Description : This is wrapper method used for Alert Present or not on Page
	 * @param :
	 *            WebElement - By identification of Locator
	 * @return : - true if Alert is Present
	 * @author : Vikash Thakur - AQM Technologies
	 */

	public boolean verifyIsAlertPresent() {
		boolean blnFlag = false;
		try {
			objPojo.getDriver().switchTo().alert();
			return blnFlag = true;
		} catch (UnhandledAlertException exception) {
			objPojo.setCustomException("UnhandledAlertException");
			System.out.println("I got no such " + exception.getMessage());
			return false;
		} catch (NoAlertPresentException exception) {
			objPojo.setCustomException("NoAlertPresentException");
			System.out.println("No Alert Present");
			return blnFlag;
		}
	}

	/**
	 * @Method : click
	 * @Description : This is wrapper method to click on web element
	 * @param :
	 *            locator - By identification of element
	 * @return : - true if click successful
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public boolean click(By locator) {
		try {
			waitForElementPresence(locator);
			waitForElementToBeClickable(locator);
			objPojo.getDriver().manage().timeouts().setScriptTimeout(objPojo.getScriptTimeoutWait(), TimeUnit.SECONDS);
			JavascriptExecutor js = (JavascriptExecutor) objPojo.getDriver();
			js.executeScript("return arguments[0].click()", getElementFluent(locator));
			waitAfterEachClick();
			return true;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			try {
				waitForElementPresence(locator);
				waitForElementToBeClickable(locator);
				getElementFluent(locator).click();
				waitAfterEachClick();
				return true;
			} catch (Exception exceptionJavascript) {
				objPojo.setCustomException("NoSuchElement Exception");
				return false;
			}
		}
	}

	@SuppressWarnings("deprecation")
	public WebElement getElementFluent(final By locator) throws NoSuchElementException, TimeoutException {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(objPojo.getDriver())
				.withTimeout(Integer.parseInt(objPojo.getObjConfig().getProperty("driver.WebDriverWait")),
						TimeUnit.SECONDS)
				.pollingEvery(Integer.parseInt(objPojo.getObjConfig().getProperty("driver.FluentWaiPulling")),
						TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class).ignoring(InvalidElementStateException.class);

		WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return objPojo.getDriver().findElement(locator);
			}
		});

		return webElement;
		
	}

	/**
	 * @Description : This is wrapper method to check the web element is displayed
	 *              on the page
	 * @param :
	 *            locator - By identification of element
	 * @return : - true if element present
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public boolean checkElementDisplayed(By locator) {
		try {
			this.waitForElementVisibilityLocated(locator);
			return objPojo.getDriver().findElement(locator).isDisplayed();
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * @Method : setText
	 * @Description : This is wrapper method to set text for input element
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            fieldValue - field value as string
	 * @return : - true if text entered successfully
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public boolean setText(By locator, String fieldValue) {
		try {
			/*
			 * waitForElementPresence(locator); waitForElementVisibilityLocated(locator);
			 */
			WebElement webElement = getElementFluent(locator);
			clearText(webElement);
			// webElement.clear();
			webElement.sendKeys(fieldValue);
			return true;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	public void clearText(WebElement webElement) {
		/*
		 * Actions navigator = new Actions(objPojo.getDriver());
		 * navigator.click(webElement) .sendKeys(Keys.END) .keyDown(Keys.SHIFT)
		 * .sendKeys(Keys.HOME) .keyUp(Keys.SHIFT) .sendKeys(Keys.BACK_SPACE)
		 * .perform();
		 */
		// webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
		webElement.clear();
	}

	/**
	 * @Method : selectCheckBox
	 * @Description : This is wrapper method select/deselect checkbox
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            status - select/deselect
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public boolean selectCheckBox(By locator, boolean status) {
		try {
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			if (webElement.getAttribute("type").equals("checkbox")) {
				if ((webElement.isSelected() && !status) || (!webElement.isSelected() && status))
					webElement.click();
				if ((webElement.isSelected() && !status) || (!webElement.isSelected() && status))
					this.click(locator);
				return true;
			} else
				return false;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			return false;
		}
	}

	/**
	 * @Method : getText
	 * @Description : This is wrapper method to extract the text value of an
	 *              webelement : This method is used for dom ul/li/div/div/h4/span
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            textBy - String - "value" or "text"
	 * @return : text value of the passed locator
	 * @Author : Vikash Thakur - AQM Technologies
	 * 
	 */
	public String getText(By locator, String textBy) {
		String strText = "";
		try {
			waitForElementPresence(locator);
			waitForElementVisibilityLocated(locator);
			WebElement webElement = this.objPojo.getDriver().findElement(locator);
			scrollToViewDown(locator);
			switch (textBy.toLowerCase()) {
			case "value":
				strText = webElement.getAttribute("value");
				break;

			case "text":
				strText = webElement.getText();
				break;
			default:
				strText = webElement.getText();
				break;
			}
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
		}
		return strText;
	}

	/**
	 * @Description : This is wrapper method to check the web element is enabled on
	 *              the page
	 * @param :
	 *            locator - By identification of element
	 * @return : - true if element present
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public boolean checkElementEnabled(By locator) {
		try {
			this.waitForElementVisibilityLocated(locator);
			this.waitForElementToBeClickable(locator);
			return objPojo.getDriver().findElement(locator).isEnabled();
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * @Description : Use below method to Select Unordered Dropdown Values
	 * @param :
	 *            Locator to click and Get Specific Element Listafter click and
	 *            Value to Select as a text
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public boolean selectUnorderedDropDown(By locator, String value) {
		try {
			this.click(locator);
			this.waitForElementVisibility(objPojo.getDriver().findElement(locator).findElement(By.xpath(
					"..//ancestor::div[@class='selectric']//following-sibling::div[@class='selectric-items']//ul//li")));
			List<WebElement> webElement = objPojo.getDriver().findElement(locator).findElements(By.xpath(
					"..//ancestor::div[@class='selectric']//following-sibling::div[@class='selectric-items']//ul//li"));
			for (WebElement webElement2 : webElement) {
				if (webElement2.getText().equals(value)) {
					click(webElement2);
					return true;
				}
			}
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
		}
		return false;
	}

	/**
	 * @Method : sendKeyBoardKeys
	 * @Description : This is wrapper method is used to send keyboard keys
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            key - key name
	 * @return : - true if text entered successfully
	 * @author : Apurva Khaire - AQM Technologies
	 */
	public boolean sendKeyboardStroke(By locator, String SendKeys) {
		try {
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			if (SendKeys.equalsIgnoreCase("Tab")) {
				webElement.sendKeys(Keys.TAB);
			} else if (SendKeys.equalsIgnoreCase("Enter")) {
				webElement.sendKeys(Keys.ENTER);
			} else if (SendKeys.equalsIgnoreCase("Down")) {
				webElement.sendKeys(Keys.DOWN);
			} else if (SendKeys.equalsIgnoreCase("Cancel")) {
				webElement.sendKeys(Keys.CANCEL);
			} else if (SendKeys.equalsIgnoreCase("ArrowUp")) {
				webElement.sendKeys(Keys.ARROW_UP);
			} else if (SendKeys.equalsIgnoreCase("Clear")) {
				webElement.sendKeys(Keys.CLEAR);
			} else if (SendKeys.equalsIgnoreCase("ArrowLeft")) {
				webElement.sendKeys(Keys.ARROW_LEFT);
			} else
				return false;
			return true;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}

	}

	/**
	 * @Method : Load Base URL
	 * @Description : Use Following method to Navigate to Specific URL
	 * @param :
	 *            moduleName : Name of app path to be navigated
	 * 
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public void loadBaseURL() {
		String baseurl = objPojo.getObjConfig().getProperty("web.protocol") + "://"
				+ objPojo.getObjConfig().getProperty("web.domain");
		objPojo.getDriver().navigate().to(baseurl);
		objPojo.getObjUtilities().logReporter("Open URL - '" + baseurl + "'.", true);
	}
	
	/*public void loadBaseURL(String moduleName) {
		String baseurl = objPojo.getObjConfig().getProperty("web.protocol") + "://"
				+ objPojo.getObjConfig().getProperty("web.domain") + ":"
				+ objPojo.getObjConfig().getProperty("web.port")
				+ objPojo.getObjConfig().getProperty("web." + moduleName.trim().toUpperCase() + ".path");
		objPojo.getDriver().navigate().to(baseurl);
		objPojo.getObjUtilities().logReporter("Open URL - '" + baseurl + "'.", true);
	}*/

	/**
	 * @Method : verifyDropdownOptionValues
	 * @Description : This is wrapper method to verify Dropdown Option Present
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            option - drop down element (if want to verify Multiple options,
	 *            Provide your value separated By ";"
	 * @Author : Vikash Thakur - AQM Technologies
	 */

	public boolean verifyDropdownOptionValues(By locator, String option) {
		try {
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			Select sltDropDown = new Select(webElement);
			List<WebElement> options = sltDropDown.getOptions();
			boolean optionValueFlag = false;
			ArrayList<String> optionList;
			if (option.contains(";"))
				optionList = new ArrayList<String>(Arrays.asList(option.trim().split(";")));
			else {
				optionList = new ArrayList<String>();
				optionList.add(option);
			}
			for (WebElement string : options) {
				String optionValue = string.getText().trim();
				if (optionList.contains(optionValue))
					optionValueFlag = true;
				optionList.remove(optionValue);
				if (optionList.isEmpty())
					break;
			}
			return optionValueFlag;

		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description : This is wrapper method to check the web element is enabled on
	 *              the page
	 * @param :
	 *            locator - By identification of element
	 * @return : - true if element present
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public boolean checkElementSelected(By locator) {
		try {
			this.waitForElementVisibilityLocated(locator);
			return objPojo.getDriver().findElement(locator).isSelected();
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * @Method : setBootStrapDivText
	 * @Description : This is wrapper method to set text for input element
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            fieldValue - field value as string
	 * @return : - true if text entered successfully
	 * @author : Vikash Thakur
	 */
	public boolean setBootStrapInputText(By locator, String fieldValue) {
		try {
			boolean selected = false;
			WebElement input = getElementFluent(locator);
			clearText(input);
			input.sendKeys(fieldValue);
			WebElement ul = input.findElement(By.xpath(".//parent::div//ul"));
			waitForPresenceOfNestedElementLocated(ul, By.xpath(".//li"));
			List<WebElement> list = ul.findElements(By.xpath(".//li"));
			// System.out.println("list------------>" + list.size());
			for (WebElement expectedLink : list) {
				// System.out.println("innerHTML---> " +
				// expectedLink.getAttribute("innerHTML"));
				if (expectedLink.getAttribute("innerHTML").trim().contains(fieldValue)) {
					waitAfterEachClick();
					expectedLink.click();
					selected = true;
					break;
				}
			}
			return selected;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description : This is wrapper method wait for element presence located
	 * @param :
	 *            locator - By identification of element
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public void waitForPresenceOfNestedElementLocated(WebElement webElement, By sub_locator) throws NotFoundException {
		objPojo.getWebDriverWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(webElement, sub_locator));
	}

	/**
	 * @Method : getAllValueFromDropDown
	 * @Description : This is wrapper method get all selected drop down element
	 * @param :
	 *            locator - By identification of element
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public ArrayList<String> getAllSelectedValueFromDropDown(By locator) {
		try {
			waitForElementPresence(locator);
			ArrayList<String> list = new ArrayList<String>();
			Select selectDorpDown = new Select(objPojo.getDriver().findElement(locator));
			List<WebElement> selectedDorpDownValue = selectDorpDown.getAllSelectedOptions();
			for (WebElement webElement : selectedDorpDownValue) {
				list.add(webElement.getText());
			}
			return list;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * @Method : uploadFile
	 * @Description : This is wrapper method to upload file using Selenium Wrapper
	 * @param :
	 *            filePath - path of file to upload
	 * @return : - true if upload is successful
	 * @author : Vikash Thakur - AQM Technologies
	 */
	public boolean uploadFile(By locator, String fileNameOnly, int thinkTime) {
		try {
			String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\AllDocs\\"
					+ fileNameOnly;
			// System.out.println("Upload file Path: " + filePath);
			if (!setText(locator, filePath))
				return false;
			waitFor(thinkTime);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : deSelectDropDownOption
	 * @Description : This is wrapper method deselect drop down element
	 * @param :
	 *            locator - By identification of element
	 * @param :
	 *            option - drop down element (user may specify text/value/index)
	 * @param :
	 *            selectType - select dorp down element by Text/Value/Index
	 * @Author : Vikash Thakur - AQM Technologies
	 */

	public boolean deSelectDropDownOption(By locator, String option, String... selectType) {
		try {
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			waitForElementVisibility(webElement);
			Select sltDropDown = new Select(webElement);
			if (selectType.length > 0 && !selectType[0].equals("")) {
				if (selectType[0].equalsIgnoreCase("Value"))
					sltDropDown.deselectByValue(option);
				else if (selectType[0].equalsIgnoreCase("Text"))
					sltDropDown.deselectByVisibleText(option);
				else if (selectType[0].equalsIgnoreCase("Index"))
					sltDropDown.deselectByIndex(Integer.parseInt(option));
				return true;
			} else {
				List<WebElement> options = sltDropDown.getOptions();
				boolean blnOptionAvailable = false;
				int iIndex = 0;
				for (WebElement weOptions : options) {
					if (weOptions.getText().trim().equals(option)) {
						sltDropDown.selectByIndex(iIndex);
						blnOptionAvailable = true;
						break;
					} else
						iIndex++;
				}
				return blnOptionAvailable;
			}

		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/***
	 * @Method : doubleClick
	 * @Description : This is wrapper method used for doubleClick on element*
	 * @param :
	 *            locator - By identification of element*
	 * @return:-true if double click successful*
	 * @Author : Vikash Thakur - AQM Technologies
	 */

	public boolean doubleClick(By locator) {
		try {
			waitForElementPresence(locator);
			waitForElementToBeClickable(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			Actions actionBuilder = new Actions(objPojo.getDriver());
			actionBuilder.doubleClick(webElement).build().perform();
			waitAfterEachClick();
			return true;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/***
	 * 
	 * @Method : getAttribute
	 * @Description : This function return locator attribute*
	 * @param :
	 *            locator - By identification of element*
	 * @author : Vikash Thakur - AQM Technologies
	 */

	public String getAttribute(By locator, String sElementAttribute) {
		String strText = "";
		try {
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			strText = webElement.getAttribute(sElementAttribute);
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
		}
		return strText;
	}

	public boolean setValidatorSheetData(String dataToSet, String SheetName, String RowNumber, String ColumnNumber) {
		try {
			objPojo.getDataValidator().getSheet(SheetName).getRow(Integer.parseInt(RowNumber))
					.getCell(Integer.parseInt(ColumnNumber)).setCellValue(dataToSet);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * @Created By Shwetha
	 * 
	 * @Updated By Nikhil on 10-October-2019
	 */

	public boolean datePickerForMultipleMonths(By element, String date) {
		try {
			String[] dateArray = date.split("-");
			String dt = dateArray[0];
			String month = dateArray[1];
			String year = dateArray[2];

			month = WordUtils.abbreviate((Month.of(Integer.parseInt(month)).name()), 0, 3, null);
			String month1 = WordUtils.capitalizeFully(month);
			By divCalendarPopup = By.xpath("//div[contains(@class,'datepicker datepicker-dropdown')]");

			objPojo.getObjUtilities().logReporter("Click on calendar icon", this.click(element));
			objPojo.getObjUtilities().logReporter("Verify calendar popup displayed",
					this.checkElementDisplayed(divCalendarPopup));
			By middleLinkDaysDatePickerSwitch = By
					.xpath("//div[@class='datepicker-days']//table//thead//tr[2]//th[@class='datepicker-switch']");
			objPojo.getObjWrapperFunctions().click(middleLinkDaysDatePickerSwitch);
			By middleLinkMonthDatePickerSwitch = By
					.xpath("//div[@class='datepicker-months']//table//thead//tr[2]//th[@class='datepicker-switch']");
			objPojo.getObjWrapperFunctions().click(middleLinkMonthDatePickerSwitch);
			By lnkMonth = By.xpath(
					"//div[contains(@class,'datepicker datepicker-dropdown')]//div[@class='datepicker-months']//table//tbody//tr//td//span[contains(text(),'"
							+ month1 + "')]");
			By lnkYear = By.xpath(
					"//div[contains(@class,'datepicker datepicker-dropdown')]//div[@class='datepicker-years']//table//tbody//tr//td//span[contains(text(),'"
							+ year + "')]");
			By lnkDate = By.xpath(
					"//div[contains(@class,'datepicker datepicker-dropdown')]//div[@class='datepicker-days']//table//tbody//tr//td[contains(text(),'"
							+ Integer.parseInt(dt) + "')]");
			By navigatorForYears = By.xpath(
					"//div[contains(@class,'datepicker datepicker-dropdown')]//div[@class='datepicker-years']//table//thead//tr[2]//th[@class='prev']");
			while (!checkElementDisplayedWithoutWait(lnkYear)) {
				objPojo.getDriver().findElement(navigatorForYears).click();
			}

			objPojo.getObjUtilities().logReporter("Click on the year link on date picker",
					objPojo.getObjWrapperFunctions().click(lnkYear));
			objPojo.getObjUtilities().logReporter("Click on the month link on date picker",
					objPojo.getObjWrapperFunctions().click(lnkMonth));
			objPojo.getObjUtilities().logReporter("Click on the date link on date picker",
					objPojo.getObjWrapperFunctions().click(lnkDate));

			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : getLatestDownloadedFileName
	 * @Description : This is wrapper method get Latest Downloaded File Name
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public String getLatestDownloadedFileName() {
		waitFor(10);
		String latestFileName = "";
		try {
			File downloadDirectory = new File(objPojo.getDownloadPath());
			File[] fileList = downloadDirectory.listFiles();
			if (fileList == null || fileList.length == 0)
				return "";
			File lastModified = fileList[0];
			for (int i = 1; i < fileList.length; i++) {
				if (lastModified.lastModified() < fileList[i].lastModified()) {
					lastModified = fileList[i];
				}
			}
			latestFileName = lastModified.getName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return latestFileName;
	}

	/**
	 * @Method : actionClick
	 * @Description : This is wrapper method click using mouse movement
	 * @param :
	 *            locator - By identification of element
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	
	
	public boolean actionClick(final By locator) {
		try {
			this.waitForElementPresence(locator);
			this.waitForElementToBeClickable(locator);
			final WebElement webElement = this.objPojo.getDriver().findElement(locator);
			final Actions actionBuilder = new Actions(this.objPojo.getDriver());
			actionBuilder.click(webElement).build().perform();
			return true;
		} catch (NoSuchElementException exception) {
			this.objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception2) {
			this.objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception2.getMessage());
			exception2.printStackTrace();
			return false;
		} catch (NotFoundException exception3) {
			this.objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception3.getMessage());
			exception3.printStackTrace();
			return false;
		} catch (ElementNotVisibleException exception4) {
			this.objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception4.getMessage());
			exception4.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception5) {
			this.objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception5.getMessage());
			exception5.printStackTrace();
			return false;
		} catch (ElementNotSelectableException exception6) {
			this.objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception6.getMessage());
			exception6.printStackTrace();
			return false;
		} catch (Exception exception7) {
			this.objPojo.setCustomException("NoSuchElement Exception");
			exception7.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : getSelectedValueFromDropDown
	 * @Description : This is wrapper method select drop down element
	 * @param :
	 *            locator - By identification of element
	 * @Author : Vikash Thakur - AQM Technologies
	 */
	public String getSelectedValueFromDropDown(By locator) {
		try {
			waitForElementPresence(locator);
			Select selectDorpDown = new Select(objPojo.getDriver().findElement(locator));
			String selectedDorpDownValue = selectDorpDown.getFirstSelectedOption().getText();
			return selectedDorpDownValue;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotSelectableException exception) {
			objPojo.setCustomException("ElementNotSelectableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * @Method : clearText
	 * @Description : This is wrapper method to clear value from locator
	 * @param :
	 *            locator - By identification of element
	 * @Author : Vikash Thakur - AQM Technologies
	 */

	public boolean clearText(By locator) {
		try {
			boolean flag = false;
			WebElement webElement = getElementFluent(locator);
			if (webElement.getTagName().equals("input")) {
				webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
				flag = true;
			}
			return flag;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : isDisabledThroughClass
	 * @Description : This is wrapper method is used to check whether element is
	 *              disabled or not through class
	 * @param :
	 *            locator - By identification of element
	 * @Author : Shwetha Talapanty - AQM Technologies
	 */
	public boolean isDisabledThroughClass(By locator) {
		WebElement webElement = objPojo.getDriver().findElement(locator);
		String classes = webElement.getAttribute("class");
		for (String c : classes.split(" ")) {
			if (c.equals("disabled")) {
				return true;
			}
		}

		return false;
	}
	/**
	 * @Description : This is wrapper method to check whether the option in present in the dropdown
	 * @param :
	 *            locator - By identification of element
	 * @return 
	 * @Author : Shwetha Talapanty - AQM Technologies
	 */
	public boolean checkOptionPresentInDropdown(By locator,String option) {
		
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			waitForElementVisibility(webElement);			
			Select select = new Select(webElement);
			List<WebElement> options = select.getOptions();
			boolean blnOptionAvailable = false;
			int iIndex = 0;
			for (WebElement weOptions : options) {
				if (weOptions.getText().trim().equals(option)) {			
			    System.out.println("Value exists");
			    return true;
			}						
		}
			return false;				
	}
	
	public boolean switchToFrame(String frameName) {
		 objPojo.getDriver().switchTo().frame(frameName);
		 return true;
	}
	//shreeya
	public List<String> locateElements(By locator){
		ArrayList<String> tabs2 = new ArrayList<String> (objPojo.getDriver().getWindowHandles());
		objPojo.getDriver().switchTo().window(tabs2.get(1));
		objPojo.getDriver().manage().window().maximize();
		List<WebElement> elements=objPojo.getDriver().findElements(locator);
		List<String> elementText=new ArrayList<>();
		for(WebElement element: elements) 
			elementText.add(element.getText());
		return elementText;
	}
	
	public List<String> locateElementsWithOutSwitch(By locator){
		List<WebElement> elements=objPojo.getDriver().findElements(locator);
		List<String> elementText=new ArrayList<>();
		for(WebElement element: elements) 
			elementText.add(element.getText());
		return elementText;
	}
	
	public List<WebElement> locateElements(By locator,String elementString){
		System.out.println("elements locate : "+elementString);
		List<WebElement> elements=objPojo.getDriver().findElements(locator);
		return elements;
	}
	
	//Shreeya
	public void closeSecondWindow() {
		
		  String base = objPojo.getDriver().getWindowHandle();
		    Set <String> set = objPojo.getDriver().getWindowHandles();
		    String[] child=set.toArray(new String[1]);
		    set.remove(base);
		    assert set.size()==1;

		    objPojo.getDriver().switchTo().window(child[1]);

		    objPojo.getDriver().close();
		    objPojo.getDriver().switchTo().window(child[0]);
	}
	
	public void switchPerviousWindow() {
		  String base = objPojo.getDriver().getWindowHandle();
		    Set <String> set = objPojo.getDriver().getWindowHandles();
		    String[] child=set.toArray(new String[1]);
		    set.remove(base);
		    assert set.size()==1;

		    objPojo.getDriver().switchTo().window(child[0]);

		    
	}
	
	public String currentWindow() {
		String base = objPojo.getDriver().getWindowHandle();
		return base;
	}
	
	public WebDriver SwitchToWindow(int index) {
		 ArrayList <String> CurrentWins=new ArrayList();
			CurrentWins.addAll(objPojo.getDriver().getWindowHandles());
			objPojo.getDriver().switchTo().window(CurrentWins.get(index));
			objPojo.getDriver().manage().window().maximize();
			
			return objPojo.getDriver();	 
	}
	//shreeya
	public String getAttribute(WebElement webElement, String sElementAttribute) {
		String strText = "";
		try {
			strText = webElement.getAttribute(sElementAttribute);
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (ElementNotVisibleException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
		}
		return strText;
	}


}
