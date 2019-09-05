package aquality.appium.elements.interfaces;

import aquality.selenium.elements.interfaces.IElementStateProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebElement;

public interface IElement extends IParent {
    /**
     * Get clear Appium element
     *
     * @return Appium element
     */
    RemoteWebElement getElement();

    /**
     * Get clear Appium element
     *
     * @param timeout Timeout for waiting
     * @return Appium element
     */
    RemoteWebElement getElement(Long timeout);

    /**
     * Get element locator
     *
     * @return Element locator
     */
    By getLocator();

    /**
     * get element name
     * @return name
     */
    String getName();

    /**
     * Send keys.
     */
    void sendKeys(Keys key);

    /**
     * Click on the item.
     */
    void click();

    /**
     * Get the item text (inner text).
     *
     * @return Text of element
     */
    String getText();

    /**
     * Gets attribute value of the element.
     *
     * @param attr Attribute name
     * @return Attribute value
     */
    String getAttribute(String attr);

    /**
     * Provides ability to define of element's state (whether it is displayed, exists or not) and respective waiting functions
     * @return provider to define element's state
     */
    IElementStateProvider state();
}
