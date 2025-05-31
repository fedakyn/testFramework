package com.example.hooks;

import com.example.base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class UiHooks extends BaseTest {

    @Before(value = "@UI", order = 0)
    public void browserSetUp() {
        // Code to initialize the WebDriver and open the browser
        logger.info("Setting up the UI test environment.");
        setUp();
    }

    @After(value = "@UI")
    public void browserTearDown() {
        // Code to close the browser and clean up resources
        logger.info("Tearing down the UI test environment.");
        tearDown();
    }
}
