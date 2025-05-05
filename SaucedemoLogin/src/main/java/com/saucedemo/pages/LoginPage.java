package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    // WebDriver instance
    private WebDriver driver;

    // Locators for login page elements
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector(".error-message-container");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Enter username
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    // Enter password
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    // Click the login button
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    // Get error message text
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    // Perform login action
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}
