package aquality.appium.elements;

import aquality.appium.elements.interfaces.IButton;
import org.openqa.selenium.By;

/**
 * Class describing element button
 */
public class Button extends Element implements IButton {

    protected Button(final By locator, final String name) {
        super(locator, name);
    }

    protected String getElementType() {
        return getLocManager().getValue("loc.button");
    }
}
