package aquality.appium.mobile.elements.interfaces;

import aquality.appium.mobile.elements.Attributes;
import aquality.appium.mobile.elements.ElementType;
import aquality.selenium.core.elements.ElementState;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;

public interface IElement extends aquality.selenium.core.elements.interfaces.IElement {

    /**
     * Send keys.
     *
     * @param key key for sending.
     */
    void sendKeys(Keys key);

    /**
     * Find an element in the parent element
     *
     * @param childLoc    child element locator
     * @param name        output name in logs
     * @param elementType type of the element to be obtained
     * @param state       visibility state of target element
     * @param <T>         type of the element to be obtained
     * @return found child element
     */
    <T extends IElement> T findChildElement(By childLoc, String name, ElementType elementType, ElementState state);

    /**
     * Find an element in the parent element with default name.
     *
     * @param childLoc    child element locator
     * @param elementType type of the element to be obtained
     * @param state       visibility state of target element
     * @param <T>         type of the element to be obtained
     * @return found child element
     */
    default <T extends IElement> T findChildElement(By childLoc, ElementType elementType, ElementState state) {
        return findChildElement(childLoc, null, elementType, state);
    }

    /**
     * Find an element in the parent element with DISPLAYED state and default name.
     *
     * @param childLoc    child element locator
     * @param elementType type of the element to be obtained
     * @param <T>         type of the element to be obtained
     * @return found child element
     */
    default <T extends IElement> T findChildElement(By childLoc, ElementType elementType) {
        return findChildElement(childLoc, elementType, ElementState.DISPLAYED);
    }

    /**
     * Find an element in the parent element with DISPLAYED state
     *
     * @param childLoc    child element locator
     * @param name        output name in logs
     * @param elementType type of the element to be obtained
     * @param <T>         type of the element to be obtained
     * @return found child element
     */
    default <T extends IElement> T findChildElement(By childLoc, String name, ElementType elementType) {
        return findChildElement(childLoc, name, elementType, ElementState.DISPLAYED);
    }

    /**
     * Gets current mobile element by specified {@link #getLocator()}
     * Default timeout is provided in {@link aquality.selenium.core.configurations.ITimeoutConfiguration}
     * {@link org.openqa.selenium.NoSuchElementException} throws if element not found
     *
     * @return instance of {@link MobileElement} if found.
     */
    @Override
    default MobileElement getElement() {
        return getElement(null);
    }

    /**
     * Gets current mobile element by specified {@link #getLocator()}
     * {@link org.openqa.selenium.NoSuchElementException} throws if element not found
     *
     * @param timeout Timeout for waiting
     * @return instance of {@link MobileElement} if found.
     */
    @Override
    MobileElement getElement(Duration timeout);

    /**
     * Gets attribute value of the element.
     *
     * @param attribute Attribute
     * @return Attribute value
     */
    default String getAttribute(Attributes attribute) {
        return getAttribute(attribute.toString());
    }
}
