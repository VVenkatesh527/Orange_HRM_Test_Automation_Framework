package com.dev.orange.hrm.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.dev.orange.hrm.Managers.FactoryManager;

public class ExtentManager  extends FactoryManager {

	
	private ExtentManager() {
		
	}
	
	protected static ThreadLocal<ExtentTest> exTest = new ThreadLocal<>();

	
	public static ExtentTest getExtentTest() {
		return exTest.get();
	}
	
	public static void setExtentTest(ExtentTest test) {
		exTest.set(test);
	}
   
}
