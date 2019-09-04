package aquality.appium.elements.interfaces;

import aquality.appium.elements.ElementType;
import aquality.selenium.elements.ElementsCount;
import org.openqa.selenium.By;

import java.util.List;

public interface IElementFactory {    
    
    IButton getButton(By locator, String name);

    ICheckBox getCheckBox(By locator, String name);

    IComboBox getComboBox(By locator, String name);

    ILabel getLabel(By locator, String name);

    ILink getLink(By locator, String name);

    IRadioButton getRadioButton(By locator, String name);

    ITextBox getTextBox(By locator, String name);

    <T extends IElement> T getCustomElement(IElementSupplier<T> supplier, By locator, String name);

    /**
     * Find an element in the parent element
     *
     * @param childLoc Child element locator
     * @param clazz class or interface of the element to be obtained
     * @param parentElement parent element for relative search of child element
     * @return found child element
     */
    <T extends IElement> T findChildElement(IElement parentElement, By childLoc, Class<? extends IElement> clazz);

    /**
     * Find an element in the parent element
     *
     * @param childLoc Child element locator
     * @param supplier required element's supplier
     * @param parentElement parent element for relative search of child element
     * @return found child element
     */
    <T extends IElement> T findChildElement(IElement parentElement, By childLoc, IElementSupplier<T> supplier);

    /**
     * Find an element in the parent element
     *
     * @param childLoc Child element locator
     * @param type the type of the element to be obtained
     * @param parentElement parent element for relative search of child element

     * @return found child element

     */
    <T extends IElement> T findChildElement(IElement parentElement, By childLoc, ElementType type);

    /**
     * Find list of elements
     *
     * @param locator Elements selector
     * @param supplier required elements' supplier
     * @param count type of expected count of elements

     * @return list of elements
     */
    <T extends IElement> List<T> findElements(By locator, IElementSupplier<T> supplier, ElementsCount count);

    /**
     * Find list of elements
     *
     * @param locator Elements selector
     * @param clazz class or interface of the element to be obtained
     * @param count type of expected count of elements
     * @return list of elements
     */
    <T extends IElement> List<T> findElements(By locator, Class<? extends IElement> clazz, ElementsCount count);

    /**
     * Find list of elements
     *
     * @param locator Elements selector
     * @param type the type of elements to be obtained
     * @param count type of expected count of elements
     * @return list of elements
     */
    <T extends IElement> List<T> findElements(By locator, ElementType type, ElementsCount count);

    /**
     * Find list of elements
     *
     * @param locator Elements selector
     * @param clazz class or interface of elements to be obtained
     * @return list of elements
     */
    default <T extends IElement> List<T> findElements(By locator, Class<? extends IElement> clazz) {
        return findElements(locator, clazz, ElementsCount.MORE_THEN_ZERO);
    }

    /**
     * Find list of elements
     *
     * @param locator Elements selector
     * @param type the type of elements to be obtained
     * @return list of elements
     */
    default <T extends IElement> List<T> findElements(By locator, ElementType type) {
        return findElements(locator, type, ElementsCount.MORE_THEN_ZERO);
    }
}
