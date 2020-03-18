package samples.android.nativeapp.apidemos.screens;

import aquality.appium.mobile.elements.interfaces.ICheckBox;
import aquality.appium.mobile.elements.interfaces.IRadioButton;
import aquality.appium.mobile.screens.AndroidScreen;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class ViewControlsScreen extends AndroidScreen {
    public ViewControlsScreen() {
        super(By.id("android:id/content"), "View/Controls");
    }

    public IRadioButton getRadioButton(int number){
        return getElementFactory().getRadioButton(
                MobileBy.AccessibilityId(String.format("RadioButton %d", number)),
                String.valueOf(number));
    }

    public ICheckBox getCheckBox(int number){
        return getElementFactory().getCheckBox(
                MobileBy.AccessibilityId(String.format("Checkbox %d", number)),
                String.valueOf(number));
    }
}
