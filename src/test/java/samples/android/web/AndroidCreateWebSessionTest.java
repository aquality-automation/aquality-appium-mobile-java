package samples.android.web;

import aquality.appium.mobile.application.AqualityServices;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AndroidCreateWebSessionTest extends AndroidWebTest {
    @Test(groups = "web")
    public void testCreateWebSession() {
        AqualityServices.getApplication().getDriver().get("http://www.google.com");
        String title = AqualityServices.getApplication().getDriver().getTitle();
        Assert.assertEquals(title, "Google");
    }
}
