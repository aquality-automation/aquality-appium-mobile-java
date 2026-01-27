package samples.android.web;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.selenium.core.configurations.ITimeoutConfiguration;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebTextBoxTest extends AndroidWebTest {

    private static final String VALUE_TO_SUBMIT = "quality assurance";
    private static final ITextBox txbSearch = AqualityServices.getElementFactory().getTextBox(By.id("searchInput"), "Search");
    private static final IButton btnOverlayToggle = AqualityServices.getElementFactory().getButton(By.className("button-collapse"), "Toggle Overlay");
    private static final IButton btnCloseBanner = AqualityServices.getElementFactory().getButton(By.className("overlay-banner-close"), "Close banner");

    @Test
    public void testTextBoxInteraction() {
        AqualityServices.getApplication().getDriver().get("https://wikipedia.org");
        txbSearch.state().waitForClickable();
        if (btnOverlayToggle.state().isDisplayed()) {
            btnOverlayToggle.click();
            btnCloseBanner.click();
        }
        txbSearch.type(VALUE_TO_SUBMIT);
        Assert.assertEquals(txbSearch.getValue(), VALUE_TO_SUBMIT, "Submitted value should match to expected");
        txbSearch.clear();
        Assert.assertEquals(txbSearch.getValue(), "", "Value should be cleared");
        txbSearch.click();
        checkUnfocus();
        txbSearch.focus();
        Assert.assertTrue(isKeyboardShown(true), "Keyboard should be shown when focus successful");
        txbSearch.typeSecret(VALUE_TO_SUBMIT);
        Assert.assertEquals(txbSearch.getValue(), VALUE_TO_SUBMIT, "Submitted value should match to expected");
        txbSearch.clearAndType(VALUE_TO_SUBMIT);
        Assert.assertEquals(txbSearch.getValue(), VALUE_TO_SUBMIT, "Submitted value should match to expected");
        txbSearch.clearAndTypeSecret(VALUE_TO_SUBMIT);
        Assert.assertEquals(txbSearch.getValue(), VALUE_TO_SUBMIT, "Submitted value should match to expected");
        txbSearch.sendKeys(Keys.ENTER);
        Assert.assertTrue(txbSearch.state().waitForNotDisplayed(), "text field should disappear after the submit");
    }

    private void checkUnfocus() {
        txbSearch.unfocus();
        Assert.assertFalse(isKeyboardShown(false), "Keyboard should not be shown when unfocus successful");
    }

    private boolean isKeyboardShown(boolean expectedStateToWait) {
        ITimeoutConfiguration timeoutConfiguration = AqualityServices.getConfiguration().getTimeoutConfiguration();
        boolean waitResult = AqualityServices.getConditionalWait()
                .waitFor(driver -> ((AndroidDriver) driver).isKeyboardShown() == expectedStateToWait,
                        timeoutConfiguration.getCommand(),
                        timeoutConfiguration.getPollingInterval().multipliedBy(10),
                        String.format("is keyboard shown condition should be %s", expectedStateToWait));
        return expectedStateToWait == waitResult;
    }
}
