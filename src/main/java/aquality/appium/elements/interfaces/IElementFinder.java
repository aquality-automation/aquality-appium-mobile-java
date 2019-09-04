package aquality.appium.elements.interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Provides ability to find elements in desired ElementState
 */
public interface IElementFinder extends SearchContext {

    /**
     * Finds elements
     * @param locator elements locator
     * @param timeout timeout for search
     * @return list of found elements
     */
    List<WebElement> findElements(By locator, long timeout);

    /**
     * Finds element
     * @param locator elements locator
     * @param timeout timeout for search
     * @throws org.openqa.selenium.NoSuchElementException if element was not found in time in desired state
     * @return found element
     */
    WebElement findElement(By locator, long timeout);

    default List<WebElement> findElements(By by) {
        return findElements(by, getDefaultTimeout());
    }

    default WebElement findElement(By by) {
        return findElement(by, getDefaultTimeout());
    }

    /**
     * @return default timeout for element waiting
     */
    long getDefaultTimeout();
}
