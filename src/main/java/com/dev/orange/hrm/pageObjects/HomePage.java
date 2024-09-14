package com.dev.orange.hrm.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage {
	
private static By pimLocator = By.xpath("//span[text()='PIM']");
	
	public HomePage(WebDriver driver) {
    	PageFactory.initElements(driver, this);
	}
	public WebElement getPimLocator() {
		threadWait();
		return driver().findElement(pimLocator);
	}
}
