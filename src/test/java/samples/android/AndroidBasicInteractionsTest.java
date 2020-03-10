package samples.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.MobileModule;
import org.testng.Assert;
import samples.android.apidemos.screens.AlertsMenuScreen;
import samples.android.apidemos.screens.InvokeSearchScreen;
import samples.android.apidemos.screens.MainMenuScreen;
import samples.android.apidemos.screens.TwoButtonsAlert;

//@Listeners(ScreenshotListener.class)
public class AndroidBasicInteractionsTest {

    //@BeforeClass
    public void setUp() {
        System.clearProperty("profile");
        AqualityServices.initInjector(new MobileModule(AqualityServices::getApplication));
    }

    //@AfterClass
    public void tearDown() {
        AqualityServices.getApplication().quit();
    }


    //@Test
    public void testSendKeys() {
        new MainMenuScreen().startSearch();
        InvokeSearchScreen searchScreen = new InvokeSearchScreen();
        Assert.assertTrue(searchScreen.isDisplayed(), searchScreen.getName() + " should be opened from the menu");
        String query = "Hello world!";
        searchScreen.submitSearch(query);
        Assert.assertEquals(searchScreen.getSearchResult(), query, "Search result don't match to entered query");
    }

    //@Test
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