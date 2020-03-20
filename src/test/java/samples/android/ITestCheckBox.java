package samples.android;

import aquality.appium.mobile.elements.interfaces.ICheckBox;
import org.testng.Assert;

public interface ITestCheckBox {

    void openCheckBoxesScreen();

    ICheckBox getCheckBox(int number);

    default void testCheckBox() {
        openCheckBoxesScreen();
        ICheckBox checkBox1 = getCheckBox(1);
        Assert.assertFalse(checkBox1.isChecked(), "Checkbox should not be checked initially");
        checkBox1.click();
        Assert.assertTrue(checkBox1.isChecked(), "Checkbox should be checked after first click on it");
        checkBox1.uncheck();
        Assert.assertFalse(checkBox1.isChecked(), "Checkbox should not be checked after uncheck");
        checkBox1.toggle();
        Assert.assertTrue(checkBox1.isChecked(), "Checkbox should be checked after toggle from unchecked state");
        checkBox1.toggle();
        Assert.assertFalse(checkBox1.isChecked(), "Checkbox should not be checked after toggle from checked state");
        getCheckBox(2).check();
        Assert.assertFalse(checkBox1.isChecked(), "Checkbox should not be checked after checking other checkbox");
    }
}
