package com.example.pages;

import com.example.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "input[data-qa='signup-name")
    private WebElement newUserNameInput;

    @FindBy(css = "input[data-qa='signup-email']")
    private WebElement newUserEmailInput;

    @FindBy(css = "button[data-qa='signup-button']")
    private WebElement signupButton;

    @FindBy(css = "input[data-qa='login-email']")
    private WebElement loginInput;

    @FindBy(css = "input[data-qa='login-password']")
    private WebElement passwordInput;

    @FindBy(css = "button[data-qa='login-button']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterSignupName(String signupName) {
        newUserNameInput.sendKeys(signupName);
    }

    public void enterSignupEmail(String signupEmail) {
        newUserEmailInput.sendKeys(signupEmail);
    }

    public void clickSignupButton() {
        signupButton.click();
    }

    public void enterLogin(String email) {
        loginInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void pressLogin() {
        loginButton.click();
    }
}
