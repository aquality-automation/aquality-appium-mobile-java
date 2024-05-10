package samples.android.nativeapp;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.IMobileApplication;
import aquality.appium.mobile.application.MobileModule;
import aquality.appium.mobile.elements.interfaces.ICheckBox;
import aquality.appium.mobile.elements.interfaces.IRadioButton;
import aquality.selenium.core.configurations.ITimeoutConfiguration;
import io.appium.java_client.appmanagement.ApplicationState;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.util.RetryAnalyzerCount;
import samples.android.ITestCheckBox;
import samples.android.ITestRadioButton;
import samples.android.nativeapp.apidemos.ApplicationActivity;
import samples.android.nativeapp.apidemos.screens.*;
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
        Assert.assertTrue(screen.state().waitForDisplayed(), String.format("%s screen should be opened", screen.getName()));
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

    public static class RetryAnalyzer extends RetryAnalyzerCount {
        public RetryAnalyzer() {}
        @Override
        public boolean retryMethod(ITestResult result) {
            return !result.isSuccess() && result.getThrowable() instanceof AssertionError;
        }
    }

    @Test(retryAnalyzer = RetryAnalyzer.class, successPercentage = 50)
    public void testApplicationManagement() {
        IMobileApplication app = AqualityServices.getApplication();
        Assert.assertThrows(IllegalArgumentException.class, () -> AqualityServices.getApplicationProfile().getDriverSettings().getBundleId());
        ApplicationActivity.SEARCH.open();
        String id = app.getId();
        app.background();
        Assert.assertEquals(app.getState(id), ApplicationState.RUNNING_IN_BACKGROUND);
        app.activate(id);
        Assert.assertEquals(app.getState(id), ApplicationState.RUNNING_IN_FOREGROUND);
        Assert.assertTrue(app.terminate());
        Assert.assertTrue(app.isStarted());
        Assert.assertEquals(app.getState(id), ApplicationState.NOT_RUNNING);
        app.activate(id, AqualityServices.get(ITimeoutConfiguration.class).getCondition());
        Assert.assertEquals(app.getState(id), ApplicationState.RUNNING_IN_FOREGROUND);
        Assert.assertTrue(app.remove());
        Assert.assertEquals(app.getState(id), ApplicationState.NOT_INSTALLED);
        Assert.assertFalse(app.terminate(id));
        app.install();
        Assert.assertEquals(app.getState(id), ApplicationState.NOT_RUNNING);
    }

    @Test
    public void testSendKeys() {
        InvokeSearchScreen searchScreen = ApplicationActivity.SEARCH.open();
        Assert.assertTrue(searchScreen.state().waitForDisplayed(), String.format("%s should be opened", searchScreen.getName()));
        String query = "Hello world!";
        searchScreen.submitSearch(query);
        Assert.assertEquals(searchScreen.getSearchResult(), query, "Search result don't match to entered query");
    }

    @Test
    public void testSaveAndCompareScreenDump()
    {
        InvokeSearchScreen searchScreen = ApplicationActivity.SEARCH.open();
        Assert.assertTrue(searchScreen.state().waitForDisplayed(), String.format("%s should be opened", searchScreen.getName()));
        final String customDumpName = String.format("my dump of %s", searchScreen.getName());
        searchScreen.dump().save(customDumpName);
        Assert.assertEquals(searchScreen.dump().compare(customDumpName), 0, "Current screen should have no visual difference comparing to just saved dump");
        final String query = "Hello world!";
        searchScreen.typeQuery(query);
        Assert.assertTrue(searchScreen.dump().compare() > 0, "Current screen after the search should have visual difference comparing to dump saved");
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

    @Test
    public void testVerticalSwipeToElement() {
        ViewControlsScreen viewControlsScreen = new ViewControlsScreen();
        openRadioButtonsScreen();
        viewControlsScreen.scrollToAllInsideScrollViewLabel();
        Assert.assertEquals(
                viewControlsScreen.getAllInsideScrollViewLabelText(),
                "(And all inside of a ScrollView!)",
                "Label text does not match expected");
        viewControlsScreen.scrollToDisabledButton();
        Assert.assertFalse(viewControlsScreen.isDisabledButtonClickable());
    }

    @Test
    public void testHorizontalSwipeToElement() {
        ViewTabsScrollableScreen viewTabsScrollableScreen = ApplicationActivity.VIEW_TABS_SCROLLABLE.open();
        Assert.assertTrue(viewTabsScrollableScreen.state().waitForDisplayed(),
                String.format("%s screen should be opened", viewTabsScrollableScreen.getName()));
        viewTabsScrollableScreen.swipeTabWithLongPress(4, 1);
        viewTabsScrollableScreen.selectTab(7);
        Assert.assertEquals(
                viewTabsScrollableScreen.getTabContentText(7),
                "Content for tab with tag Tab 7",
                "Label text does not match expected");
        viewTabsScrollableScreen.swipeTab(5, 7);
        viewTabsScrollableScreen.selectTab(4);
        Assert.assertEquals(
                viewTabsScrollableScreen.getTabContentText(4),
                "Content for tab with tag Tab 4",
                "Label text does not match expected");
    }

    private void logStep(String step) {
        AqualityServices.getLogger().info(step);
    }
}