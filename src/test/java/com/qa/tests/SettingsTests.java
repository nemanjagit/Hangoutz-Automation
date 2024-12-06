package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.EventPage;
import com.qa.pages.LoginPage;
import com.qa.pages.SettingsPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.InputStream;

public class SettingsTests extends BaseTest {
    LoginPage loginPage;
    EventPage eventPage;
    SettingsPage settingsPage;
    JSONObject data;
    InputStream dataIs;
    JSONObject settingsScreen;
    JSONObject loginScreen;
    String email;


    @BeforeClass
    public void beforeClass() throws Exception {
        try {
            String dataFileName = "data/strings.json";
            dataIs = getClass().getClassLoader().getResourceAsStream(dataFileName);
            JSONTokener tokener = new JSONTokener(dataIs);

            data = new JSONObject(tokener);
            settingsScreen = data.getJSONObject("settingsScreen");
            loginScreen = data.getJSONObject("loginScreen");

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
        Assert.assertTrue(isDisplayed(loginPage.logo));
        email = loginScreen.getString("validEmail");
        String password = loginScreen.getString("validPassword");
        eventPage = loginPage.login(email, password);
        settingsPage = eventPage.navigateToSettingsPage();
    }

    @Test
    public void changeName(){
        settingsPage.clickOnEditIcon();
        String nameToEnter = data.getJSONObject("settingsScreen").getString("enteredName");
        settingsPage.enterName(nameToEnter);
        settingsPage.clickOnConfirmEditIcon();
        String actualName = settingsPage.getName();
        Assert.assertEquals(nameToEnter, actualName);
    }

    @Test
    public void checkEmail(){
        String actualEmail = settingsPage.getEmail();
        Assert.assertEquals(email, actualEmail);
    }

    @Test
    public void checkLogout(){
        loginPage = settingsPage.clickOnLogoutButton();
        Assert.assertTrue(loginPage.isLogoPresent());
    }


}
