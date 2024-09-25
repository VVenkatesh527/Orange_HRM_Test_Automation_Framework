package com.dev.orange.hrm.stepDefination;

import com.dev.orange.hrm.Managers.FactoryManager;
import com.dev.orange.hrm.pageObjects.LoginPage;
import com.dev.orange.hrm.utilities.Log;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class LoginPageStep  extends FactoryManager {

    public WebDriver driver;

    @Given("User should navigate to orange HRM url {string}")
    public void userShouldNavigateToOrangeHRMUrl(String string) {

        driver = createInstance(getInputProperty("browser"));
        openBrowser();
        Log.info("Browser launched on the Chrome");
        pageObjectsIntilization();
        driver.get(string);
    }


    @When("user enter credentials with {string} and {string} hit on Login button")
    public void userEnterCredentialsWithAnd(String username, String password) {

        LoginPage.loginOrangehrmApplication(username,password);

    }

    @Then("User should be on Home page and Title should be {string}")
    public void userShouldBeOnHomePageAndTitleShouldBe(String Expected) {

        String Actual = LoginPage.gettitleLocator().getText();
        Assert.assertEquals(Actual,Expected);
    }

}
