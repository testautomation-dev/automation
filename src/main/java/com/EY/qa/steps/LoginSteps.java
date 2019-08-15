package com.EY.qa.steps;

import com.EY.qa.framework.SetupTestDriver;
import com.EY.qa.framework.WebApp;
import com.EY.qa.pages.LoginPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Created by Aruna on 08/07/19.
 */
public class LoginSteps {
    //Cucumber test step definitions for Login Module.

    //Logging object
    private static final Logger log = LogManager.getLogger(LoginSteps.class);
    private static WebDriver driver;
    private static WebApp webApp;
    private static LoginPage loginPage;
    private static SetupTestDriver setupTestDriver;


    @Given("^Browser is open$")
    public void main_page_is_loaded() throws Throwable {
        log.info("Given: Browser is open");

        webApp = WebApp.getInstance();
       // driver= setupTestDriver.getDriver();
    }

    @When("^I login into EY mail$")
    public void i_login_into_the_webpage() throws Throwable {
        log.info("When: I login into EY mail");
        loginPage = webApp.gotoLoginPage();
        loginPage.login();
    }

    @Then("^I am able to see the main page$")
    public void i_am_able_to_go_main_page() throws Throwable {
        log.info("Then: I am able to see the main page");
        loginPage.verifyMainPage();
    }

    @Then("^exit the page$")
    public void i_am_able_exit_page() throws Throwable {
        log.info("Then: exit the page");
        WebApp.stopDriver();
    }

    @Then("^Click on logout button$")
    public void i_am_able_logout_page() throws Throwable {
        log.info("Then: logout the page");
        //loginPage = webApp.gotoLoginPage();
        loginPage.logout();
    }

}
