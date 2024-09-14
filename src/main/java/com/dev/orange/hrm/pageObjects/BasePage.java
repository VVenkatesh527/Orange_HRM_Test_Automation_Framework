package com.dev.orange.hrm.pageObjects;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.dev.orange.hrm.Managers.DriverManager;
import com.dev.orange.hrm.utilities.Log;

public class BasePage extends DriverManager{
	
	
	public static String getWebElementText(WebElement element) {

		String value = null;
		if (!element.getAttribute("InnerText").isBlank() || !element.getAttribute("InnerText").isEmpty()) {
			value = element.getAttribute("InnerText");
		} else if (!element.getAttribute("textContent").isBlank() || element.getAttribute("textContent").isEmpty()) {
			value = element.getAttribute("textContent");
		} else if (!element.getAttribute("text").isBlank() || element.getAttribute("text").isEmpty()) {
			value = element.getAttribute("text");
		} else if (!element.getAttribute("Value").isBlank() || element.getAttribute("Value").isEmpty()) {
			value = element.getAttribute("Value");
		} else {
			value = element.getText();
		}
		return value;
	}
	
	public static List<String> getWebElementText(List<WebElement> element) {

		List<String> listofvalue = new ArrayList<>();

		if (element.size() > 1) {
			for (int i = 0; i < element.size(); i++) {
				String addlist = element.get(i).getText();
				if (addlist.isBlank()) {
					element.get(i).getText();
				} else {
					listofvalue.add(addlist);
				}
			}
			Log.info("WebElements added in the List");
		} else {
			Log.warn("List of WebElement not available");
		}
		return listofvalue;
	}
	
	public static Timestamp getDateTimeStamp() {
		
		Timestamp currentDateandTime = Timestamp.from(Instant.now());
		return currentDateandTime;
	}


}
