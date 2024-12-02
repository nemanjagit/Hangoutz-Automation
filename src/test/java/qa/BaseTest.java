package qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.TestUtils;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Properties;


public class BaseTest {
    protected static AppiumDriver driver;
    protected static Properties properties;
    InputStream inputStream;
    public String platformName;

    @BeforeClass
    public void beforeClass() throws Exception {

        try {
            properties = new Properties();
            String propertyFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
            properties.load(inputStream);
            URI appUri = URI.create(properties.getProperty("appiumURL"));
            URL url = appUri.toURL();

            platformName = properties.getProperty("platformName");
            String appUrl;
            switch (platformName) {
                case "Android":
                    appUrl = getClass().getClassLoader().getResource(properties.getProperty("androidAppLocation")).getFile();
                    UiAutomator2Options androidOptions = new UiAutomator2Options();
                    androidOptions.
                            setAutomationName(properties.getProperty("androidAutomationName")).
                            setAppPackage(properties.getProperty("androidAppPackage")).
                            setAppActivity(properties.getProperty("androidAppActivity")).
                            setApp(appUrl);
                    driver = new AndroidDriver(url, androidOptions);
                    break;

                case "iOS":
                    appUrl = getClass().getResource(properties.getProperty("iOSAppLocation")).getFile();
                    XCUITestOptions iOSOptions = new XCUITestOptions();
                    iOSOptions.setAutomationName(properties.getProperty("iOSAutomationName")).
                            setDeviceName(properties.getProperty("iOSDeviceName")).
                            setPlatformVersion(properties.getProperty("iOSPlatformVersion")).
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
        WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
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

    @AfterClass
    public void afterClass(){
        driver.quit();
    }


}
