package com.mercari.tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.mercari.tests",
        plugin = {"pretty", "html:reports/cucumber-report.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
