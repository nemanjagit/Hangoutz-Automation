package com.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.qa.utils.TestUtils;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;

import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;


public class BaseTest {
    public static AppiumDriver driver;
    protected Properties properties;
    InputStream inputStream;
    public static String platformName;

    @BeforeMethod
    public void before() throws Exception {

        try {
            properties = new Properties();
            String propertyFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
            properties.load(inputStream);
            URI appUri = URI.create(properties.getProperty("appiumUrl"));
            URL url = appUri.toURL();

            platformName = properties.getProperty("platformName");
            String appUrl;
            switch (platformName) {
                case "Android":
                    appUrl = getClass().getResource(properties.getProperty("androidAppLocation")).getFile();
                    UiAutomator2Options androidOptions = new UiAutomator2Options();
                    androidOptions.
                            setAutomationName(properties.getProperty("androidAutomationName")).
                            setAppPackage(properties.getProperty("androidAppPackage")).
                            setAppActivity(properties.getProperty("androidAppActivity")).
                            setUdid(properties.getProperty("androidUdId")).
                            setApp(appUrl);
                    driver = new AndroidDriver(url, androidOptions);
                    break;

                case "iOS":
                    appUrl = getClass().getResource(properties.getProperty("iOSAppLocation")).getFile();
                    XCUITestOptions iOSOptions = new XCUITestOptions();
                    iOSOptions.setAutomationName(properties.getProperty("iOSAutomationName")).
                            setDeviceName(properties.getProperty("iOSDeviceName")).
                            setPlatformVersion(properties.getProperty("iOSPlatformVersion")).
                            setUdid(properties.getProperty("iOSUdId")).
                            setApp(appUrl);

                    driver = new IOSDriver(url, iOSOptions);
                    break;

                default:
                    throw new Exception("Unsupported platform: " + platformName);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TestUtils.SHORT_WAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForPresence(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TestUtils.SHORT_WAIT);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void click(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    public void sendKeys(WebElement element, String keys) {
        waitForVisibility(element);
        element.sendKeys(keys);
    }

    public String getText(WebElement element) {
        waitForVisibility(element);
        return element.getText();
    }

    public void clear(WebElement element) {
        waitForVisibility(element);
        element.clear();
    }

    public boolean isDisplayed(WebElement element) {
        waitForVisibility(element);
        return element.isDisplayed();
    }

    public static void hideIOSKeyboard(){
        driver.findElement(By.xpath("//XCUIElementTypeTextField")).sendKeys(Keys.RETURN);
    }

    public boolean isPresent(WebElement element) {
        try {
            isDisplayed(element);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private final static PointerInput FINGER = new PointerInput(TOUCH, "finger");
    public  void doSwipe(AppiumDriver driver, Point start, Point end, int duration) {
        Sequence swipe = new Sequence(FINGER, 1)
                .addAction(FINGER.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
                .addAction(FINGER.createPointerDown(LEFT.asArg()))
                .addAction(FINGER.createPointerMove(ofMillis(duration), viewport(), end.getX(), end.getY()))
                .addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }

    public void goBack(){
        AndroidDriver androidDriver = (AndroidDriver) driver;
        androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }


}
