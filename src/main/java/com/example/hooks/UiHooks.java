package com.example.hooks;

import com.example.base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class UiHooks extends BaseTest {

    @Before(value = "@UI", order = 0)
    public void browserSetUp() {
        logger.info("Setting up the UI test environment.");
        setUp();
    }

    @After(value = "@UI")
    public void browserTearDown() {
        logger.info("Tearing down the UI test environment.");
        tearDown();
    }
}
