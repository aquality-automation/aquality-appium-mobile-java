package aquality.appium.mobile.elements.actions;

import aquality.appium.mobile.actions.ITouchActions;
import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.configuration.ITouchActionsConfiguration;
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
                (1 - getConfiguration().getSwipeHorizontalOffset()),
                (1 - getConfiguration().getSwipeVerticalOffset()));
        this.scrollDownEndPoint = recalculatePointCoordinates(
                getBottomRightCornerPoint(),
                getConfiguration().getSwipeHorizontalOffset(),
                getConfiguration().getSwipeVerticalOffset());
        this.swipeLeftStartPoint = recalculatePointCoordinates(
                getBottomRightCornerPoint(),
                (1 - getConfiguration().getSwipeVerticalOffset()),
                (1 - getConfiguration().getSwipeHorizontalOffset()));
        this.swipeLeftEndPoint = recalculatePointCoordinates(
                getBottomRightCornerPoint(),
                getConfiguration().getSwipeVerticalOffset(),
                getConfiguration().getSwipeHorizontalOffset());
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
        int numberOfRetries = AqualityServices.get(ITouchActionsConfiguration.class).getSwipeRetries();
        ITouchActions touchActions = AqualityServices.getTouchActions();
        while (numberOfRetries-- > 0 && !element.state().isDisplayed()) {
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
     * @param point            point to recalculate coordinates
     * @param horizontalOffset coefficient to recalculate the point with horizontal offset.
     * @param verticalOffset   coefficient to recalculate the point with vertical offset.
     * @return point with recalculated coordinates with horizontal and vertical offset.
     */
    private Point recalculatePointCoordinates(Point point, double horizontalOffset, double verticalOffset) {
        return new Point(
                (int) (point.getX() * horizontalOffset),
                (int) (point.getY() * verticalOffset));
    }

    /**
     * Returns ITouchActionsConfiguration class.
     *
     * @return ITouchActionsConfiguration class
     */
    private ITouchActionsConfiguration getConfiguration() {
        return AqualityServices.get(ITouchActionsConfiguration.class);
    }
}
