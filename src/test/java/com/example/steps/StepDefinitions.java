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
import com.example.helpers.Util;

public class StepDefinitions extends BaseTest {

    SignupPage signupPage = new SignupPage(driver);

    @Given("I launch the browser")
    public void browser_launch(){
        setUp();
    }

    @When("I open the application")
    public void open_application(){
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
    public void click_signup(){
        HomePage homePage = new HomePage(driver);
        homePage.clickSignUpLoginButton();
    }

    @And("I input the signup name {word}")
    public void input_signup_name(String username){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterSignupName(username);
    }

    @And("I input the signupEmail")
    public void input_signup_email(){
        String email = Util.generateRandomString(10);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterSignupEmail(email + "@test.net");
    }

    @And("I press signup")
    public void press_signup(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignupButton();
    }

    @And ("I complete all the fields for registering")
    public void complete_all_fields(){
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
    public void check_account_created(){
        SignupPage signupPage = new SignupPage(driver);
        WebElement accountCreated = signupPage.getCreatedMessage();
        Assert.assertTrue("The account created message is not displayed", accountCreated.isDisplayed());
    }

}
