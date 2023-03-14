package aquality.appium.mobile.elements;

import aquality.appium.mobile.application.Application;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.elements.actions.ElementTouchActions;
import aquality.appium.mobile.elements.actions.IElementTouchActions;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.appium.mobile.elements.interfaces.IElementFactory;
import aquality.selenium.core.configurations.IElementCacheConfiguration;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.interfaces.IElementFinder;
import aquality.selenium.core.localization.ILocalizationManager;
import aquality.selenium.core.localization.ILocalizedLogger;
import aquality.selenium.core.utilities.IElementActionRetrier;
import aquality.selenium.core.visualization.IImageComparator;
import aquality.selenium.core.waitings.IConditionalWait;
import org.openqa.selenium.*;

/**
 * Abstract class, describing wrapper of Appium element.
 */
public abstract class Element extends aquality.selenium.core.elements.Element implements IElement {
    /**
     * The main constructor
     *
     * @param loc     By Locator
     * @param nameOf  Output in logs
     * @param stateOf desired ElementState
     */
    protected Element(final By loc, final String nameOf, final ElementState stateOf) {
        super(loc, nameOf, stateOf);
    }

    @Override
    protected Application getApplication() {
        return AqualityServices.getApplication();
    }

    @Override
    protected IElementFactory getElementFactory() {
        return AqualityServices.getElementFactory();
    }

    @Override
    protected IElementFinder getElementFinder() {
        return AqualityServices.get(IElementFinder.class);
    }

    @Override
    protected IImageComparator getImageComparator() {
        return AqualityServices.get(IImageComparator.class);
    }

    @Override
    protected IElementCacheConfiguration getElementCacheConfiguration() {
        return AqualityServices.get(IElementCacheConfiguration.class);
    }

    @Override
    protected IElementActionRetrier getElementActionRetrier() {
        return AqualityServices.get(IElementActionRetrier.class);
    }

    @Override
    protected ILocalizedLogger getLocalizedLogger() {
        return AqualityServices.getLocalizedLogger();
    }

    protected ILocalizationManager getLocalizationManager() {
        return AqualityServices.get(ILocalizationManager.class);
    }

    @Override
    protected IConditionalWait getConditionalWait() {
        return AqualityServices.getConditionalWait();
    }

    @Override
    public <T extends IElement> T findChildElement(By childLoc, String name, ElementType elementType, ElementState state) {
        return getElementFactory().findChildElement(this, childLoc, name, elementType, state);
    }

    @Override
    public void sendKeys(Keys key) {
        logElementAction("loc.text.sending.keys", Keys.class.getSimpleName().concat(".").concat(key.name()));
        doWithRetry(() -> getElement().sendKeys(key));
    }

    @Override
    public IElementTouchActions getTouchActions() {
        return new ElementTouchActions(this);
    }

    @Override
    public Point getCenter() {
        logElementAction("loc.get.center");
        WebElement element = getElement();
        Point upperLeft = element.getLocation();
        Dimension dimensions = element.getSize();
        Point center = new Point(upperLeft.getX() + dimensions.getWidth() / 2,
                upperLeft.getY() + dimensions.getHeight() / 2);
        logElementAction("loc.center.value", center.getX(), center.getY());
        return center;
    }
}
