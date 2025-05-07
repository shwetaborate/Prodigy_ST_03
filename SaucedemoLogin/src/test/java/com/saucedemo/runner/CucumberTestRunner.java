package com.saucedemo.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * TestNG runner class for Cucumber tests.
 */
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.saucedemo.stepdefinitions"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber-pretty.html",
        "json:target/cucumber-reports/CucumberTestReport.json"
    },
    monochrome = true
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
    // This class is used to run Cucumber tests with TestNG
}