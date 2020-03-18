package aquality.appium.mobile.elements;

import aquality.appium.mobile.elements.interfaces.IRadioButton;
import aquality.selenium.core.elements.ElementState;
import org.openqa.selenium.By;

/**
 * Class describing the Radiobutton
 */
public class RadioButton extends CheckableElement implements IRadioButton {

    protected RadioButton(final By locator, final String name, final ElementState state) {
        super(locator, name, state);
    }

    protected String getElementType() {
        return getLocalizationManager().getLocalizedMessage("loc.radio");
    }
}
