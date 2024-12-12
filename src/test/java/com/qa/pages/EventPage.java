package com.qa.pages;

import com.qa.BottomNavigation;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.List;

public class EventPage extends BottomNavigation {

    @AndroidFindBy(accessibility = "filterBarItemGoing")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"filterBar\" and @label=\"GOING\"]")
    public WebElement going;

    @AndroidFindBy(accessibility = "filterBarItemInvited")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"filterBar\" and @label=\"INVITED\"]")
    public WebElement invited;

    @AndroidFindBy(accessibility = "filterBarItemCreated")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"filterBar\" and @label=\"CREATED\"]")
    public WebElement created;

    @AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"eventCard\"])/parent::*")
    //@iOSXCUITFindBy(accessibility = "card") to be added
    public volatile List<WebElement> eventCards;


    public void navigateToInvited(){
        click(invited);
        //add iOS
        waitForPresence(AppiumBy.accessibilityId("acceptInvitationButton"));
        eventCards = driver.findElements(AppiumBy.xpath("(//android.view.View[@content-desc=\"eventCard\"])/parent::*"));
    }
    public void navigateToCreated(){
        click(created);
        //add iOS
        //waitForPresence(AppiumBy.accessibilityId(""));
        //eventCards = driver.findElements(AppiumBy.xpath("(//android.view.View[@content-desc=\"eventCard\"])/parent::*"));
    }

    public void navigateToGoing(){
        click(going);
        //add iOS
        waitForPresence(AppiumBy.accessibilityId("numberOfPeople"));
        eventCards = driver.findElements(AppiumBy.xpath("(//android.view.View[@content-desc=\"eventCard\"])/parent::*"));
    }

    public WebElement getCard(int cardNumber) {
        return eventCards.get(cardNumber - 1);
    }

    public String getCardTitle(int cardNumber) {
        WebElement card = getCard(cardNumber);
        WebElement titleElement;
        if (platformName.equals("Android")) {
            titleElement = card.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"eventTitle\"]"));
        }
        else {
            //add iOS locator!!!!!!!!
            titleElement = card.findElement(AppiumBy.accessibilityId("cardTitle"));
        }
        return getText(titleElement);
    }

    public void acceptInvite(int cardNumber) {
        WebElement card = getCard(cardNumber);
        //add iOS
        WebElement acceptButtonElement = card.findElement(AppiumBy.xpath("(//android.view.View[@content-desc=\"acceptInvitationButton\"])"));
        click(acceptButtonElement);
        eventCards = driver.findElements(AppiumBy.xpath("(//android.view.View[@content-desc=\"eventCard\"])/parent::*"));
    }

    //assumed that event title is unique
    public boolean cardInList(String title) {
        boolean found = false;
        for (int i = 1; i < eventCards.size()+1; i++) {
            if (getCardTitle(i).equals(title)) {
                found = true;
                System.out.println("Found");
                break;
            }
        }
        return found;
    }

    public void tapOnCard(String title) {
        waitForPresence(AppiumBy.xpath("(//android.view.View[@content-desc=\"eventCard\"])/parent::*"));
        eventCards = driver.findElements(AppiumBy.xpath("(//android.view.View[@content-desc=\"eventCard\"])/parent::*"));
        WebElement card;
        for (int i = 1; i < eventCards.size()+1; i++) {
            if(getCardTitle(i).equals(title)) {
                card = eventCards.get(i-1);
                click(card);
                break;
            }
        }
    }

    public void scrollToParticipant(){
        doSwipe(driver,new Point((int) (523), (int) (1100)),new Point((int) (523), (int) (600)),2000);

    }

    public String getParticipantName(String expectedName) {
        //waitForPresence(AppiumBy.xpath("//android.widget.TextView[@text=\""+expectedName+"\"]"));
        WebElement element = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\""+expectedName+"\"]"));
        return getText(element);

    }
}
