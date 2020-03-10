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

import java.time.Duration;

public abstract class Screen {

    private final By locator;
    private final String name;
    private final ILabel screenLabel;

    /**
     * Constructor with parameters
     */
    protected Screen(By locator, String name) {
        this.locator = locator;
        this.name = name;
        screenLabel = getElementFactory().getLabel(locator, name);
    }

    protected abstract AppiumDriver getDriver();

    /**
     * Return screen state for screen locator
     *
     * @return True - screen is opened,
     * False - screen is not opened
     */
    public boolean isDisplayed() {
        return isDisplayed(null);
    }

    /**
     * Return screen state for screen locator
     *
     * @param timeout timeout for action
     * @return True - screen is opened,
     * False - screen is not opened
     */
    public boolean isDisplayed(Duration timeout) {
        return screenLabel.state().waitForDisplayed(timeout);
    }

    public Dimension getSize() {
       return screenLabel.getElement().getSize();
    }

    protected IElementFactory getElementFactory(){
        return AqualityServices.getElementFactory();
    }

    void ensureApplicationPlatformCorrect(PlatformName targetPlatform) {
        PlatformName currentPlatform = AqualityServices.getApplication().getPlatformName();
        if(targetPlatform != currentPlatform) {
            throw new InvalidArgumentException(String.format(
                    "Cannot perform this operation. Current platform %s don't match to target %s",
                    currentPlatform,
                    targetPlatform));
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
     * Provides ability to define of element's state (whether it is displayed, exists or not) and respective waiting functions
     *
     * @return provider to define element's state
     */
    public IElementStateProvider state() {
        return screenLabel.state();
    }
}
