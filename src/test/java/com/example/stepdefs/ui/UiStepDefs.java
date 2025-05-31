package com.example.stepdefs.ui;

import com.example.base.BaseTest;
import com.example.models.RegistrationField;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.example.pages.SignupPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.example.helpers.Util;

import java.util.HashMap;
import java.util.Map;

public class UiStepDefs extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;
    private SignupPage signupPage;

    @When("I open the application")
    public void open_application() {
        if (homePage == null) {
            homePage = new HomePage(driver);
            loginPage = new LoginPage(driver);
            signupPage = new SignupPage(driver);
        }
        driver.get("https://automationexercise.com/");
        try {
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[class='fc-button fc-cta-consent fc-primary-button']")));
            acceptCookiesButton.click();
            logger.info("Cookies popup handled successfully.");
        } catch (Exception e) {
            logger.info("Cookies popup not found or already accepted.");
        }
    }

    @And("I navigate to the Signup and Login Page")
    public void click_signup() {
        homePage.clickSignUpLoginButton();
    }

    @And("I sign up as {word}")
    public void sign_up(String username) {
        loginPage.enterSignupName(username);
        String email = Util.generateRandomString(10);
        loginPage.enterSignupEmail(email + "@test.net");
        loginPage.clickSignupButton();
        Assert.assertTrue("Enter Account Information label is displayed", signupPage.getAccountInfoLabel().isDisplayed());
    }

    @And("I log in")
    public void log_in() {
        loginPage.enterLogin("eOnlKzXMTD@test.net");
        loginPage.enterPassword("testPassword");
        loginPage.pressLogin();
        Assert.assertTrue("Delete Account button is displayed", homePage.getDeleteAccountButton().isDisplayed() && homePage.getDeleteAccountButton().isEnabled());
        Assert.assertTrue("Logout button is displayed", homePage.getLogoutButton().isDisplayed() && homePage.getLogoutButton().isEnabled());
    }


    @And("I complete the registration form")
    public void complete_all_fields() {
        Map<RegistrationField, String> data = new HashMap<>();
        data.put(RegistrationField.GENDER, "id_gender1");
        data.put(RegistrationField.NAME, "testUsername");
        data.put(RegistrationField.PASSWORD, "testPassword");
        data.put(RegistrationField.DAY, "16");
        data.put(RegistrationField.MONTH, "8");
        data.put(RegistrationField.YEAR, "1997");
        data.put(RegistrationField.NEWSLETTER, "true");
        data.put(RegistrationField.OFFERS, "true");
        data.put(RegistrationField.FIRST_NAME, "John");
        data.put(RegistrationField.LAST_NAME, "Doe");
        data.put(RegistrationField.COMPANY, "Mirceasoft");
        data.put(RegistrationField.ADDRESS1, "First address");
        data.put(RegistrationField.ADDRESS2, "Second address");
        data.put(RegistrationField.COUNTRY, "Canada");
        data.put(RegistrationField.STATE, "Ontario");
        data.put(RegistrationField.CITY, "Toronto");
        data.put(RegistrationField.ZIP_CODE, "123456");
        data.put(RegistrationField.MOBILE, "0293450123");
        signupPage.registerUser(data);
    }

    @Then("I check that account created message is displayed")
    public void check_account_created() {
        WebElement accountCreated = signupPage.getCreatedMessage();
        Assert.assertTrue("The account created message is displayed", accountCreated.isDisplayed());
    }
}
