package com.saucedemo.tests;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class for the Sauce Demo login functionality.
 */
public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverSetup.getDriver();
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        WebDriverSetup.quitDriver();
    }

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() {
        return new Object[][] {
            {"standard_user", "secret_sauce"},
            {"performance_glitch_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
            {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
            {"invalid_user", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
            {"standard_user", "wrong_password", "Epic sadface: Username and password do not match any user in this service"},
            {"", "", "Epic sadface: Username is required"}
        };
    }

    @Test(dataProvider = "validLoginData")
    public void testValidLogin(String username, String password) {
        // Open the login page
        loginPage.open();
        
        // Perform login
        loginPage.login(username, password);
        
        // Verify login was successful
        Assert.assertTrue(loginPage.isLoginSuccessful(), 
            "Login failed for valid credentials: " + username + " / " + password);
    }

    @Test(dataProvider = "invalidLoginData")
    public void testInvalidLogin(String username, String password, String expectedErrorMessage) {
        // Open the login page
        loginPage.open();
        
        // Perform login
        loginPage.login(username, password);
        
        // Verify login failed with the expected error message
        Assert.assertFalse(loginPage.isLoginSuccessful(), 
            "Login succeeded unexpectedly for invalid credentials: " + username + " / " + password);
        
        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage,
            "Error message did not match expected message for invalid credentials: " + username + " / " + password);
    }
    
    @Test
    public void testLoginPageElements() {
        // Open the login page
        loginPage.open();
        
        // Verify page elements are present and functioning
        loginPage.enterUsername("test_user");
        loginPage.enterPassword("test_password");
        loginPage.clickLoginButton();
        
        // Verify error message is displayed
        Assert.assertFalse(loginPage.isLoginSuccessful(), 
            "Login succeeded unexpectedly for invalid credentials");
    }
}