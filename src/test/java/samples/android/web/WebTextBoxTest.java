package samples.android.web;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebTextBoxTest extends AndroidWebTest {

    private static final String VALUE_TO_SUBMIT = "quality assurance";

    @Test
    public void testTextBoxInteraction() {
        AqualityServices.getApplication().getDriver().get("https://wikipedia.org");
        ITextBox txbSearch = AqualityServices.getElementFactory().getTextBox(By.id("searchInput"), "Search");
        txbSearch.state().waitForClickable();
        txbSearch.type(VALUE_TO_SUBMIT);
        Assert.assertEquals(VALUE_TO_SUBMIT, txbSearch.getValue(), "Submitted value should match to expected");
        txbSearch.clear();
        Assert.assertEquals("", txbSearch.getValue(), "Value should be cleared");
        txbSearch.click();
        checkUnfocus(txbSearch);
        txbSearch.focus();
        Assert.assertTrue(isKeyboardShown(true), "Keyboard should be shown when focus successful");
        txbSearch.typeSecret(VALUE_TO_SUBMIT);
        Assert.assertEquals(VALUE_TO_SUBMIT, txbSearch.getValue(), "Submitted value should match to expected");
        txbSearch.clearAndType(VALUE_TO_SUBMIT);
        Assert.assertEquals(VALUE_TO_SUBMIT, txbSearch.getValue(), "Submitted value should match to expected");
        txbSearch.clearAndTypeSecret(VALUE_TO_SUBMIT);
        Assert.assertEquals(VALUE_TO_SUBMIT, txbSearch.getValue(), "Submitted value should match to expected");
        txbSearch.sendKeys(Keys.ENTER);
        Assert.assertTrue(txbSearch.state().waitForNotDisplayed(), "text field should disappear after the submit");
    }

    private void checkUnfocus(ITextBox txbSearch) {
        txbSearch.unfocus();
        Assert.assertFalse(isKeyboardShown(false), "Keyboard should not be shown when unfocus successful");
    }

    @SuppressWarnings("unchecked")
    private boolean isKeyboardShown(boolean expectedStateToWait) {
        boolean waitResult = AqualityServices.getConditionalWait()
                .waitFor(driver -> ((AndroidDriver<AndroidElement>)driver).isKeyboardShown() == expectedStateToWait,
                        AqualityServices.getConfiguration().getTimeoutConfiguration().getCommand(),
                        AqualityServices.getConfiguration().getTimeoutConfiguration().getPollingInterval().multipliedBy(10),
                        String.format("is keyboard shown condition should be %s", expectedStateToWait));
        return expectedStateToWait == waitResult;
    }
}
