package samples.android.nativeapp.apidemos.screens;

import aquality.appium.mobile.elements.SwipeDirection;
import aquality.appium.mobile.elements.actions.SwipeActions;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ICheckBox;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.elements.interfaces.IRadioButton;
import aquality.appium.mobile.screens.AndroidScreen;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class ViewControlsScreen extends AndroidScreen {
    public ViewControlsScreen() {
        super(By.id("android:id/content"), "View/Controls");
    }

    public IRadioButton getRadioButton(int number) {
        return getElementFactory().getRadioButton(
                MobileBy.AccessibilityId(String.format("RadioButton %d", number)),
                String.valueOf(number));
    }

    public ICheckBox getCheckBox(int number) {
        return getElementFactory().getCheckBox(
                MobileBy.AccessibilityId(String.format("Checkbox %d", number)),
                String.valueOf(number));
    }

    private ILabel lblAllInsideScrollView() {
        return getElementFactory().getLabel(
                MobileBy.AccessibilityId("(And all inside of a ScrollView!)"),
                "All inside of Scroll View");
    }

    private IButton btnDisabled() {
        return getElementFactory().getButton(MobileBy.id("button_disabled"), "Disabled");
    }

    public void scrollToAllInsideScrollViewLabel() {
        SwipeActions.scrollToElement(lblAllInsideScrollView(), SwipeDirection.DOWN);
    }

    public String getAllInsideScrollViewLabelText() {
        return lblAllInsideScrollView().getText();
    }

    public void scrollToDisabledButton() {
        SwipeActions.scrollToElement(btnDisabled(), SwipeDirection.UP);
    }

    public boolean isDisabledButtonClickable() {
        return btnDisabled().state().isClickable();
    }
}
