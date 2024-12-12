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
import java.util.Random;

public class E2ETestsIOS extends BaseTest {
    JSONObject data;
    InputStream dataIs;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    EventPage eventPage;
    JSONObject registrationScreen;
    JSONObject friendsScreen;
    SettingsPage settingsPage;
    FriendsPage friendsPage;
    Random random = new Random();

    @BeforeClass
    public void beforeClass() throws Exception {
        try {
            String dataFileName = "data/strings.json";
            dataIs = getClass().getClassLoader().getResourceAsStream(dataFileName);
            JSONTokener tokener = new JSONTokener(dataIs);

            data = new JSONObject(tokener);
            registrationScreen = data.getJSONObject("registrationScreen");
            friendsScreen = data.getJSONObject("friendsScreen");

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
        Assert.assertTrue(isDisplayed(loginPage.logo));
        registrationPage = loginPage.clickCreateAccountButton();
        registrationPage.registerNewAccount(registrationScreen.getString("validName"),
                                            (registrationScreen.getString("validEmail")),
                                            registrationScreen.getString("validPassword"),
                                            registrationScreen.getString("validPassword"));
        eventPage = loginPage.login(registrationScreen.getString("validEmail"),
                                registrationScreen.getString("validPassword"));
        Assert.assertTrue(isDisplayed(eventPage.going));
        friendsPage = eventPage.navigateToFriendsPage();
        friendsPage.clickAddFriendButton();
        friendsPage.clickOnSearchField();
        friendsPage.enterUserName(friendsScreen.getString("enteredName"));
        friendsPage.addUser();
        friendsPage.scrollDown();
        String expectedFriendName = friendsScreen.getString("enteredName");
        String actualFriendName = friendsPage.getFriendName();
        Assert.assertEquals(expectedFriendName, actualFriendName);
        settingsPage = eventPage.navigateToSettingsPage();
        loginPage = settingsPage.clickOnLogoutButton();
        Assert.assertTrue(isDisplayed(loginPage.logo));


    }

    @Test
    public void AndroidTest(){

    }
}