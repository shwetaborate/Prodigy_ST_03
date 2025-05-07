package com.saucedemo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.time.Duration;

/**
 * Utility class for setting up and managing the WebDriver instance.
 */
public class WebDriverSetup {
    private static WebDriver driver;
    
    /**
     * Initializes the Firefox WebDriver instance.
     * @return The WebDriver instance.
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            // Set up Firefox driver
            System.setProperty("webdriver.gecko.driver", "D:\\Gecko\\geckodriver.exe");
            
            // Configure Firefox options
            FirefoxOptions options = new FirefoxOptions();
            // Uncomment the following line if you want to run in headless mode
            // options.addArguments("--headless");
            
            // Initialize driver
            driver = new FirefoxDriver(options);
            
            // Configure driver settings
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        }
        return driver;
    }
    
    /**
     * Closes the WebDriver instance.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    
    /**
     * Navigates to the specified URL.
     * @param url The URL to navigate to.
     */
    public static void navigateTo(String url) {
        getDriver().get(url);
    }
}