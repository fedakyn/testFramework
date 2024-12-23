package com.example.steps;

import com.example.base.BaseTest;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.example.pages.SignupPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.example.utils.Util;

public class StepDefinitions extends BaseTest {

    SignupPage signupPage = new SignupPage(driver);

    @Given("I launch the browser")
    public void i_launch_the_browser(){
        setUp();
    }

    @When("I open the application")
    public void i_open_the_application(){
        driver.get("https://automationexercise.com/");
        try {
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[class='fc-button fc-cta-consent fc-primary-button']")));
            acceptCookiesButton.click();
            System.out.println("Cookies popup handled successfully.");
        } catch (Exception e) {
            System.out.println("Cookies popup not found or already accepted.");
        }
    }

    @And ("I click on signup login")
    public void i_click_on_signup_login(){
        HomePage homePage = new HomePage(driver);
        homePage.clickSignUpLoginButton();
    }

    @And("I input the signup name {word}")
    public void i_input_the_signup_name(String username){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterSignupName(username);
    }

    @And("I input the signupEmail")
    public void i_input_the_password(){
        String email = Util.generateRandomString(10);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterSignupEmail(email + "@test.net");
    }

    @And("I press signup")
    public void i_press_signup(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignupButton();
    }

    @And ("I complete all the fields for registering")
    public void i_complete_all_the_fields_for_registering(){
        SignupPage signupPage = new SignupPage(driver);
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
    public void i_check_account_created_message(){
        SignupPage signupPage = new SignupPage(driver);
        WebElement accountCreated = signupPage.getCreatedMessage();
        Assert.assertTrue("The account created message is not displayed", accountCreated.isDisplayed());
    }

}
