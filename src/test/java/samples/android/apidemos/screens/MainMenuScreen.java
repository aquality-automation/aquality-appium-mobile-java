package samples.android.apidemos.screens;

import aquality.appium.mobile.screens.AndroidScreen;
import io.appium.java_client.android.Activity;
import org.openqa.selenium.By;

public class MainMenuScreen extends AndroidScreen {

    private final String SEARCH_ACTIVITY = ".app.SearchInvoke";
    private final String ALERT_DIALOG_ACTIVITY = ".app.AlertDialogSamples";
    private final String VIEW_CONTROLS_ACTIVITY = ".view.Controls1";
    private final String PACKAGE = "io.appium.android.apis";

    public MainMenuScreen() {
        super(By.id("android:id/content"), "Main menu");
    }

    public void startSearch() {
        startActivity(SEARCH_ACTIVITY);
    }

    public void openAlerts() {
        startActivity(ALERT_DIALOG_ACTIVITY);
    }

    public void openViewControls() {
        startActivity(VIEW_CONTROLS_ACTIVITY);
    }

    private void startActivity(String activity) {
        getDriver().startActivity(new Activity(PACKAGE, activity));
    }
}
