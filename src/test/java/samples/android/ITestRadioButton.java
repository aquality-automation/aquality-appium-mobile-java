package samples.android;

import aquality.appium.mobile.elements.interfaces.IRadioButton;
import org.testng.Assert;

public interface ITestRadioButton {

    void openRadioButtonsScreen();

    IRadioButton getRadioButton(int number);

    default void testRadioButton() {
        openRadioButtonsScreen();
        IRadioButton button1 = getRadioButton(1);
        Assert.assertFalse(button1.isChecked(), "RadioButton should not be checked initially");
        button1.click();
        Assert.assertTrue(button1.isChecked(), "RadioButton should be checked after click on it");
        getRadioButton(2).click();
        Assert.assertFalse(button1.isChecked(),
                String.format("RadioButton %s should not be checked after click on another option", button1.getName()));
    }
}
