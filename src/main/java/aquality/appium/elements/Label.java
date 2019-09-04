package aquality.appium.elements;

import aquality.appium.elements.interfaces.ILabel;
import org.openqa.selenium.By;

public class Label extends Element implements ILabel {

    protected Label(final By locator, final String name) {
        super(locator, name);
    }

    protected String getElementType() {
        return getLocManager().getValue("loc.label");
    }

}
