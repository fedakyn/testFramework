package com.example.pages;

import com.example.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "[class='fa fa-lock']")
    private WebElement signUpLoginButton;

    @FindBy(css = "[class='fa fa-trash-o']")
    private WebElement deleteAccountButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickSignUpLoginButton() {
        signUpLoginButton.click();
    }

    public void deleteAccount() {
        deleteAccountButton.click();
    }
}
