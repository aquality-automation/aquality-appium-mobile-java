package samples.android;

import aquality.appium.application.ApplicationManager;
import aquality.selenium.logger.Logger;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import samples.android.apidemos.screens.AlertsMenuScreen;
import samples.android.apidemos.screens.InvokeSearchScreen;
import samples.android.apidemos.screens.MainMenuScreen;
import samples.android.apidemos.screens.TwoButtonsAlert;

public class AndroidBasicInteractionsTest {
    private AndroidDriver<?> driver;
    private final String SEARCH_ACTIVITY = ".app.SearchInvoke";
    private final String ALERT_DIALOG_ACTIVITY = ".app.AlertDialogSamples";
    private final String PACKAGE = "io.appium.android.apis";

    @BeforeClass
    public void setUp() {
        driver = (AndroidDriver<?>) ApplicationManager.getApplication().getDriver();
    }

    @AfterClass
    public void tearDown() {
        ApplicationManager.getApplication().quit();
    }


    @Test()
    public void testSendKeys() {
        new MainMenuScreen().startSearch();
        InvokeSearchScreen searchScreen = new InvokeSearchScreen();
        String query = "Hello world!";
        searchScreen.submitSearch(query);
        Assert.assertEquals(searchScreen.getSearchResult(), query, "Search result don't match to entered query");
    }

    @Test
    public void testOpensAlert() {

        Logger.getInstance().info("Open the 'Alert Dialog' activity of the android app");
        new MainMenuScreen().openAlerts();

        Logger.getInstance().info("Click button that opens a dialog");
        new AlertsMenuScreen().openTwoButtonsDialog();

        Logger.getInstance().info("Check that the dialog is there");
        TwoButtonsAlert alertDialog = new TwoButtonsAlert();
        Assert.assertEquals(alertDialog.getAlertText(),
                "Lorem ipsum dolor sit aie consectetur adipiscing\nPlloaso mako nuto siwuf cakso dodtos anr koop.",
                "Alert text should match to expected");

        Logger.getInstance().info("Close the dialog");
        alertDialog.closeDialog();
    }
}