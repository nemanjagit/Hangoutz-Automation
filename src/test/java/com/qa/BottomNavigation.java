package com.qa;

import com.qa.pages.EventPage;
import com.qa.pages.FriendsPage;
import com.qa.pages.SettingsPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BottomNavigation extends BaseTest{
    public BottomNavigation() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"bottomNavigationBarItem\"])[1]")
    @iOSXCUITFindBy(accessibility = "eventIcon")
    WebElement bottomBarEvents;

    @AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"bottomNavigationBarItem\"])[2]")
    @iOSXCUITFindBy(accessibility = "friendsIcon")
    WebElement bottomBarFriends;

    @AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"bottomNavigationBarItem\"])[3]")
    @iOSXCUITFindBy(accessibility = "settingsIcon")
    WebElement BottomBarSettings;

    public EventPage navigateToEventPage() {
        click(bottomBarEvents);
        return new EventPage();
    }

    public FriendsPage navigateToFriendsPage(){
        click(bottomBarFriends);
        return new FriendsPage();
    }

    public SettingsPage navigateToSettingsPage(){
        click(BottomBarSettings);
        return new SettingsPage();
    }


}
