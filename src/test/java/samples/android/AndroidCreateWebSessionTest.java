package samples.android;


import aquality.appium.application.ApplicationManager;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class AndroidCreateWebSessionTest {
    private AndroidDriver<WebElement> driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("profile", "androidwebsession");
        driver = (AndroidDriver<WebElement>) ApplicationManager.getApplication().getDriver();
    }

    @AfterClass
    public void tearDown() {
        ApplicationManager.getApplication().quit();
        System.clearProperty("profile");
    }

    @Test()
    public void testCreateWebSession() throws URISyntaxException {
        driver.get(new URI("http://www.google.com").toString());
        String title = driver.getTitle();
        Assert.assertEquals(title, "Google");
    }
}
