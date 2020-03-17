package samples.android;

import aquality.appium.mobile.application.AqualityServices;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testreport.ScreenshotListener;

@Listeners(ScreenshotListener.class)
public class AndroidCreateWebSessionTest implements IAndroidWebSessionTest {
    @Test
    public void testCreateWebSession() {
        AqualityServices.getApplication().getDriver().get("http://www.google.com");
        String title = AqualityServices.getApplication().getDriver().getTitle();
        Assert.assertEquals(title, "Google");
    }
}
