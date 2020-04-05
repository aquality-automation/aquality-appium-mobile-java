package aquality.appium.mobile.elements.actions;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.actions.TouchActions;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.configuration.ISwipeConfiguration;
import aquality.appium.mobile.elements.interfaces.IElement;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

public class ElementTouchActions implements IElementTouchActions {

    private final Point scrollDownStartPoint = recalculatePointCoordinates(
            getBottomRightCornerPoint(),
            AqualityServices.get(ISwipeConfiguration.class).getHorizontalSwipeBottomPointXCoefficient(),
            AqualityServices.get(ISwipeConfiguration.class).getHorizontalSwipeBottomPointYCoefficient());

    private final Point scrollDownEndPoint = recalculatePointCoordinates(
            getBottomRightCornerPoint(),
            AqualityServices.get(ISwipeConfiguration.class).getHorizontalSwipeTopPointXCoefficient(),
            AqualityServices.get(ISwipeConfiguration.class).getHorizontalSwipeTopPointYCoefficient());

    private final Point swipeLeftStartPoint = recalculatePointCoordinates(
            getBottomRightCornerPoint(),
            AqualityServices.get(ISwipeConfiguration.class).getVerticalSwipeRightPointXCoefficient(),
            AqualityServices.get(ISwipeConfiguration.class).getVerticalSwipeRightPointYCoefficient());

    private final Point swipeLeftEndPoint = recalculatePointCoordinates(
            getBottomRightCornerPoint(),
            AqualityServices.get(ISwipeConfiguration.class).getVerticalSwipeLeftPointXCoefficient(),
            AqualityServices.get(ISwipeConfiguration.class).getVerticalSwipeLeftPointYCoefficient());

    private final Point scrollUpStartPoint = scrollDownEndPoint;

    private final Point scrollUpEndPoint = scrollDownStartPoint;

    private final Point swipeRightStartPoint = swipeLeftEndPoint;

    private final Point swipeRightEndPoint = swipeLeftStartPoint;

    @Override
    public void swipe(IElement element, Point endPoint) {
        Point elementCenter = getElementCenter(element);
        getTouchActions().swipe(elementCenter, endPoint);
    }

    @Override
    public void swipeWithLongPress(IElement element, Point endPoint) {
        Point elementCenter = getElementCenter(element);
        getTouchActions().swipeWithLongPress(elementCenter, endPoint);
    }

    @Override
    public void scrollToElement(IElement element, SwipeDirection direction) {
        int numberOfRetries = AqualityServices.get(ISwipeConfiguration.class).getRetries();
        TouchActions touchActions = getTouchActions();
        while (numberOfRetries > 0) {
            if (!element.state().isDisplayed()) {
                switch (direction) {
                    case DOWN:
                        touchActions.swipe(scrollDownStartPoint, scrollDownEndPoint);
                        break;
                    case UP:
                        touchActions.swipe(scrollUpStartPoint, scrollUpEndPoint);
                        break;
                    case LEFT:
                        touchActions.swipe(swipeLeftStartPoint, swipeLeftEndPoint);
                        break;
                    case RIGHT:
                        touchActions.swipe(swipeRightStartPoint, swipeRightEndPoint);
                        break;
                    default:
                        throw new IllegalArgumentException(
                                String.format("'%s' direction does not exist", direction.toString()));
                }
            }
            numberOfRetries--;
        }
    }

    /**
     * returns new instance of aquality.appium.mobile.actions.TouchActions.
     */
    private TouchActions getTouchActions() {
        return new TouchActions();
    }

    /**
     * returns the the center of provided element as Point.
     *
     * @param element element.
     */
    private Point getElementCenter(IElement element) {
        Dimension elementSize = element.getElement().getSize();
        Point elementLocation = element.getElement().getLocation();
        int centerX = elementLocation.getX() - elementSize.width / 2;
        int centerY = elementLocation.getY() - elementSize.height / 2;
        return new Point(centerX, centerY);
    }

    /**
     * returns Point in the bottom right corner of the screen.
     */
    private Point getBottomRightCornerPoint() {
        Dimension screenSize = AqualityServices.getApplication().getDriver().manage().window().getSize();
        return new Point(screenSize.width, screenSize.height);
    }

    /**
     * Returns the point with recalculated coordinates.
     *
     * @param point        point to recalculate coordinates
     * @param coefficientX coefficient to recalculate the point's x coordinate. Example: x' = x * coefficientX
     * @param coefficientY coefficient to recalculate the point's y coordinate. Example: y' = y * coefficientY
     * @return point with recalculated coordinates according to the xCoefficient and yCoefficient
     */
    private Point recalculatePointCoordinates(Point point, double coefficientX, double coefficientY) {
        return new Point(
                (int) (point.getX() * coefficientX),
                (int) (point.getY() * coefficientY));
    }
}
