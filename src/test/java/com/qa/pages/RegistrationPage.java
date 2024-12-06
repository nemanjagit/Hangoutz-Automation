package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class RegistrationPage extends BaseTest {

    public RegistrationPage(){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "registerLogo")
    @iOSXCUITFindBy(accessibility = "Hangoutz")
    public WebElement logo;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"registerNameInput\"]/parent::*")
    @iOSXCUITFindBy(accessibility = "nameField")
    public WebElement nameField;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"registerEmailInput\"]/parent::*")
    @iOSXCUITFindBy(accessibility = "emailField")
    public WebElement emailField;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"registerPasswordInput\"]/parent::*")
    @iOSXCUITFindBy(accessibility = "passwordField")
    public WebElement passwordField;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"registerConfirmPasswordInput\"]/parent::*")
    @iOSXCUITFindBy(accessibility = "confirmPasswordField")
    public WebElement confirmPasswordField;

    @AndroidFindBy(accessibility = "actionButtonText")
    @iOSXCUITFindBy(accessibility = "createAccountButton")
    public WebElement createAccountButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"All fields must be filled!\"]")
    @iOSXCUITFindBy(accessibility = "All fields must be filled.")
    public WebElement emptyErrorMessage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Name must be 3–25 characters\"]")
    @iOSXCUITFindBy(accessibility = "Name must be 3-25 characters")
    public WebElement invalidNameErrorMessage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Email must be in correct format\"]")
    @iOSXCUITFindBy(accessibility = "Email must be in correct format")
    public WebElement invalidEmailErrorMessage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Password must contain at least one number and be minimum 8 characters long\"]")
    @iOSXCUITFindBy(accessibility = "Password must contain at least one number and be minimum 8 characters long")
    public WebElement invalidPasswordErrorMessage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Passwords must match\"]")
    @iOSXCUITFindBy(accessibility = "Password must match")
    public WebElement invalidConfirmPasswordErrorMessage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Email already in use\"]")
    @iOSXCUITFindBy(accessibility = "Email already in use")
    public WebElement existingEmailErrorMessage;

    public void enterName(String name) {
    clear(nameField);
    sendKeys(nameField, name);
    }

    public void enterEmail(String email) {
        clear(emailField);
        sendKeys(emailField, email);
    }

    public void enterPassword(String password) {
        clear(passwordField);
        sendKeys(passwordField, password);
    }
    public void enterConfirmPassword(String password) {
        clear(confirmPasswordField);
        sendKeys(confirmPasswordField, password);
    }

    public LoginPage clickCreateAccountButton() {
        click(createAccountButton);
        return new LoginPage();
    }

    public String getErrorMessage(WebElement element) {
        waitForVisibility(element);
        return element.getText();
    }


}
