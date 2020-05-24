package aquality.appium.mobile.elements.actions;

import aquality.appium.mobile.actions.SwipeDirection;
import org.openqa.selenium.Point;

/**
 * Describes Touch Actions for elements.
 */
public interface IElementTouchActions {

    /**
     * Swipes from element to end point using TouchAction.
     *
     * @param endPoint point on screen to swipe to.
     */
    void swipe(final Point endPoint);

    /**
     * Swipes from element to end point using LongPress.
     *
     * @param endPoint point on screen to swipe to.
     */
    void swipeWithLongPress(final Point endPoint);

    /**
     * Scrolls current screen in provided direction until element is displayed.
     *
     * @param direction direction to swipe.
     */
    void scrollToElement(final SwipeDirection direction);
}
