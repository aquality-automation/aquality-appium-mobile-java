package aquality.appium.mobile.elements;

import aquality.appium.mobile.elements.interfaces.ILink;
import aquality.selenium.core.elements.ElementState;
import org.openqa.selenium.By;

/**
 * The class that describes the link
 */
public class Link extends Element implements ILink {

    protected Link(final By locator, final String name, final ElementState state) {
        super(locator, name, state);
    }

    protected String getElementType() {
        return getLocalizationManager().getLocalizedMessage("loc.link");
    }

    @Override
    public String getHref() {
        return getAttribute(Attributes.HREF);
    }
}
