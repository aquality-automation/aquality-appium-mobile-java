package samples.android.apidemos.screens;

import aquality.appium.screens.AndroidScreen;
import io.appium.java_client.android.Activity;
import org.openqa.selenium.By;

public class MainMenuScreen extends AndroidScreen {

    private final String SEARCH_ACTIVITY = ".app.SearchInvoke";
    private final String ALERT_DIALOG_ACTIVITY = ".app.AlertDialogSamples";
    private final String PACKAGE = "io.appium.android.apis";

    public MainMenuScreen() {
        super(By.id("android:id/content"), "Main menu");
    }

    public void startSearch() {
        getDriver().startActivity(new Activity(PACKAGE, SEARCH_ACTIVITY));
    }

    public void openAlerts() {
        getDriver().startActivity(new Activity(PACKAGE, ALERT_DIALOG_ACTIVITY));
    }
}
