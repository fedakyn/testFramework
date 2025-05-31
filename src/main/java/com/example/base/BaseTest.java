package com.example.base;

import com.example.helpers.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BaseTest{
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    public static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    public void setUp(){
        String chromeDriverPath = ConfigLoader.getProperty("chromedriver.path");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}