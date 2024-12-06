package com.qa.pages;

import com.qa.BottomNavigation;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class SettingsPage extends BottomNavigation {
    @AndroidFindBy(accessibility = "Profile Photo")
    @iOSXCUITFindBy(accessibility = "image")
    public WebElement image;

    @AndroidFindBy(accessibility = "settingsNameValidatorIcon")
    @iOSXCUITFindBy(accessibility = "pencil")
    public WebElement editIcon;

    @AndroidFindBy(accessibility = "settingsNameValidatorIcon")
    @iOSXCUITFindBy(accessibility = "checkmark")
    public WebElement confirmEditIcon;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"settingsNameField\"]/parent::*")
    @iOSXCUITFindBy(className = "XCUIElementTypeTextField")
    public WebElement editName;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"settingsNameField\"]/parent::*")
    @iOSXCUITFindBy(accessibility = "nameLabel")
    public WebElement name;

    @AndroidFindBy(xpath = "//*[@content-desc='settingsBackgroundLines']/following-sibling::*[2]")
    @iOSXCUITFindBy(accessibility = "emailLabel")
    public WebElement emailLabel;

    @AndroidFindBy(accessibility = "actionButtonText")
    @iOSXCUITFindBy(accessibility = "logoutButton")
    public WebElement logoutButton;

    public void clickOnEditIcon() {
        editIcon.click();
    }

    public void enterName(String name) {
        editName.clear();
        editName.sendKeys(name);
    }

    public void clickOnConfirmEditIcon() {
        confirmEditIcon.click();
    }

    public String getName() {
        return getText(name);
    }

    public String getEmail() {
        return getText(emailLabel);
    }

    public LoginPage clickOnLogoutButton() {
        logoutButton.click();
        return new LoginPage();
    }



}
