package com.saucedemo.stepdefinitions;

import org.openqa.selenium.WebDriver;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.WebDriverSetup;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class LoginSteps {
    WebDriver driver = WebDriverSetup.getDriver();
    LoginPage loginPage = new LoginPage(driver);

    @Given("User is on SauceDemo login page")
    public void user_is_on_login_page() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("User enters username {string}")
    public void user_enters_username(String username) {
        loginPage.enterUsername(username);
    }

    @And("User enters password {string}")
    public void user_enters_password(String password) {
        loginPage.enterPassword(password);
    }

    @And("User clicks on the login button")
    public void clicks_login_button() {
        loginPage.clickLogin();
    }

    @Then("User should be redirected to the home page")
    public void user_should_be_redirected_to_the_home_page() {
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "User was not redirected to the home page.");
    }

    @Then("User should see an error message {string}")
    public void user_should_see_error_message(String expectedError) {
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals(actualError, expectedError, "Error message is not as expected.");
    }

    @Then("The username field should be pre-populated with {string}")
    public void username_field_should_be_prepopulated(String expectedUsername) {
        // Assuming there’s a method to get the username field value in LoginPage
        String actualUsername = loginPage.getUsernameFieldValue();
        Assert.assertEquals(actualUsername, expectedUsername, "Username field is not pre-populated correctly.");
    }
}
