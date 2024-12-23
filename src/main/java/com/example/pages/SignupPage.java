package com.example.pages;

import com.example.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SignupPage extends BasePage {

    @FindBy(css = "input[data-qa='name']")
    private WebElement username;

    @FindBy(css = "input[data-qa='password']")
    private WebElement userPassword;

    @FindBy(css = "[data-qa='days']")
    private WebElement dayDropdown;

    @FindBy(css = "[data-qa='months']")
    private WebElement monthDropdown;

    @FindBy(css = "[data-qa='years']")
    private WebElement yearDropdown;

    @FindBy(css = "[id='uniform-newsletter']>span>input")
    private WebElement newsletter;

    @FindBy(css = "[id='uniform-optin']>span>input")
    private WebElement specialOffers;

    @FindBy(css = "input[data-qa='first_name']")
    private WebElement firstName;

    @FindBy(css = "input[data-qa='last_name']")
    private WebElement lastName;

    @FindBy(css = "input[data-qa='company']")
    private WebElement companyName;

    @FindBy(css = "input[data-qa='address']")
    private WebElement address1;

    @FindBy(css = "input[data-qa='address2']")
    private WebElement address2;

    @FindBy(css = "[data-qa='country']")
    private WebElement country;

    @FindBy(css = "input[data-qa='state']")
    private WebElement state;

    @FindBy(css = "input[data-qa='city']")
    private WebElement city;

    @FindBy(css = "input[data-qa='zipcode']")
    private WebElement zipcode;

    @FindBy(css = "input[data-qa='mobile_number']")
    private WebElement mobileNumber;

    @FindBy(css = "button[data-qa='create-account']")
    private WebElement createAccountButton;

    @FindBy(css = "[data-qa='continue-button']")
    private WebElement continueButton;

    @FindBy(css = "[data-qa='account-created']")
    private WebElement accountCreatedMessage;

    public void selectGender(String attributeValue) {
        List<WebElement> radioContainers = driver.findElements(By.className("radio-inline"));

        for (WebElement container : radioContainers) {
            WebElement label = container.findElement(By.tagName("label"));
            String forAttribute = label.getAttribute("for");

            if (forAttribute != null && forAttribute.equals(attributeValue)) {
                label.click();
                break;
            }
        }
    }

    public void enterName(String name) {
        username.sendKeys(Keys.CONTROL + "a");
        username.sendKeys(Keys.DELETE);
        username.sendKeys(name);
    }

    public void enterPassword(String password) {
        userPassword.sendKeys(password);
    }

    public void selectDateOfBirth(String day, String month, String year) {
        Select selectDay = new Select(dayDropdown);
        selectDay.selectByValue(day);
        Select selectMonth = new Select(monthDropdown);
        selectMonth.selectByValue(month);
        Select selectYear = new Select(yearDropdown);
        selectYear.selectByValue(year);
    }

    public void signUpNewsletter() {
        newsletter.click();
    }

    public void receiveOffers() {
        specialOffers.click();
    }

    public void enterFirstName(String name) {
        firstName.sendKeys(name);
    }

    public void enterLastName(String name) {
        lastName.sendKeys(name);
    }

    public void enterCompany(String company) {
        companyName.sendKeys(company);
    }

    public void enterAddress1(String address) {
        address1.sendKeys(address);
    }

    public void enterAddress2(String address) {
        address2.sendKeys(address);
    }

    public void selectCountry(String countryName) {
        Select selectCountry = new Select(country);
        selectCountry.selectByValue(countryName);
    }

    public void enterState(String stateName) {
        state.sendKeys(stateName);
    }

    public void enterCity(String cityName) {
        city.sendKeys(cityName);
    }

    public void enterZipCode(String zipcodeNumber) {
        zipcode.sendKeys(zipcodeNumber);
    }

    public void enterMobile(String mobile) {
        mobileNumber.sendKeys(mobile);
    }

    public void createAccount() {
        createAccountButton.click();
    }

    public void continueAfterCreatingAccount() {
        continueButton.click();
    }

    public WebElement getCreatedMessage(){
        return accountCreatedMessage;
    }

    public SignupPage(WebDriver driver) {
        super(driver);
    }
}
