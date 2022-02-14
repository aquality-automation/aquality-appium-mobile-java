package samples.android.nativeapp.apidemos.screens;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ICheckBox;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.elements.interfaces.IRadioButton;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class ViewControlsScreen extends AndroidScreen {

    public IRadioButton getRadioButton(int number) {
        return getElementFactory().getRadioButton(
                AppiumBy.accessibilityId(String.format("RadioButton %d", number)),
                String.valueOf(number));
    }

    public ICheckBox getCheckBox(int number) {
        return getElementFactory().getCheckBox(
                AppiumBy.accessibilityId(String.format("Checkbox %d", number)),
                String.valueOf(number));
    }

    private final IButton btnDisabled = getElementFactory().getButton(By.id("button_disabled"), "Disabled");

    public ViewControlsScreen() {
        super(By.id("android:id/content"), "View/Controls");
    }

    public void scrollToAllInsideScrollViewLabel() {
        lblAllInsideScrollView.getTouchActions().scrollToElement(SwipeDirection.DOWN);
    }

    public String getAllInsideScrollViewLabelText() {
        return lblAllInsideScrollView.getText();
    }

    public void scrollToDisabledButton() {
        btnDisabled.getTouchActions().scrollToElement(SwipeDirection.UP);
    }

    public boolean isDisabledButtonClickable() {
        return btnDisabled.state().isClickable();
    }

    private final ILabel lblAllInsideScrollView = getElementFactory().getLabel(
            AppiumBy.accessibilityId("(And all inside of a ScrollView!)"),
            "All inside of Scroll View");
}
