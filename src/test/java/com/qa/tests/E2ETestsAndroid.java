package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.*;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.InputStream;

public class E2ETestsAndroid extends BaseTest {
    JSONObject data;
    InputStream dataIs;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    EventPage eventPage;
    JSONObject registrationScreen;
    JSONObject loginScreen;
    JSONObject settingsScreen;
    SettingsPage settingsPage;
    FriendsPage friendsPage;
    String nameToEnter;
    String validEmail;
    String validPassword;

    @BeforeClass
    public void beforeClass() throws Exception {
        try {
            String dataFileName = "data/strings.json";
            dataIs = getClass().getClassLoader().getResourceAsStream(dataFileName);
            JSONTokener tokener = new JSONTokener(dataIs);

            data = new JSONObject(tokener);
            registrationScreen = data.getJSONObject("registrationScreen");
            loginScreen = data.getJSONObject("loginScreen");
            settingsScreen = data.getJSONObject("settingsScreen");
            nameToEnter = settingsScreen.getString("enteredName");
            validEmail = loginScreen.getString("validEmail");
            validPassword = loginScreen.getString("validPassword");

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (dataIs != null) {
                dataIs.close();
            }
        }

    }

    @BeforeMethod
    public void beforeMethod() {
        loginPage = new LoginPage();
    }

    @Test
    public void iOSTest(){

    }

    @Test
    public void AndroidTest() throws InterruptedException {
        //LOGIN
        Assert.assertTrue(isDisplayed(loginPage.logo));
        eventPage = loginPage.login(validEmail, validPassword);
        Assert.assertTrue(isDisplayed(eventPage.going));

        //GET NAME FROM SETTINGS
        settingsPage = eventPage.navigateToSettingsPage();
        String actualName = settingsPage.getNameBeforeEdit();
        eventPage = settingsPage.navigateToEventPage();

        //ACCEPT
        eventPage.navigateToInvited();
        String title = eventPage.getCardTitle(1);
        eventPage.acceptInvite(1);
        boolean removedFromInvited = !eventPage.cardInList(title);
        Assert.assertTrue(removedFromInvited);
        eventPage.navigateToGoing();
        boolean appearedInGoing = eventPage.cardInList(title);
        Assert.assertTrue(appearedInGoing);

        //CHECK NAME BEFORE CHANGE
        eventPage.tapOnCard(title);
        eventPage.scrollToParticipant();
        String participantName = eventPage.getParticipantName(actualName);
        Assert.assertEquals(actualName, participantName);

        //CHANGE NAME
        goBack();
        settingsPage = eventPage.navigateToSettingsPage();
        settingsPage.clickOnEditIcon();
        settingsPage.enterName(nameToEnter);
        settingsPage.clickOnConfirmEditIcon();

        //CHECK CHANGED NAME
        eventPage = settingsPage.navigateToEventPage();
        Thread.sleep(500);
        eventPage.tapOnCard(title);
        eventPage.scrollToParticipant();
        participantName = eventPage.getParticipantName(nameToEnter);
        Assert.assertEquals(nameToEnter, participantName);
    }
}