package com.dev.orange.hrm.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.dev.orange.hrm.Managers.DriverManager;
import com.dev.orange.hrm.utilities.Log;

public class PIMPage extends DriverManager {

	public static By addEmployeeLocator = By.xpath("//a[contains(text(),'Add Employee')]");
	public static By saveLocator = By.xpath("//button[contains(@type,'submit')]");
	public static By employeeListLocator = By.xpath("//a[contains(text(),'Employee List')]");
	public static By employeeNameSearchLocator = By.xpath(
			"//label[contains(text(),'Employee Name')]//parent::div//following-sibling::div//input[contains(@placeholder,'Type for')]");
	public static By employeeIdSearchLocator = By.xpath(
			"//label[contains(text(),'Employee Id')]//parent::div//following-sibling::div//input[contains(@class,'oxd-input oxd-input--active')]");
	public static By searchBtnLocator = By.xpath("//button[normalize-space()='Search']");
	public static By emplyeeIdLocator = By.xpath(
			"//label[contains(text(),'Employee Id')]//parent::div//following-sibling::div//input[contains(@class,'oxd-input')]");
	public static By recordFoundLocator = By.xpath("//span[normalize-space()='(1) Record Found']");
	public static By listOfPIMFeatureLocator = By
			.xpath("//nav[contains(@aria-label,'Topbar Menu')]//ul//li//a[contains(text(),'')]");
	public static By configurationPIMFeatureLocator = By.xpath("//li//span[normalize-space()='Configuration']");
	public static By addEmployeeTitleLocator = By.xpath("//div//h6[text()='Add Employee']");
	public static By configurationListLocator = By.xpath("//a[@role='menuitem']");
	public static By employeeInfoLocator = By.xpath("//h5[normalize-space()='Employee Information']");
	public static By configurationLocator = By.xpath("//span[contains(text(),'Configuration')]");
	public static By tableHeaderListLocator = By
			.xpath("//div[@class='oxd-table-header-cell oxd-padding-cell oxd-table-th']");

	public PIMPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getAddEmployeeLocator() {
		threadWait();
		return driver().findElement(addEmployeeLocator);
	}

	public static WebElement getsaveLocator() {
		threadWait();
		return driver().findElement(saveLocator);
	}

	public static WebElement getemployeeListLocator() {
		threadWait();
		return driver().findElement(employeeListLocator);
	}

	public static WebElement getemployeeNameSearchLocator() {
		threadWait();
		return driver().findElement(employeeNameSearchLocator);
	}

	public static WebElement getemployeeIdSearchLocator() {
		threadWait();
		return driver().findElement(employeeIdSearchLocator);
	}

	public static WebElement getsearchBtnLocator() {
		threadWait();
		return driver().findElement(searchBtnLocator);
	}

	public static WebElement getemplyeeIdLocator() {
		threadWait();
		return driver().findElement(emplyeeIdLocator);
	}

	public static WebElement getrecordFoundLocator() {
		threadWait();
		return driver().findElement(recordFoundLocator);
	}

	public static List<WebElement> getlistOfPIMFeatureLocator() {
		threadWait();
		return driver().findElements(listOfPIMFeatureLocator);
	}

	public WebElement getWebElementUsingString(String input) {
		threadWait();
		WebElement element = null;
		try {
			element = driver().findElement(By.name("" + input + "Name"));

		} catch (Exception e) {
			Log.info("WebElement not Found");
			e.printStackTrace();
		}
		return element;
	}

	public WebElement getAddEmployeeTitleLocator() {
		threadWait();
		return driver().findElement(addEmployeeTitleLocator);
	}

	public List<WebElement> getconfigurationListLocator() {
		threadWait();
		return driver().findElements(configurationListLocator);
	}

	public WebElement getemployeeInfoLocator() {
		threadWait();
		return driver().findElement(employeeInfoLocator);
	}

	public WebElement getconfigurationLocator() {
		threadWait();
		return driver().findElement(configurationLocator);
	}

	public List<WebElement> gettableHeaderListLocator() {
		threadWait();
		return driver().findElements(tableHeaderListLocator);
	}

	public List<String> getFeatureListOfPIM() {

		ArrayList<String> elementList = new ArrayList<>();
		threadWait();
		String first = driver().findElement(configurationPIMFeatureLocator).getText();
		elementList.add(first);
		int j = getlistOfPIMFeatureLocator().size();
		for (int i = 0; i < j; i++) {
			elementList.add(getlistOfPIMFeatureLocator().get(i).getText());
		}
		return elementList;

	}

}
