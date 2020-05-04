package samples.android.nativeapp;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.MobileModule;
import aquality.appium.mobile.elements.interfaces.ICheckBox;
import aquality.appium.mobile.elements.interfaces.IRadioButton;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import samples.android.ITestCheckBox;
import samples.android.ITestRadioButton;
import samples.android.nativeapp.apidemos.ApplicationActivity;
import samples.android.nativeapp.apidemos.screens.AlertsMenuScreen;
import samples.android.nativeapp.apidemos.screens.InvokeSearchScreen;
import samples.android.nativeapp.apidemos.screens.TwoButtonsAlert;
import samples.android.nativeapp.apidemos.screens.ViewControlsScreen;
import testreport.ScreenshotListener;

@Listeners(ScreenshotListener.class)
public class AndroidBasicInteractionsTest implements ITestCheckBox, ITestRadioButton {

    @Override
    public void openRadioButtonsScreen() {
        openViewControlsScreen();
    }

    @Override
    public IRadioButton getRadioButton(int number) {
        return ((ViewControlsScreen) ApplicationActivity.VIEW_CONTROLS.getScreen()).getRadioButton(number);
    }

    @Override
    public void openCheckBoxesScreen() {
        openViewControlsScreen();
    }

    private void openViewControlsScreen() {
        ViewControlsScreen screen = ApplicationActivity.VIEW_CONTROLS.open();
        Assert.assertTrue(screen.state().isDisplayed(), String.format("%s screen should be opened", screen.getName()));
    }

    @Override
    public ICheckBox getCheckBox(int number) {
        return ((ViewControlsScreen) ApplicationActivity.VIEW_CONTROLS.getScreen()).getCheckBox(number);
    }

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
        InvokeSearchScreen searchScreen = ApplicationActivity.SEARCH.open();
        Assert.assertTrue(searchScreen.state().isDisplayed(), String.format("%s should be opened", searchScreen.getName()));
        String query = "Hello world!";
        searchScreen.submitSearch(query);
        Assert.assertEquals(searchScreen.getSearchResult(), query, "Search result don't match to entered query");
    }

    @Test
    public void testRadioButton() {
        ITestRadioButton.super.testRadioButton();
    }

    @Test
    public void testOpensAlert() {
        AlertsMenuScreen menuScreen = ApplicationActivity.ALERT_DIALOGS.open();

        logStep("Click button that opens a dialog");
        menuScreen.openTwoButtonsDialog();

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