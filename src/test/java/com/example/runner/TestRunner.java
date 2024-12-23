package com.example.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/Features",
        glue = "com.example.steps",
        plugin = {"pretty", "html:target/cucumber-report.html"},
        monochrome = true,
        tags = "@vali"
)

public class TestRunner {
}
