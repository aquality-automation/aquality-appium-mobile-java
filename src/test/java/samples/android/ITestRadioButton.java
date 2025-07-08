package samples.android;

import aquality.appium.mobile.elements.interfaces.IRadioButton;
import org.testng.Assert;

public interface ITestRadioButton {

    void openRadioButtonsScreen();

    IRadioButton getRadioButton(int number);

    default void testRadioButton() {
        openRadioButtonsScreen();
        IRadioButton button2 = getRadioButton(2);
        Assert.assertFalse(button2.isChecked(), "RadioButton should not be checked initially");
        button2.click();
        Assert.assertTrue(button2.isChecked(), "RadioButton should be checked after click on it");
        getRadioButton(1).click();
        Assert.assertFalse(button2.isChecked(),
                String.format("RadioButton %s should not be checked after click on another option", button2.getName()));
    }
}
