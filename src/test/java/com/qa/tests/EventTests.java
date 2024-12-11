package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.EventPage;
import com.qa.pages.LoginPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.InputStream;


public class EventTests extends BaseTest {
    LoginPage loginPage;
    InputStream dataIs;
    JSONObject data;
    EventPage eventPage;
    JSONObject loginScreen;
    JSONObject eventScreen;
    String email;

    @BeforeClass
    public void beforeClass() throws Exception {
        try {
            String dataFileName = "data/strings.json";
            dataIs = getClass().getClassLoader().getResourceAsStream(dataFileName);
            JSONTokener tokener = new JSONTokener(dataIs);

            data = new JSONObject(tokener);
            eventScreen = data.getJSONObject("eventScreen");
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
        Assert.assertTrue(isDisplayed(eventPage.going));
    }

    @Test
    public void checkAcceptedEvent(){
        eventPage.navigateToInvited();
        String title = eventPage.getCardTitle(1);
        eventPage.acceptInvite(1);
        boolean removedFromInvited = !eventPage.cardInList(title);
        Assert.assertTrue(removedFromInvited);
        eventPage.navigateToGoing();
        boolean appearedInGoing = eventPage.cardInList(title);
        Assert.assertTrue(appearedInGoing);

    }



}
