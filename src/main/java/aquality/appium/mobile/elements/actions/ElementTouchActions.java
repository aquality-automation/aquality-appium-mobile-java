package aquality.appium.mobile.elements.actions;

import aquality.appium.mobile.actions.ITouchActions;
import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.configuration.ISwipeConfiguration;
import aquality.appium.mobile.elements.interfaces.IElement;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

public class ElementTouchActions implements IElementTouchActions {
    private IElement element;
    private Point scrollDownStartPoint;
    private Point scrollDownEndPoint;
    private Point swipeLeftStartPoint;
    private Point swipeLeftEndPoint;
    private Point scrollUpStartPoint;
    private Point scrollUpEndPoint;
    private Point swipeRightStartPoint;
    private Point swipeRightEndPoint;

    public ElementTouchActions(IElement element) {
        this.element = element;
        this.scrollDownStartPoint = recalculatePointCoordinates(
                getBottomRightCornerPoint(),
                getISwipeConfiguration().getHorizontalSwipeBottomPointXCoefficient(),
                getISwipeConfiguration().getHorizontalSwipeBottomPointYCoefficient());
        this.scrollDownEndPoint = recalculatePointCoordinates(
                getBottomRightCornerPoint(),
                getISwipeConfiguration().getHorizontalSwipeTopPointXCoefficient(),
                getISwipeConfiguration().getHorizontalSwipeTopPointYCoefficient());
        this.swipeLeftStartPoint = recalculatePointCoordinates(
                getBottomRightCornerPoint(),
                getISwipeConfiguration().getVerticalSwipeRightPointXCoefficient(),
                getISwipeConfiguration().getVerticalSwipeRightPointYCoefficient());
        this.swipeLeftEndPoint = recalculatePointCoordinates(
                getBottomRightCornerPoint(),
                getISwipeConfiguration().getVerticalSwipeLeftPointXCoefficient(),
                getISwipeConfiguration().getVerticalSwipeLeftPointYCoefficient());
        this.scrollUpStartPoint = this.scrollDownEndPoint;
        this.scrollUpEndPoint = this.scrollDownStartPoint;
        this.swipeRightStartPoint = this.swipeLeftEndPoint;
        this.swipeRightEndPoint = this.swipeLeftStartPoint;
    }

    @Override
    public void swipe(Point endPoint) {
        AqualityServices.getTouchActions().swipe(element.getElement().getCenter(), endPoint);
    }

    @Override
    public void swipeWithLongPress(Point endPoint) {
        AqualityServices.getTouchActions().swipeWithLongPress(element.getElement().getCenter(), endPoint);
    }

    @Override
    public void scrollToElement(SwipeDirection direction) {
        int numberOfRetries = AqualityServices.get(ISwipeConfiguration.class).getRetries();
        ITouchActions touchActions = AqualityServices.getTouchActions();
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
     * Returns Point in the bottom right corner of the screen.
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

    /**
     * Returns ISwipeConfiguration class.
     *
     * @return ISwipeConfiguration class
     */
    private ISwipeConfiguration getISwipeConfiguration() {
        return AqualityServices.get(ISwipeConfiguration.class);
    }
}
