package samples.android.web;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.ILink;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebLinkTest extends AndroidWebTest {

    private ILink link;

    @BeforeMethod
    public void openLinkPage() {
        AqualityServices.getApplication().getDriver().get("http://the-internet.herokuapp.com/redirector");

        ILink content = AqualityServices.getElementFactory().getLink(By.id("content"), "Content");
        link = AqualityServices.getElementFactory().findChildElement(content, By.id("redirect"), ElementType.LINK);
    }

    @Test
    public void testLinkGetsHref(){
        Assert.assertTrue(link.getHref().contains("redirect"), "Link href mismatch");
    }

    @Test
    public void testLinkClickable() {
        link.state().waitForClickable();
        link.click();
        Assert.assertTrue(link.state().waitForNotExist(), "Link was not clicked properly as it isn't disappeared");
    }
}
