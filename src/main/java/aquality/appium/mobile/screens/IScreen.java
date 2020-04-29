package aquality.appium.mobile.screens;

import aquality.selenium.core.elements.interfaces.IElementStateProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

public interface IScreen {
    /**
     * Locator for specified screen
     */
    By getLocator();

    /**
     * Name of specified screen
     */
    String getName();

    /**
     * Size of the element described by screen locator.
     */
    Dimension getSize();

    /**
     * Provides ability to define of element's state (whether it is displayed, exists or not) and respective waiting functions
     *
     * @return provider to define element's state
     */
    IElementStateProvider state();
}
