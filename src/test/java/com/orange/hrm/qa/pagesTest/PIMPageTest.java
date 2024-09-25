package com.orange.hrm.qa.pagesTest;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.dev.orange.hrm.utilities.Log;
import com.orange.hrm.qa.baseSuite.BaseTestSuite;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import static com.dev.orange.hrm.pageObjects.LoginPage.loginOrangehrmApplication;

public class PIMPageTest extends BaseTestSuite {

	

	@Description("Validate User is Navigated to Add Employee Tab After Clicking on Add Button From Employee List Tab")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void tc_hrm_employees_003() {

		loginOrangehrmApplication(" "," ");
		String getTitle = driver().getTitle();
		Assert.assertEquals(getTitle, getInputProperty("homePageTitle"));
		Log.info("Logged in Successfully");
		homePage.getPimLocator().click();
		Log.info("clicked on PIM feature");
		pimPage.getAddEmployeeLocator().click();
		Log.info("clicked on Add Employee button");
		String actualResult = pimPage.getAddEmployeeTitleLocator().getText();
		Assert.assertEquals(actualResult, getInputProperty("addEmployee"));

	}

}
