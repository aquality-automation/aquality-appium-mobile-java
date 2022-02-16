package aquality.appium.mobile.elements.interfaces;

import aquality.appium.mobile.elements.Attributes;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.actions.IElementTouchActions;
import aquality.selenium.core.elements.ElementState;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;

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
     * Gets attribute value of the element.
     *
     * @param attribute Attribute
     * @return Attribute value
     */
    default String getAttribute(Attributes attribute) {
        return getAttribute(attribute.toString());
    }

    /**
     * Gets the utility used to perform touch actions for element.
     *
     * @return instance of element touch actions.
     */
    IElementTouchActions getTouchActions();

    /**
     * Method returns central coordinates of an element.
     * @return The instance of the {@link org.openqa.selenium.Point}
     */
    Point getCenter();
}
