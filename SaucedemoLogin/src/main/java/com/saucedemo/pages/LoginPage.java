package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Model class for the Sauce Demo Login page.
 */
public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Page URL
    private static final String PAGE_URL = "https://www.saucedemo.com/";
    
    // Locators
    @FindBy(id = "user-name")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(id = "login-button")
    private WebElement loginButton;
    
    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;
    
    @FindBy(className = "inventory_list")
    private WebElement productList;
    
    /**
     * Constructor to initialize elements using PageFactory.
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Opens the login page.
     * @return The LoginPage instance for method chaining
     */
    public LoginPage open() {
        driver.get(PAGE_URL);
        return this;
    }
    
    /**
     * Sets the username value.
     * @param username The username to enter
     * @return The LoginPage instance for method chaining
     */
    public LoginPage enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
        return this;
    }
    
    /**
     * Sets the password value.
     * @param password The password to enter
     * @return The LoginPage instance for method chaining
     */
    public LoginPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }
    
    /**
     * Clicks the login button.
     * @return The LoginPage instance for method chaining
     */
    public LoginPage clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        return this;
    }
    
    /**
     * Performs the login action with the provided credentials.
     * @param username The username to enter
     * @param password The password to enter
     * @return The LoginPage instance for method chaining
     */
    public LoginPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return this;
    }
    
    /**
     * Checks if login was successful by verifying if the product list is displayed.
     * @return true if login was successful, false otherwise
     */
    public boolean isLoginSuccessful() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productList));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Gets the error message text if login failed.
     * @return The error message text
     */
    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }
}