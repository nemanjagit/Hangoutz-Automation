package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FriendsPage extends BaseTest {

    public FriendsPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy()
    @iOSXCUITFindBy(accessibility = "AddFriendButton")
    public WebElement addFriendButton;

    @AndroidFindBy()
    @iOSXCUITFindBy(accessibility = "usersSearchField")
    public WebElement usersSearchField;

    @AndroidFindBy()
    @iOSXCUITFindBy(accessibility = "usersSearchFieldClearButton")
    public WebElement usersSearchFieldClearButton;

    @AndroidFindBy()
    @iOSXCUITFindBy(accessibility = "addUserButton")
    public WebElement addUserButton;

    @AndroidFindBy()
    @iOSXCUITFindBy(accessibility = "friendsSearchField")
    public WebElement friendsSearchField;

    @AndroidFindBy()
    @iOSXCUITFindBy(accessibility = "friendName")
    public WebElement friendName;


    public void enterUserName(String userName) {
        clear(usersSearchField);
        usersSearchField.sendKeys(userName);
    }

    public void clickAddFriendButton() {
        click(addFriendButton);
    }

    public void closeSearchPopUp() {
        click(friendsSearchField);
    }

    public void clickOnSearchField() {
        click(usersSearchField);
    }

    public String getFriendName() {
        return getText(friendName);
    }

    public void addUser() {
        click(addUserButton);
    }

    public void scrollDown() {
        doSwipe(driver, new Point((int) (180), (int) (75)), new Point((int) (180), (int) (700)), 1000);
    }
}
