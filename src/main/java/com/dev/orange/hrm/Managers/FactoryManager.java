package com.dev.orange.hrm.Managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dev.orange.hrm.pageObjects.BasePage;
import com.dev.orange.hrm.pageObjects.HomePage;
import com.dev.orange.hrm.pageObjects.LoginPage;
import com.dev.orange.hrm.pageObjects.PIMPage;
import com.dev.orange.hrm.utilities.Log;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FactoryManager {

	public static FileInputStream inputStream = null;
	public static Properties prop = null;
	public static WebDriverWait wait = null;
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();


	private static EdgeOptions edgeOptions;
	private static FirefoxOptions firefoxOptions;
	private static ChromeOptions chromeOptions;


    private static final String defaultPropertiesFile = System.getProperty("user.dir") + "//src//main//resources//";
	protected static String reportPath = System.getProperty("user.dir") + "//Reports//AutomationExtentReport.html";
	protected static LoginPage loginPage = null;
	protected static BasePage basePage = null;
	protected static HomePage homePage = null;
	protected static PIMPage pimPage = null;

	protected static WebDriver driver() {

		return driver.get();
	}

	public static ChromeOptions getChromeOptions() {

        chromeOptions = new ChromeOptions();
		chromeOptions.setCapability("browserVersion", "121.0.6167.85");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		if (Boolean.parseBoolean(getInputProperty("remote"))) {
			chromeOptions.setCapability("browserName", "chrome");
			chromeOptions.setBrowserVersion(getInputProperty("browserversion").trim());

			Map<String, Object> selenoidOptions = new HashMap<String, Object>();
			selenoidOptions.put("screenResolution", "1280x1024x24");
			selenoidOptions.put("enableVNC", true);
			selenoidOptions.put("name", getInputProperty("testname"));
			chromeOptions.setCapability("selenoid:options", selenoidOptions);

		}

		chromeOptions.addArguments("--remote-allow-origins=*");
		chromeOptions.addArguments("--headless");

		return chromeOptions;
	}

	public static FirefoxOptions getFirefoxOptions() {

       firefoxOptions = new FirefoxOptions();
		if (Boolean.parseBoolean(getInputProperty("headless").trim()))
			firefoxOptions.addArguments("--headless");
		if (Boolean.parseBoolean(getInputProperty("incognito").trim()))
			firefoxOptions.addArguments("--incognito");

		if (Boolean.parseBoolean(getInputProperty("remote"))) {
			firefoxOptions.setCapability("browserName", "firefox");
			firefoxOptions.setBrowserVersion(getInputProperty("browserversion").trim());

			Map<String, Object> selenoidOptions = new HashMap<String, Object>();
			selenoidOptions.put("screenResolution", "1280x1024x24");
			selenoidOptions.put("enableVNC", true);
			selenoidOptions.put("name", getInputProperty("testname"));
			firefoxOptions.setCapability("selenoid:options", selenoidOptions);
		}
		return firefoxOptions;
	}

	private static EdgeOptions getEdgeOptions() {
        edgeOptions = new EdgeOptions();
		return edgeOptions;
	}

	public static void pageObjectsIntilization() {

		loginPage = new LoginPage(driver());
		basePage = new BasePage();
		homePage = new HomePage(driver());
		pimPage = new PIMPage(driver());
	}

	public static void destroyPageObjects() {

		loginPage = null;
		homePage = null;
		basePage = null;
		pimPage = null;
	}

	public static WebDriver createInstance(String browser) {

		switch (browser.toLowerCase().trim()) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver(getChromeOptions()));
			break;
		case "firefox":
			WebDriverManager.chromedriver().setup();
			driver.set(new FirefoxDriver(getFirefoxOptions()));
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver(getEdgeOptions()));
			break;

		default:
			break;

		}
		driver().manage().deleteAllCookies();
		driver().manage().window().maximize();
		driver().get(getInputProperty("url"));

		return driver();
	}

	public static void openBrowser() {

		if (System.getProperty("browser") == "gridurl") {
			// grid url
		} else
			createInstance(getInputProperty("browser"));

	}

	private static Properties readPropertyFile() {

		File file = new File(defaultPropertiesFile + "orange.properties");
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.error("File Not Found at Path");
		}
		Properties prop = new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static String getInputProperty(String input) {
		Properties prop = readPropertyFile();
		String property = prop.getProperty(input);
		return property;
	}

	public static void implicitWait(int timeout) {

		driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
	}

	public static void visibilityOfElement(WebElement element, long Seconds) {

		wait = new WebDriverWait(driver(), Duration.ofSeconds(Seconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void pageLoadTimeOut(int timeout) {

		driver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout));
	}

	public static void threadWait() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
