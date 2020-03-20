package samples.android.nativeapp.apidemos.screens;

import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.AndroidScreen;
import org.openqa.selenium.By;

public class TwoButtonsAlert extends AndroidScreen {

    private static final By alertTitleLocator = By.id("android:id/alertTitle");

    private final ILabel lblAlertTitle = getElementFactory().getLabel(alertTitleLocator, "Alert title");
    private final IButton btnClose = getElementFactory().getButton(By.id("android:id/button1"), "Close alert dialog");

    public TwoButtonsAlert() {
        super(alertTitleLocator, "Two-buttons alert");
    }

    public String getAlertText() {
        return lblAlertTitle.getText();
    }

    public void close(){
        btnClose.click();
    }
}
