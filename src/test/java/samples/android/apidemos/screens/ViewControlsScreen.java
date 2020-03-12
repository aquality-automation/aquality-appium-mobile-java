package samples.android.apidemos.screens;

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
}
