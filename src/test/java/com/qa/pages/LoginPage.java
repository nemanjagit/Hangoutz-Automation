package com.qa.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.qa.BaseTest;


public class LoginPage extends BaseTest {

    public LoginPage(){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "loginIcon")
    @iOSXCUITFindBy(accessibility = "Hangoutz")
    public WebElement logo;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"loginEmailInputField\"]/parent::*")
    @iOSXCUITFindBy(accessibility = "userName")
    public WebElement emailField;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"loginPasswordInputField\"]/parent::*")
    @iOSXCUITFindBy(accessibility = "userPassword")
    public WebElement passwordField;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"loginSignInButton\"]")
    @iOSXCUITFindBy(accessibility = "login")
    public WebElement loginButton;

    @AndroidFindBy(accessibility = "loginCreateAccount")
    @iOSXCUITFindBy(accessibility = "Create account")
    public WebElement createAccountButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Incorrect email or password\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Incorrect email or password\"]")
    public WebElement incorrectErrorMessage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"All fields must be filled!\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"All fields must be filled!\"]")
    public WebElement emptyErrorMessage;


    public void enterEmail(String email) {
        clear(emailField);
        sendKeys(emailField, email);
    }

    public void enterPassword(String password) {
        clear(passwordField);
        sendKeys(passwordField, password);
    }

    public EventPage clickLoginButton() {
        click(loginButton);
        return new EventPage();
    }

    public RegistrationPage clickCreateAccountButton() {
        click(createAccountButton);
        return new RegistrationPage();
    }

    public String getErrorMessage(WebElement element) {
        waitForVisibility(element);
        return element.getText();
    }

    public EventPage Login (String email, String password) {
        enterEmail(email);
        enterPassword(password);
        return clickLoginButton();
    }

}
