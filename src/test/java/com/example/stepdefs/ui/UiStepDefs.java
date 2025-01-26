package com.example.stepdefs.ui;

import com.example.base.BaseTest;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.example.pages.SignupPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.example.helpers.Util;

public class UiStepDefs extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;
    private SignupPage signupPage;

    @Before(value = "@UI", order = 0)
    public void browserSetup() {
        setUp();
    }

    @Before(value = "@UI", order = 1)
    public void setUpPages() {
        if (driver == null) {
            throw new IllegalStateException("Driver is not initialized!");
        }
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        signupPage = new SignupPage(driver);
    }

    @After(value = "@UI")
    public void tearDownAfterScenario() {
        tearDown();
    }

    @When("I open the application")
    public void open_application() {
        driver.get("https://automationexercise.com/");
        try {
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[class='fc-button fc-cta-consent fc-primary-button']")));
            acceptCookiesButton.click();
            logger.info("Cookies popup handled successfully.");
        } catch (Exception e) {
            logger.info("Cookies popup not found or already accepted.");
        }
    }

    @And("I click on signup login")
    public void click_signup() {
        homePage.clickSignUpLoginButton();
    }

    @And("I input the signup name {word}")
    public void input_signup_name(String username) {
        loginPage.enterSignupName(username);
    }

    @And("I input the signupEmail")
    public void input_signup_email() {
        String email = Util.generateRandomString(10);
        loginPage.enterSignupEmail(email + "@test.net");
    }

    @And("I press signup")
    public void press_signup() {
        loginPage.clickSignupButton();
    }

    @And("I complete all the fields for registering")
    public void complete_all_fields() {
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
    }

    @Then("I check that account created message is displayed")
    public void check_account_created() {
        WebElement accountCreated = signupPage.getCreatedMessage();
        Assert.assertTrue("The account created message is not displayed", accountCreated.isDisplayed());
    }

    @Then("I close the browser")
    public void close_browser() {
        tearDown();
    }
}
