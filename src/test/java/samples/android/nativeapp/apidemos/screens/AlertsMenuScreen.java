package samples.android.nativeapp.apidemos.screens;

import aquality.appium.mobile.elements.interfaces.IButton;
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
