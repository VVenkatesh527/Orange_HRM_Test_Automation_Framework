package com.dev.orange.hrm.stepDefination;

import com.dev.orange.hrm.Managers.FactoryManager;
import com.dev.orange.hrm.utilities.Log;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.dev.orange.hrm.Managers.FactoryManager.*;

public class Stepdefination  extends FactoryManager  {

    public WebDriver driver;


    @Given("User navigate to orange HRM page {string}")
    public void NavigateToOrangeHRMPage(String string) {

       driver = createInstance(getInputProperty("browser"));
       openBrowser();
       Log.info("Browser launched on the Chrome");
       pageObjectsIntilization();
       driver.get(string);
    }

    @Then("verify and validate login page Title as {string} as Expected")
    public void verifyAndValidateLoginPageTitleAsExpected(String expected) {

       String actual=driver.getTitle();
        Assert.assertEquals(actual,expected);
    }


}
