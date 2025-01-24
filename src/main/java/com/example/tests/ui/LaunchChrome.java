package com.example.tests.ui;

import com.example.base.BaseTest;
import com.example.helpers.Util;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.example.pages.SignupPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class LaunchChrome extends BaseTest {

    public static void main(String[] args) {
        LaunchChrome test = new LaunchChrome();
        test.runTest();
    }

    @Test
    public void runTest() {
        String email = Util.generateRandomString(10);
        setUp();
        driver.get("https://automationexercise.com/");
        try {
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[class='fc-button fc-cta-consent fc-primary-button']")));
            acceptCookiesButton.click();
            System.out.println("Cookies popup handled successfully.");
        } catch (Exception e) {
            System.out.println("Cookies popup not found or already accepted.");
        }

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SignupPage signupPage = new SignupPage(driver);

        homePage.clickSignUpLoginButton();
        loginPage.enterSignupName("testUser");
        loginPage.enterSignupEmail(email + "@test.net");
        loginPage.clickSignupButton();
        signupPage.selectGender("id_gender1");
        signupPage.enterName("testUsername");
        signupPage.enterPassword("testPassword");
        signupPage.selectDateOfBirth("16", "8", "1997");
        signupPage.signUpNewsletter();
        signupPage.receiveOffers();
        signupPage.enterFirstName("John");
        signupPage.enterLastName("Doe");
        signupPage.enterCompany("Mirceasoft");
        signupPage.enterAddress1("First address");
        signupPage.enterAddress2("Second address");
        signupPage.selectCountry("Canada");
        signupPage.enterState("Ontario");
        signupPage.enterCity("Toronto");
        signupPage.enterZipCode("123456");
        signupPage.enterMobile("0293450123");
        signupPage.createAccount();
        signupPage.continueAfterCreatingAccount();
        homePage.clickSignUpLoginButton();
        loginPage.enterLogin(email + "@test.net");
        loginPage.enterPassword("testPassword");
        loginPage.pressLogin();
//        tearDown();
    }
}
