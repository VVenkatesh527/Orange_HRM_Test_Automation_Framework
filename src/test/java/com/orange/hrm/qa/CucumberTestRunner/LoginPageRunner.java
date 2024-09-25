package com.orange.hrm.qa.CucumberTestRunner;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports/cucumber.html","json:target/cucumber-reports/cucumber.html"

},
        features = {"src/main/resources/FeatureFile/LoginPage.feature"},
        glue = {"com.dev.orange.hrm.stepDefination.LoginPageStep"})

public class LoginPageRunner {

}
