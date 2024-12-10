package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.EventPage;
import com.qa.pages.LoginPage;
import com.qa.pages.RegistrationPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.Random;

public class RegistrationTests extends BaseTest {
    RegistrationPage registrationPage;
    JSONObject data;
    InputStream dataIs;
    LoginPage loginPage;
    EventPage eventPage;
    JSONObject registrationScreen;
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
        registrationPage = loginPage.clickCreateAccountButton();
    }


    @Test(priority = 6)
    public void successfulRegistration() {
        Assert.assertTrue(isDisplayed(registrationPage.logo));
        registrationPage.enterName(registrationScreen.getString("validName"));
        int randomInt = random.nextInt(10000000);
        registrationPage.enterEmail(randomInt + registrationScreen.getString("validEmail"));
        registrationPage.enterPassword(registrationScreen.getString("validPassword"));
        registrationPage.enterConfirmPassword(registrationScreen.getString("validPassword"));
        if (platformName.equals("iOS")) {
            hideIOSKeyboard();
        }
        loginPage = registrationPage.clickCreateAccountButton();
        Assert.assertTrue(isDisplayed(loginPage.logo));
        eventPage = loginPage.login(randomInt + registrationScreen.getString("validEmail"), registrationScreen.getString("validPassword"));
        Assert.assertTrue(isDisplayed((eventPage.going)));

    }

    @Test(priority = 5)
    public void emptyFields() {
        Assert.assertTrue(isDisplayed(registrationPage.logo));
        loginPage = registrationPage.clickCreateAccountButton();
        String expectedErrorMessage = registrationScreen.getString("emptyErrorMessage");
        String actualErrorMessage = registrationPage.getErrorMessage(registrationPage.emptyErrorMessage);
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test(priority = 4)
    public void invalidEmail() {
        Assert.assertTrue(isDisplayed(registrationPage.logo));
        registrationPage.enterName(registrationScreen.getString("validName"));
        registrationPage.enterEmail(registrationScreen.getString("invalidEmail"));
        registrationPage.enterPassword(registrationScreen.getString("validPassword"));
        registrationPage.enterConfirmPassword(registrationScreen.getString("validPassword"));
        if (platformName.equals("iOS")) {
            hideIOSKeyboard();
        }
        loginPage = registrationPage.clickCreateAccountButton();
        String expectedErrorMessage = registrationScreen.getString("invalidEmailMessage");
        String actualErrorMessage = registrationPage.getErrorMessage(registrationPage.invalidEmailErrorMessage);
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);

    }

    @Test(priority = 3)
    public void invalidName() {
        Assert.assertTrue(isDisplayed(registrationPage.logo));
        registrationPage.enterName(registrationScreen.getString("invalidName"));
        registrationPage.enterEmail(registrationScreen.getString("validEmail"));
        registrationPage.enterPassword(registrationScreen.getString("validPassword"));
        registrationPage.enterConfirmPassword(registrationScreen.getString("validPassword"));
        if (platformName.equals("iOS")) {
            hideIOSKeyboard();
        }
        loginPage = registrationPage.clickCreateAccountButton();
        String expectedErrorMessage = registrationScreen.getString("invalidNameMessage");
        String actualErrorMessage = registrationPage.getErrorMessage(registrationPage.invalidNameErrorMessage);
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test(priority = 2)
    public void invalidPassword() {
        Assert.assertTrue(isDisplayed(registrationPage.logo));
        registrationPage.enterName(registrationScreen.getString("validName"));
        registrationPage.enterEmail(registrationScreen.getString("validEmail"));
        registrationPage.enterPassword(registrationScreen.getString("invalidPassword"));
        registrationPage.enterConfirmPassword(registrationScreen.getString("invalidPassword"));
        if (platformName.equals("iOS")) {
            hideIOSKeyboard();
        }
        loginPage = registrationPage.clickCreateAccountButton();
        String expectedErrorMessage = registrationScreen.getString("invalidPasswordMessage");
        String actualErrorMessage = registrationPage.getErrorMessage(registrationPage.invalidPasswordErrorMessage);
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);

    }

    @Test(priority = 1)
    public void invalidConfirmPassword() {
        Assert.assertTrue(isDisplayed(registrationPage.logo));
        registrationPage.enterName(registrationScreen.getString("validName"));
        registrationPage.enterEmail(registrationScreen.getString("validEmail"));
        registrationPage.enterPassword(registrationScreen.getString("validPassword"));
        registrationPage.enterConfirmPassword(registrationScreen.getString("invalidConfirmPassword"));
        if (platformName.equals("iOS")) {
            hideIOSKeyboard();
        }
        loginPage = registrationPage.clickCreateAccountButton();
        String expectedErrorMessage = registrationScreen.getString("invalidConfirmPasswordMessage");
        String actualErrorMessage = registrationPage.getErrorMessage(registrationPage.invalidConfirmPasswordErrorMessage);
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test(priority = 7)
    public void emailAlreadyExist() {
        Assert.assertTrue(isDisplayed(registrationPage.logo));
        registrationPage.enterName(registrationScreen.getString("validName"));
        registrationPage.enterEmail(registrationScreen.getString("validEmail"));
        registrationPage.enterPassword(registrationScreen.getString("validPassword"));
        registrationPage.enterConfirmPassword(registrationScreen.getString("validPassword"));
        if (platformName.equals("iOS")) {
            hideIOSKeyboard();
        }
        loginPage = registrationPage.clickCreateAccountButton();
        String expectedErrorMessage = registrationScreen.getString("existingEmailErrorMessage");
        String actualErrorMessage = registrationPage.getErrorMessage(registrationPage.existingEmailErrorMessage);
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);

    }


}

