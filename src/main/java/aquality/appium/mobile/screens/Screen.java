package aquality.appium.mobile.screens;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IElementFactory;
import aquality.appium.mobile.elements.interfaces.ILabel;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidArgumentException;

import java.time.Duration;

public abstract class Screen {

    /**
     * Locator for specified screen
     */
    protected final By locator;
    /**
     * Name of specified screen
     */
    protected final String name;

    /**
     * Constructor with parameters
     */
    protected Screen(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    protected abstract AppiumDriver getDriver();

    /**
     * Return screen state for screen locator
     *
     * @return True - screen is opened,
     * False - screen is not opened
     */
    public boolean isDisplayed() {
        return getFormLabel().state().waitForDisplayed();
    }

    /**
     * Return screen state for screen locator
     *
     * @param timeout timeout for action
     * @return True - screen is opened,
     * False - screen is not opened
     */
    public boolean isDisplayed(Duration timeout) {
        return getFormLabel().state().waitForDisplayed(timeout);
    }

    public Dimension getScreenSize() {
       return getFormLabel().getElement().getSize();
    }

    protected IElementFactory getElementFactory(){
        return AqualityServices.getElementFactory();
    }

    private ILabel getFormLabel(){
        return getElementFactory().getLabel(locator, name);
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
}
