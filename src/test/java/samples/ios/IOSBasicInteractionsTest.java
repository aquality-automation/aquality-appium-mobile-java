package samples.ios;

import aquality.appium.application.ApplicationManager;
import io.appium.java_client.ios.IOSDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import samples.ios.testapp.screens.MainScreen;
import samples.ios.testapp.screens.TwoItemsAlertScreen;

public class IOSBasicInteractionsTest {
    private IOSDriver<?> driver;

    @BeforeClass
    public void setUp() {
        driver = (IOSDriver<?>) ApplicationManager.getApplication().getDriver();
    }

    @AfterClass
    public void tearDown() {
        ApplicationManager.getApplication().quit();
    }

    @Test
    public void testSendKeysToInput() {
        MainScreen mainScreen = new MainScreen();
        Assert.assertTrue(mainScreen.isDisplayed(), "Main screen of app wasn't loaded");
        mainScreen.enterTextToFirstField("Hello World!");
        Assert.assertEquals(mainScreen.getValueFromFirstTextBox(), "Hello World!");
    }

    @Test
    public void testAcceptAlertViaButtons() {
        MainScreen mainScreen = new MainScreen();
        mainScreen.tapShowAlert();
        TwoItemsAlertScreen twoItemsAlertScreen = new TwoItemsAlertScreen();
        Assert.assertTrue(twoItemsAlertScreen.isDisplayed(), "Alert wasn't opened");
        Assert.assertEquals(twoItemsAlertScreen.getTitle(), "Cool title");
        twoItemsAlertScreen.acceptAlert();
    }

    @Test
    public void testAcceptAlertViaDriver() {
        MainScreen mainScreen = new MainScreen();
        mainScreen.tapShowAlert();
        TwoItemsAlertScreen twoItemsAlertScreen = new TwoItemsAlertScreen();
        Assert.assertTrue(twoItemsAlertScreen.isDisplayed(), "Alert wasn't opened");
        Assert.assertEquals(twoItemsAlertScreen.getTitle(), "Cool title");
        driver.switchTo().alert().accept();
        Assert.assertTrue(mainScreen.isDisplayed(), "Main screen wasn't opened");
    }
}
