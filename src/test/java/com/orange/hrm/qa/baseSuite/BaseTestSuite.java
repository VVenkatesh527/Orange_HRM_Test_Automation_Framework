package com.orange.hrm.qa.baseSuite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.dev.orange.hrm.Managers.DriverManager;
import com.dev.orange.hrm.listeners.ExtentReport;
import com.dev.orange.hrm.utilities.Log;

public class BaseTestSuite extends DriverManager {

	@BeforeMethod
	public void setUp() {

		openBrowser();
		Log.info("Browser launched on tthe Chrome");
		pageObjectsIntilization();
		ExtentReport.initReports();
		pageLoadTimeOut(40);
		implicitWait(20);
	}

	@AfterMethod
	public void tearDown() {

		destroyPageObjects();
		ExtentReport.tearDownReports();
		if (driver() != null) {
			driver().quit();
		}
	}
}
