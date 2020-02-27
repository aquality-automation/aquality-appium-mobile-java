package aquality.appium.mobile.elements;

import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.selenium.core.elements.ElementState;
import org.openqa.selenium.By;

/**
 * The class that describes the label
 */
public class Label extends Element implements ILabel {

    protected Label(final By locator, final String name, final ElementState state) {
        super(locator, name, state);
    }

    protected String getElementType() {
        return getLocalizationManager().getLocalizedMessage("loc.label");
    }
}
