package com.qa.pages;

import com.qa.BottomNavigation;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class EventPage extends BottomNavigation {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"GOING\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"filterBar\" and @label=\"GOING\"]")
    public WebElement going;
}
