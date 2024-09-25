package com.orange.hrm.qa.pagesTest;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.dev.orange.hrm.utilities.Log;
import com.orange.hrm.qa.baseSuite.BaseTestSuite;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import static com.dev.orange.hrm.pageObjects.LoginPage.loginOrangehrmApplication;

public class EmployeePageTest extends BaseTestSuite {

	
	@Description("Validate tab Configuration List, Employee List, Add Employee & Reports are displayed in PIM Page")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void tc_hrm_employees_002() {

		loginOrangehrmApplication("username","password");
		String getTitle = driver().getTitle();
		Assert.assertEquals(getTitle, getInputProperty("homePageTitle"));
		Log.info("Home Page Title after login successfully--->" + getTitle);
		homePage.getPimLocator().click();
		Log.info("clicked on PIM ---> " + homePage.getPimLocator().getText());
		List<String> listOfFeature = pimPage.getFeatureListOfPIM();
		ArrayList<String> expectedList = new ArrayList<>();
		expectedList.add("Configuration");
		expectedList.add("Employee List");
		expectedList.add("Add Employee");
		expectedList.add("Reports");
		Assert.assertEquals(listOfFeature, expectedList);
		Log.info("List of WebElement on PIM Page is Validated");

	}
}
