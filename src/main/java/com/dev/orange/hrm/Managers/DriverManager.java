package com.dev.orange.hrm.Managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dev.orange.hrm.pageObjects.BasePage;
import com.dev.orange.hrm.pageObjects.HomePage;
import com.dev.orange.hrm.pageObjects.LoginPage;
import com.dev.orange.hrm.pageObjects.PIMPage;
import com.dev.orange.hrm.utilities.Log;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

	public static FileInputStream inputStream = null;
	public static Properties prop = null;
	public static WebDriverWait wait = null;
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	private static final String defaultPropertiesFile = System.getProperty("user.dir") + "//src//main//resources//";
	protected static String reportPath = System.getProperty("user.dir") + "//Reports//AutomationExtentReport.html";
	protected static LoginPage loginPage = null;
	protected static BasePage basePage = null;
	protected static HomePage homePage = null;
	protected static PIMPage pimPage = null;

	protected static WebDriver driver() {

		return driver.get();
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
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().clearDriverCache().setup();
			driver.set(new ChromeDriver(options));
			break;

		case "edge":
			WebDriverManager.edgedriver().clearDriverCache().setup();
			driver.set(new EdgeDriver());
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
