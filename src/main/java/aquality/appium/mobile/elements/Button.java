package aquality.appium.mobile.elements;

import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.selenium.core.elements.ElementState;
import org.openqa.selenium.By;

/**
 * The class describing the button
 */
public class Button extends Element implements IButton {

    protected Button(final By locator, final String name, final ElementState state) {
        super(locator, name, state);
    }

    protected String getElementType() {
        return getLocalizationManager().getLocalizedMessage("loc.button");
    }
}
