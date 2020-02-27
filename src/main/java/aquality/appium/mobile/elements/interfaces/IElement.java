package aquality.appium.mobile.elements.interfaces;

import aquality.appium.mobile.elements.ElementType;
import aquality.selenium.core.elements.ElementState;
import org.openqa.selenium.By;

public interface IElement extends aquality.selenium.core.elements.interfaces.IElement {

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
        return findChildElement(childLoc, null, elementType, ElementState.DISPLAYED);
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
}
