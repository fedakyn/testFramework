package com.example.base;

import com.example.helpers.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest{
    protected WebDriver driver;
    protected WebDriverWait wait;

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