package aquality.appium.mobile.elements.actions;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.elements.interfaces.IElement;
import org.openqa.selenium.Point;

/**
 * Describes Touch Actions for elements.
 */
public interface IElementTouchActions {

    /**
     * Swipes from element to end point using TouchAction.
     *
     * @param element  element on screen to swipe from.
     * @param endPoint point on screen to swipe to.
     */
    void swipe(IElement element, Point endPoint);

    /**
     * Swipes from element to end point using LongPress.
     *
     * @param element  element on screen to swipe from.
     * @param endPoint point on screen to swipe to.
     */
    void swipeWithLongPress(IElement element, Point endPoint);

    /**
     * Scrolls current screen in provided direction until element is displayed.
     *
     * @param element   element to scroll to.
     * @param direction direction to swipe.
     */
    void scrollToElement(IElement element, SwipeDirection direction);
}
