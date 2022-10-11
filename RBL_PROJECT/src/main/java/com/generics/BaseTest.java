package com.generics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.Scenario;
import cucumber.api.java.After;

public class BaseTest extends Pojo {
	private Properties objConfig;

	public Pojo initializeWebEnvironment() {
		if (this.getDriver() == null) {
			this.loadConfigProperties();
			this.setDriver(initializeWebEnvironment(this.objConfig));
			this.setAfterClickwait(Integer.parseInt(this.objConfig.getProperty("AfterClickWait")));
			this.setScriptTimeoutWait(Integer.parseInt(this.objConfig.getProperty("ScriptTimeoutWait")));
			this.setWebDriverWait(new WebDriverWait(this.getDriver(),
					(long) Integer.parseInt(this.objConfig.getProperty("driver.WebDriverWait").trim())));
			this.setObjUtilities(new Utilities(this));
			this.setObjWrapperFunctions(new WrapperFunctions(this));
			// this.createLo4jLogerDirectory();
		}
		return this;
	}

	private void loadConfigProperties() {
		try {
			this.objConfig = new Properties();
			this.objConfig.load(
					new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties"));
			this.setObjConfig(this.objConfig);
		} catch (Exception var2) {
			var2.printStackTrace();
		}
	}

	public void tearDownWebEnvironment(Scenario scenario) {
		try {
			if (scenario.isFailed()) {
				final byte[] screenshot = ((TakesScreenshot) this.getDriver()).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			}
			CustomReporterHelper.getException().add(new CustomReporterHelper(scenario, this));
			this.getDriver().quit();
			setObjUtilities(null);
			this.getObjWrapperFunctions().waitFor(2);
			System.out.println("Kill Chrome Browser.!!!!!!");

		} catch (Exception var4) {
			try {
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			} catch (IOException var3) {
				var3.printStackTrace();
			}
			var4.printStackTrace();
			if (System.getProperty("web.browser").trim().equalsIgnoreCase("IE")
					|| System.getProperty("web.browser").trim().equalsIgnoreCase("Chrome")) {
				this.killBrowserAndDriver(this.objConfig);
			}
		}
		this.setObjWrapperFunctions(null);
	}

	@After
	private void killBrowserAndDriver(Properties objConfig) {
		this.getDriver().quit();
		// String browser = System.getProperty("web.browser").trim();
		// String browserProcess = "";
		// String driverProcess = "";
		// if (!browser.equals("") && browser.equalsIgnoreCase("IE")) {
		// browserProcess = "iexplore";
		// driverProcess = "IEDriverServer.exe";
		// } else if (!browser.equals("") && browser.equalsIgnoreCase("Chrome")) {
		// browserProcess = browser;
		// driverProcess = "chromedriver.exe";
		// }
		//
		// try {
		// Process procDriver = Runtime.getRuntime().exec("taskkill /F /T /IM " +
		// driverProcess);
		// Process procIE = Runtime.getRuntime().exec("taskkill /F /T /IM " +
		// browserProcess + ".exe");
		// procDriver.waitFor();
		// procIE.waitFor();
		// } catch (Exception var7) {
		// var7.printStackTrace();
		// }

	}

	private void createLo4jLogerDirectory() {
		String logFilePath = reportPath + "/target/custom-reports/log4j-Reports/log4j-Logger.log";
		File reportFile = new File(logFilePath);
		try {
			if (!reportFile.exists()) {
				(new File(reportPath + "/target/custom-reports/log4j-Reports")).mkdir();
				reportFile.createNewFile();
			}
		} catch (IOException var4) {
			var4.printStackTrace();
		}
	}

	// private boolean cleanDownloadsDirectory() {
	// try {
	// String downloadFilesLocation = System.getProperty("user.dir")
	// + this.objConfig.getProperty("downloads.path").trim();
	// File directory = new File(downloadFilesLocation);
	// FileUtils.cleanDirectory(directory);
	// return true;
	// } catch (Exception var3) {
	// var3.printStackTrace();
	// return false;
	// }
	// }

	// synchronized private void loadDataProvider() {
	// try {
	// String testDataPath = objConfig.getProperty("testdata.path");
	// if (!testDataPath.contains(":"))
	// testDataPath = System.getProperty("user.dir") + testDataPath;
	// testDataPath = testDataPath + objConfig.getProperty("testdata.fileName") +
	// ".xlsx";
	// excelFileIS = new FileInputStream(testDataPath);
	// setWorkbook(new XSSFWorkbook(excelFileIS));
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	private WebDriver initializeWebEnvironment(Properties objConfig) {
		WebDriver webDriver = null;

		try {
			String browser = objConfig.getProperty("web.browser").trim().toLowerCase();
			String Headless = objConfig.getProperty("Api.browser").trim().toLowerCase();
			if (browser.equalsIgnoreCase("chrome")) {
				ChromeOptions objChromeOptions = new ChromeOptions();
				if (Headless.equalsIgnoreCase("true")) {
					System.setProperty("webdriver.chrome.driver",
							objConfig.getProperty("webdriver.chrome.driver").trim());
					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.addArguments("--headless");
					chromeOptions.addArguments("--window-size=1920,1200");
					webDriver = new ChromeDriver(chromeOptions);
				}
				if (objConfig.getProperty("chromeIncognito").equalsIgnoreCase("true"))
					objChromeOptions.addArguments("incognito");
				if (objConfig.getProperty("webDriver").equalsIgnoreCase("true")) {
					String WebDriverPath = objConfig.getProperty("webdriver.chrome.driver").trim().toLowerCase();
					System.setProperty("webdriver.chrome.driver", WebDriverPath);
					webDriver = new ChromeDriver(objChromeOptions);

				}
				objChromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
				Map<String, Object> prefs = new HashMap<String, Object>();
				String downloadPath = System.getProperty("user.dir") + File.separator + "target" + File.separator
						+ "downloadFiles";
				this.setDownloadPath(downloadPath + "\\");
				prefs.put("download.default_directory", downloadPath);
				prefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
				objChromeOptions.setExperimentalOption("prefs", prefs);
			} else if (browser.equalsIgnoreCase("firefox")) {

				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + objConfig.getProperty("webdriver.firefox.driver").trim());
				webDriver = new FirefoxDriver();

			} else if (browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", objConfig.getProperty("webdriver.ie.driver").trim());
				webDriver = new InternetExplorerDriver();
			}
			webDriver.manage().window().maximize();
			webDriver.manage().timeouts().implicitlyWait(
					(long) Integer.parseInt(objConfig.getProperty("driver.implicitlyWait").trim()), TimeUnit.SECONDS);
			webDriver.manage().timeouts().pageLoadTimeout(
					(long) Integer.parseInt(objConfig.getProperty("driver.pageLoadTimeout").trim()), TimeUnit.SECONDS);
			return webDriver;
		} catch (Exception var4) {
			var4.printStackTrace();
			return null;
		}
	}

}