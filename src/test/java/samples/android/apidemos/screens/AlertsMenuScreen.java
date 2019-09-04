package samples.android.apidemos.screens;

import aquality.appium.elements.interfaces.IButton;
import aquality.appium.screens.AndroidScreen;
import org.openqa.selenium.By;

public class AlertsMenuScreen extends AndroidScreen {

    private final IButton btnTwoButtonsDialog =
            getElementFactory().getButton(By.id("io.appium.android.apis:id/two_buttons"), "Open two-buttons dialog");

    public AlertsMenuScreen() {
        super(By.id("io.appium.android.apis:id/screen"), "Alerts menu");
    }

    public void openTwoButtonsDialog(){
        btnTwoButtonsDialog.click();
    }
}
