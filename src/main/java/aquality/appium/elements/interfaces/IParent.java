package aquality.appium.elements.interfaces;

import aquality.appium.elements.ElementType;
import org.openqa.selenium.By;

public interface IParent {

    /**
     * Find an element in the parent element
     *
     * @param childLoc Child element locator
     * @param type the type of the element to be obtained

     * @return child element
     */
    <T extends IElement> T findChildElement(By childLoc, ElementType type);

    /**
     * Find an element in the parent element
     *
     * @param childLoc Child element locator
     * @param clazz class or interface of the element to be obtained
     * @return found child element
     */
    <T extends IElement> T findChildElement(By childLoc, Class<? extends IElement> clazz);

    /**
     * Find an element in the parent element
     *
     * @param childLoc Child element locator
     * @param supplier required element's supplier
     * @return found child element
     */
    <T extends IElement> T findChildElement(By childLoc, IElementSupplier<T> supplier);
}
