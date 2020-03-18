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
public class AndroidBasicInteractionsTest implements ITestCheckBox, ITestRadioButton {

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

    @Override
    public void openRadioButtonsScreen() {
        openViewControlsScreen();
    }

    @Override
    public IRadioButton getRadioButton(int number) {
        return new ViewControlsScreen().getRadioButton(number);
    }

    @Test
    public void testRadioButton() {
        ITestRadioButton.super.testRadioButton();
    }

    @Override
    public void openCheckBoxesScreen() {
        openViewControlsScreen();
    }

    @Override
    public ICheckBox getCheckBox(int number) {
        return new ViewControlsScreen().getCheckBox(number);
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