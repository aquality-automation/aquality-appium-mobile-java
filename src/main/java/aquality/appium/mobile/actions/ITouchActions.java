package aquality.appium.mobile.actions;

import org.openqa.selenium.Point;

/**
 * Describes general Touch Actions.
 */
public interface ITouchActions {

    /**
     * Swipes from start point to end point using TouchAction.
     *
     * @param startPoint point on screen to swipe from.
     * @param endPoint   point on screen to swipe to.
     */
    void swipe(Point startPoint, Point endPoint);

    /**
     * Swipes from start point to end point using LongPress.
     *
     * @param startPoint point on screen to swipe from.
     * @param endPoint   point on screen to swipe to.
     */
    void swipeWithLongPress(Point startPoint, Point endPoint);
}
