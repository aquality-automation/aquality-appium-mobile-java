package aquality.appium.mobile.screens;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IElementFactory;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.selenium.core.elements.interfaces.IElementStateProvider;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidArgumentException;

public abstract class Screen<T extends AppiumDriver> implements IScreen {

    private final By locator;
    private final String name;
    private final ILabel screenLabel;

    /**
     * Constructor with parameters
     */
    protected Screen(By locator, String name) {
        this.locator = locator;
        this.name = name;
        this.screenLabel = getElementFactory().getLabel(locator, name);
    }

    protected IElementFactory getElementFactory(){
        return AqualityServices.getElementFactory();
    }

    @SuppressWarnings("unchecked")
    protected T getDriver() {
        ensureApplicationPlatformCorrect(getPlatform());
        return (T) AqualityServices.getApplication().getDriver();
    }

    protected abstract PlatformName getPlatform();

    private void ensureApplicationPlatformCorrect(PlatformName targetPlatform) {
        PlatformName currentPlatform = AqualityServices.getApplication().getPlatformName();
        if (targetPlatform != currentPlatform) {
            throw new InvalidArgumentException(String.format(
                    "Cannot perform this operation. Current platform %s don't match to target %s",
                    currentPlatform, targetPlatform));
        }
    }

    /**
     * Locator for specified screen
     */
    public By getLocator() {
        return locator;
    }

    /**
     * Name of specified screen
     */
    public String getName() {
        return name;
    }

    /**
     * Size of the element described by screen locator.
     */
    public Dimension getSize() {
       return screenLabel.getElement().getSize();
    }

    /**
     * Provides ability to define of element's state (whether it is displayed, exists or not) and respective waiting functions
     *
     * @return provider to define element's state
     */
    public IElementStateProvider state() {
        return screenLabel.state();
    }
}
