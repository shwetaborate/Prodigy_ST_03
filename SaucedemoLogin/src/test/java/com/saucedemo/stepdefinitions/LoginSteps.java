package com.saucedemo.stepdefinitions;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.WebDriverSetup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Step definitions for Cucumber BDD tests related to login functionality.
 */
public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    
    @Before
    public void setUp() {
        driver = WebDriverSetup.getDriver();
        loginPage = new LoginPage(driver);
    }
    
    @After
    public void tearDown() {
        WebDriverSetup.quitDriver();
    }
    
    @Given("the user is on the login page")
    public void userIsOnLoginPage() {
        loginPage.open();
    }
    
    @When("the user enters username {string}")
    public void userEntersUsername(String username) {
        loginPage.enterUsername(username);
    }
    
    @When("the user enters password {string}")
    public void userEntersPassword(String password) {
        loginPage.enterPassword(password);
    }
    
    @When("the user clicks the login button")
    public void userClicksLoginButton() {
        loginPage.clickLoginButton();
    }
    
    @When("the user logs in with username {string} and password {string}")
    public void userLogsInWithCredentials(String username, String password) {
        loginPage.login(username, password);
    }
    
    @Then("the user should be logged in successfully")
    public void userShouldBeLoggedInSuccessfully() {
        Assert.assertTrue(loginPage.isLoginSuccessful(), 
            "Login failed when it was expected to succeed");
    }
    
    @Then("the user should see an error message containing {string}")
    public void userShouldSeeErrorMessage(String errorMessageText) {
        Assert.assertFalse(loginPage.isLoginSuccessful(), 
            "Login succeeded when it was expected to fail");
        Assert.assertTrue(loginPage.getErrorMessage().contains(errorMessageText),
            "Expected error message not found. Actual: " + loginPage.getErrorMessage());
    }
}