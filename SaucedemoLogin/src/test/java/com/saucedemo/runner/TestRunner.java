package com.saucedemo.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.saucedemo.stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@positive or @negative or @edge"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
