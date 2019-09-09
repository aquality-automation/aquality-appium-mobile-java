package samples.ios.testapp.screens;

import aquality.appium.elements.interfaces.IButton;
import aquality.appium.elements.interfaces.ITextBox;
import aquality.appium.screens.IOSScreen;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class MainScreen extends IOSScreen {

    private static final By FIRST_TEXT_FIELD_LOCATOR = MobileBy.AccessibilityId("TextField1");
    private final ITextBox txbFirstTextField = getElementFactory().getTextBox(FIRST_TEXT_FIELD_LOCATOR, "First text field");
    private final IButton btnShowAlert = getElementFactory().getButton(MobileBy.AccessibilityId("show alert"), "Show alert");

    public MainScreen() {
        super(FIRST_TEXT_FIELD_LOCATOR, "Main screen");
    }

    public String getValueFromFirstTextBox() {
        return txbFirstTextField.getText();
    }

    public void enterTextToFirstField(String value) {
        txbFirstTextField.typeSecret(value);
    }

    public void tapShowAlert() {
        btnShowAlert.click();
    }
}
