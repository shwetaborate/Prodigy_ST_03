package com.saucedemo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSetup {

    private static WebDriver driver;

    public static WebDriver initializeDriver() {
        // Set the path to the GeckoDriver executable
        System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");

        // Initialize the FirefoxDriver
        driver = new FirefoxDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
