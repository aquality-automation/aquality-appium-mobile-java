package aquality.appium.elements;

import aquality.appium.elements.interfaces.IRadioButton;
import org.openqa.selenium.By;

/**
 * Class describing the Radiobutton
 */
public class RadioButton extends Element implements IRadioButton {

    protected RadioButton(final By locator, final String name) {
        super(locator, name);
    }

    protected String getElementType() {
        return getLocManager().getValue("loc.radio");
    }
}
