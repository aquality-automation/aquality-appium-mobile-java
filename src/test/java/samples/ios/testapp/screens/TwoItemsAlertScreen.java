package samples.ios.testapp.screens;

import aquality.appium.elements.interfaces.IButton;
import aquality.appium.elements.interfaces.ILabel;
import aquality.appium.screens.IOSScreen;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class TwoItemsAlertScreen extends IOSScreen {

    private static final By TITLE_LOCATOR = MobileBy.AccessibilityId("Cool title");
    private final ILabel lblAlertTitle = getElementFactory().getLabel(MobileBy.AccessibilityId("Cool title"), "Title");
    private final IButton btnOk = getElementFactory().getButton(MobileBy.AccessibilityId("OK"), "OK");

    public TwoItemsAlertScreen() {
        super(TITLE_LOCATOR, "Alert screen");
    }

    public String getTitle() {
        return lblAlertTitle.getText();
    }

    public void acceptAlert() {
        btnOk.click();
    }
}
