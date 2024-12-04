package com.qa.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.qa.BaseTest;

public class EventPage extends BaseTest {
    public EventPage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"GOING\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"filterBar\" and @label=\"GOING\"]")
    public WebElement going;
}
