package aquality.appium.screens;

import aquality.appium.elements.interfaces.IElementFactory;
import aquality.appium.logger.Logger;
import org.openqa.selenium.By;

public class Screen {

    private static final Logger logger = Logger.getInstance();
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
        this.elementFactory = null;// new ElementFactory();
    }

    /**
     * Return screen state for screen locator
     *
     * @return True - screen is opened,
     * False - screen is not opened
     */
    public boolean isDisplayed() {
        return getElementFactory().getLabel(locator, name).state().waitForDisplayed();
    }

    /**
     * Return screen state for screen locator
     *
     * @param timeout timeout for action
     * @return True - screen is opened,
     * False - screen is not opened
     */
    public boolean isScreenDisplayed(Long timeout) {
        return getElementFactory().getLabel(locator, name).state().waitForDisplayed(timeout);
    }

// todo: define if it is needed
//    /**
//     * Scroll screen without scrolling entire page
//     * @param x horizontal coordinate
//     * @param y vertical coordinate
//     */
//    public void scrollBy(int x, int y) {
//        getElementFactory().getLabel(locator, name).getJsActions().scrollBy(x, y);
//    }
//
//    public Dimension getScreenSize() {
//        return getElementFactory().getLabel(locator, name).getElement().getSize();
//    }
//

    protected IElementFactory getElementFactory(){
        return elementFactory;
    }
}
