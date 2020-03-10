package samples.android;


import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.MobileModule;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
        AqualityServices.initInjector(new MobileModule(AqualityServices::getApplication));
        driver = (AndroidDriver<WebElement>) AqualityServices.getApplication().getDriver();
    }

    @AfterClass
    public void tearDown() {
        AqualityServices.getApplication().quit();
        System.clearProperty("profile");
    }

    @Test
    public void testCreateWebSession() throws URISyntaxException {
        driver.get(new URI("http://www.google.com").toString());
        String title = driver.getTitle();
        Assert.assertEquals(title, "Google");
    }

    @Test
    public void testTextBoxInteraction() {
        driver.get("https://wikipedia.org");
        ITextBox txbSearch = AqualityServices.getElementFactory().getTextBox(By.id("searchInput"), "Search");
        txbSearch.state().waitForClickable();
        txbSearch.click();
        Assert.assertTrue(driver.isKeyboardShown(), "Keyboard should be shown when click successful");
        txbSearch.unfocus();
        Assert.assertFalse(driver.isKeyboardShown(), "Keyboard should not be shown when unfocus successful");
        txbSearch.focus();
        Assert.assertTrue(driver.isKeyboardShown(), "Keyboard should be shown when focus successful");
        final String valueToSubmit = "quality assurance";
        txbSearch.type(valueToSubmit);
        Assert.assertEquals(valueToSubmit, txbSearch.getValue(), "Submitted value should match to expected");
        txbSearch.clear();
        Assert.assertEquals("", txbSearch.getValue(), "Value should be cleared");
        txbSearch.typeSecret(valueToSubmit);
        Assert.assertEquals(valueToSubmit, txbSearch.getValue(), "Submitted value should match to expected");
        txbSearch.clearAndType(valueToSubmit);
        Assert.assertEquals(valueToSubmit, txbSearch.getValue(), "Submitted value should match to expected");
        txbSearch.clearAndTypeSecret(valueToSubmit);
        Assert.assertEquals(valueToSubmit, txbSearch.getValue(), "Submitted value should match to expected");
        txbSearch.sendKeys(Keys.ENTER);
        Assert.assertTrue(txbSearch.state().waitForNotDisplayed(), "text field should disappear after the submit");
    }
}
