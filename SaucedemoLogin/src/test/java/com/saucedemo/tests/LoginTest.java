package com.saucedemo.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.WebDriverSetup;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = WebDriverSetup.getDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @AfterClass
    public void tearDown() {
        WebDriverSetup.quitDriver();
    }

    @DataProvider(name = "validCredentials")
    public Object[][] validCredentials() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Sorry, this user has been locked out."},
                {"invalid_user", "secret_sauce", "Username and password do not match any user in this service."},
                {"standard_user", "wrong_password", "Username and password do not match any user in this service."},
                {"", "secret_sauce", "Username is required."},
                {"standard_user", "", "Password is required."},
                {"", "", "Username is required."}
        };
    }

    @DataProvider(name = "edgeCases")
    public Object[][] edgeCases() {
        return new Object[][]{
                {"a".repeat(100), "secret_sauce", "input length or invalid credentials"},
                {"standard_user", "a".repeat(100), "input length or invalid credentials"},
                {"user!@#$%^&*()", "secret_sauce", "invalid characters"},
                {"   ", "   ", "Username is required."},
                {"STANDARD_USER", "SECRET_SAUCE", "Username and password do not match any user in this service."}
        };
    }

    @Test(dataProvider = "validCredentials")
    public void testValidLogin(String username, String password) {
        Reporter.log("Testing valid login with username: " + username, true);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Login failed!");
        Reporter.log("Valid login test passed for username: " + username, true);
    }

    @Test(dataProvider = "invalidCredentials")
    public void testInvalidLogin(String username, String password, String expectedErrorMessage) {
        Reporter.log("Testing invalid login with username: " + username, true);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message does not match!");
        Reporter.log("Invalid login test passed for username: " + username, true);
    }

    @Test(dataProvider = "edgeCases")
    public void testEdgeCases(String username, String password, String expectedErrorMessage) {
        Reporter.log("Testing edge case login with username: " + username, true);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage), "Error message does not match for edge case!");
        Reporter.log("Edge case test passed for username: " + username, true);
    }
}
