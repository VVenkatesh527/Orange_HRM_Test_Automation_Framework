package com.dev.orange.hrm.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.dev.orange.hrm.Managers.DriverManager;
import com.dev.orange.hrm.utilities.Log;

public class LoginPage extends DriverManager {

	private static By titleLocator = By.tagName("title");
	private static By userNameLocator = By.xpath("//input[@name='username']");
	private static By passwordLocator = By.xpath("//input[@name='password']");
	private static By loginBtnLocator = By.xpath("//button[@type='submit']");

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public static WebElement getUserNameLocator() {
		return driver().findElement(userNameLocator);
	}

	public static WebElement getPasswordLocator() {
		return driver().findElement(passwordLocator);
	}

	public static WebElement getLoginBtnLocator() {
		return driver().findElement(loginBtnLocator);
	}

	public static WebElement gettitleLocator() {
		return driver().findElement(titleLocator);
	}

	public WebDriver loginOrangehrmApplication() {
		String username = "";
		String password = "";
		threadWait();
		if (username.isBlank() || username.contains(null)) {
			username = getInputProperty("username");
			getUserNameLocator().sendKeys(username);
			Log.info("Entered Username is --->" + username);
		}
		if (password.isBlank() || password.contains(null)) {
			password = getInputProperty("password");
			getPasswordLocator().sendKeys(password);
			Log.info("Entered password is --->" + password);
		}

		getLoginBtnLocator().click();
		Log.info("clicked on Login Button");
		return driver();
	}
}
