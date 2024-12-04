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

//    @AndroidFindBy(accessibility = "registerLogo")
//    @iOSXCUITFindBy()
//    public WebElement logo;
//
//    @AndroidFindBy()
//    @iOSXCUITFindBy()
//    public WebElement nameField;
//
//    @AndroidFindBy()
//    @iOSXCUITFindBy()
//    public WebElement emailField;
//
//    @AndroidFindBy()
//    @iOSXCUITFindBy()
//    public WebElement passwordField;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"registerConfirmPasswordInput\"]/parent::*")
    @iOSXCUITFindBy(accessibility = "confirmPasswordField")
    public WebElement confirmPasswordField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Create Account']")
    @iOSXCUITFindBy(accessibility = "Create account")
    public WebElement createAccountButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"All fields must be filled!\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"All fields must be filled!\"]")
    public WebElement emptyErrorMessage;


//    public void enterEmail(String email) {
//        clear(emailField);
//        sendKeys(emailField, email);
//    }
//
//    public void enterPassword(String password) {
//        clear(passwordField);
//        sendKeys(passwordField, password);
//    }

    public void clickCreateAccountButton() {
        click(createAccountButton);
    }

    public String getErrorMessage(WebElement element) {
        waitForVisibility(element);
        return element.getText();
    }


}
