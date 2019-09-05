package aquality.appium.screens;

import aquality.appium.application.ApplicationManager;
import aquality.appium.application.PlatformName;
import aquality.appium.elements.ElementFactory;
import aquality.appium.elements.interfaces.IElementFactory;
import aquality.appium.elements.interfaces.ILabel;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidArgumentException;

public class Screen {

    /**
     * Locator for specified screen
     */
    protected final By locator;
    /**
     * Name of specified screen
     */
    protected final String name;

    private final IElementFactory elementFactory;

    /**
     * Constructor with parameters
     */
    protected Screen(By locator, String name) {
        this.locator = locator;
        this.name = name;
        this.elementFactory = new ElementFactory();
    }

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
    public boolean isDisplayed(Long timeout) {
        return getFormLabel().state().waitForDisplayed(timeout);
    }

    public Dimension getScreenSize() {
       return getFormLabel().getElement().getSize();
    }

    protected IElementFactory getElementFactory(){
        return elementFactory;
    }

    private ILabel getFormLabel(){
        return getElementFactory().getLabel(locator, name);
    }

    void ensureApplicationPlatformCorrect(PlatformName targetPlatform) {
        PlatformName currentPlatform = ApplicationManager.getApplication().getPlatformName();
        if(targetPlatform != currentPlatform) {
            throw new InvalidArgumentException(String.format(
                    "Cannot perform this operation. Current platform %s don't match to target %s",
                    currentPlatform,
                    targetPlatform));
        }
    }
}
