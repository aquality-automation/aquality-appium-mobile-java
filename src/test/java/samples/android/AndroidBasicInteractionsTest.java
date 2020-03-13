package samples.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.MobileModule;
import aquality.appium.mobile.elements.interfaces.ICheckBox;
import aquality.appium.mobile.elements.interfaces.IRadioButton;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import samples.android.apidemos.screens.*;
import testreport.ScreenshotListener;

@Listeners(ScreenshotListener.class)
public class AndroidBasicInteractionsTest {

    @BeforeClass
    public void setUp() {
        System.clearProperty("profile");
        AqualityServices.initInjector(new MobileModule(AqualityServices::getApplication));
    }

    @AfterClass
    public void tearDown() {
        AqualityServices.getApplication().quit();
    }


    @Test
    public void testSendKeys() {
        new MainMenuScreen().startSearch();
        InvokeSearchScreen searchScreen = new InvokeSearchScreen();
        Assert.assertTrue(searchScreen.isDisplayed(), searchScreen.getName() + " should be opened from the menu");
        String query = "Hello world!";
        searchScreen.submitSearch(query);
        Assert.assertEquals(searchScreen.getSearchResult(), query, "Search result don't match to entered query");
    }

    @Test
    public void testRadioButton() {
        ViewControlsScreen screen = openViewControlsScreen();
        IRadioButton button1 = screen.getRadioButton(1);
        Assert.assertFalse(button1.isChecked(), "RadioButton should not be checked initially");
        button1.click();
        Assert.assertTrue(button1.isChecked(), "RadioButton should be checked after click on it");
        screen.getRadioButton(2).click();
        Assert.assertFalse(button1.isChecked(),
                String.format("RadioButton %s should not be checked after click on another option", button1.getName()));
    }

    @Test
    public void testCheckBox() {
        ViewControlsScreen screen = openViewControlsScreen();
        ICheckBox checkBox1 = screen.getCheckBox(1);
        Assert.assertFalse(checkBox1.isChecked(), "Checkbox should not be checked initially");
        checkBox1.click();
        Assert.assertTrue(checkBox1.isChecked(), "Checkbox should be checked after first click on it");
        checkBox1.uncheck();
        Assert.assertFalse(checkBox1.isChecked(), "Checkbox should not be checked after uncheck");
        checkBox1.toggle();
        Assert.assertTrue(checkBox1.isChecked(), "Checkbox should be checked after toggle from unchecked state");
        checkBox1.toggle();
        Assert.assertFalse(checkBox1.isChecked(), "Checkbox should not be checked after toggle from checked state");
        screen.getCheckBox(2).check();
        Assert.assertFalse(checkBox1.isChecked(), "Checkbox should not be checked after checking other checkbox");
    }

    private ViewControlsScreen openViewControlsScreen() {
        ViewControlsScreen screen = new ViewControlsScreen();
        logStep(String.format("open %s screen", screen.getName()));
        new MainMenuScreen().openViewControls();
        Assert.assertTrue(screen.isDisplayed(), String.format("%s screen should be opened", screen.getName()));
        return screen;
    }

    @Test
    public void testOpensAlert() {

        logStep("Open the 'Alert Dialog' activity of the android app");
        new MainMenuScreen().openAlerts();

        logStep("Click button that opens a dialog");
        new AlertsMenuScreen().openTwoButtonsDialog();

        logStep("Check that the dialog is there");
        TwoButtonsAlert alertDialog = new TwoButtonsAlert();
        Assert.assertEquals(alertDialog.getAlertText(),
                "Lorem ipsum dolor sit aie consectetur adipiscing\nPlloaso mako nuto siwuf cakso dodtos anr koop.",
                "Alert text should match to expected");

        logStep("Close the dialog");
        alertDialog.close();
    }

    private void logStep(String step) {
        AqualityServices.getLogger().info(step);
    }
}