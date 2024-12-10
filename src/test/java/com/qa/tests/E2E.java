package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.*;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.Random;

public class E2E extends BaseTest {
    JSONObject data;
    InputStream dataIs;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    EventPage eventPage;
    JSONObject registrationScreen;
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
    public void AndroidTest(){

    }
}