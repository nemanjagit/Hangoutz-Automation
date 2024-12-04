package com.qa.tests;

import com.qa.pages.RegistrationPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.pages.EventPage;
import com.qa.pages.LoginPage;
import com.qa.BaseTest;
import java.io.InputStream;


public class LoginTests extends BaseTest {
    LoginPage loginPage;
    InputStream dataIs;
    JSONObject data;
    EventPage eventPage;
    RegistrationPage registrationPage;

    @BeforeClass
    public void beforeClass() throws Exception {
        try {
            String dataFileName = "data/strings.json";
            dataIs = getClass().getClassLoader().getResourceAsStream(dataFileName);
            JSONTokener tokener = new JSONTokener(dataIs);

            data = new JSONObject(tokener);
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
    public void incorrectPasswordLogin(){
        Assert.assertTrue(isDisplayed(loginPage.logo));
        loginPage.enterEmail(data.getJSONObject("loginScreen").getString("validEmail"));
        loginPage.enterPassword(data.getJSONObject("loginScreen").getString("invalidPassword"));
        eventPage = loginPage.clickLoginButton();
        String expectedErrorMessage = data.getJSONObject("loginScreen").getString("incorrectErrorMessage");
        String actualErrorMessage = loginPage.getErrorMessage(loginPage.incorrectErrorMessage);
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void incorrectEmailLogin(){
        Assert.assertTrue(isDisplayed(loginPage.logo));
        loginPage.enterEmail(data.getJSONObject("loginScreen").getString("invalidEmail"));
        loginPage.enterPassword(data.getJSONObject("loginScreen").getString("validPassword"));
        eventPage = loginPage.clickLoginButton();
        String expectedErrorMessage = data.getJSONObject("loginScreen").getString("incorrectErrorMessage");
        String actualErrorMessage = loginPage.getErrorMessage(loginPage.incorrectErrorMessage);
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void emptyEmailLogin(){
        Assert.assertTrue(isDisplayed(loginPage.logo));
        loginPage.enterPassword(data.getJSONObject("loginScreen").getString("validPassword"));
        eventPage = loginPage.clickLoginButton();
        String expectedErrorMessage = data.getJSONObject("loginScreen").getString("emptyErrorMessage");
        String actualErrorMessage = loginPage.getErrorMessage(loginPage.emptyErrorMessage);
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void emptyPasswordLogin(){
        Assert.assertTrue(isDisplayed(loginPage.logo));
        loginPage.enterEmail(data.getJSONObject("loginScreen").getString("validEmail"));
        eventPage = loginPage.clickLoginButton();
        String expectedErrorMessage = data.getJSONObject("loginScreen").getString("emptyErrorMessage");
        String actualErrorMessage = loginPage.getErrorMessage(loginPage.emptyErrorMessage);
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void navigateToRegistrationPage(){
        Assert.assertTrue(isDisplayed(loginPage.logo));
        registrationPage = loginPage.clickCreateAccountButton();
        Assert.assertTrue(isDisplayed(registrationPage.confirmPasswordField));
    }

    @Test
    public void successfulLogin(){
        Assert.assertTrue(isDisplayed(loginPage.logo));
        loginPage.enterEmail(data.getJSONObject("loginScreen").getString("validEmail"));
        loginPage.enterPassword(data.getJSONObject("loginScreen").getString("validPassword"));
        eventPage = loginPage.clickLoginButton();
        Assert.assertTrue(isDisplayed(eventPage.going));
    }



}