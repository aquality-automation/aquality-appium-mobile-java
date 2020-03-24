package aquality.appium.mobile.elements.actions;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.configuration.ISwipeConfiguration;
import aquality.appium.mobile.elements.ScreenSizeType;
import aquality.appium.mobile.elements.SwipeDirection;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.selenium.core.logging.Logger;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

import static io.appium.java_client.touch.WaitOptions.waitOptions;

public class SwipeActions {

    /**
     * returns new instance of TouchAction.
     */
    private static TouchAction<?> getTouchAction() {
        return new TouchAction<>(AqualityServices.getApplication().getDriver());
    }

    /**
     * Swipes from start coordinates to end coordinates using TouchAction.
     *
     * @param x1 horizontal start coordinate.
     * @param y1 vertical start coordinate.
     * @param x2 horizontal end coordinate.
     * @param y2 vertical end coordinate.
     */
    public static void swipe(int x1, int y1, int x2, int y2) {
        Logger.getInstance().info(String.format("Swiping: from coordinates x:%s y:%s to x:%s y:%s", x1, y1, x2, y2));
        getTouchAction().press(PointOption.point(x1, y1))
                .waitAction(waitOptions(AqualityServices.get(ISwipeConfiguration.class).getTimeout()))
                .moveTo(PointOption.point(x2, y2)).release().perform();
    }

    /**
     * Swipes from start coordinates to end coordinates using LongPress.
     *
     * @param x1 horizontal start coordinate.
     * @param y1 vertical start coordinate.
     * @param x2 horizontal end coordinate.
     * @param y2 vertical end coordinate.
     */
    public static void swipeWithLongPress(int x1, int y1, int x2, int y2) {
        Logger.getInstance().info(String.format("Swiping: from coordinates x:%s y:%s to x:%s y:%s", x1, y1, x2, y2));
        getTouchAction().longPress(PointOption.point(x1, y1))
                .moveTo(PointOption.point(x2, y2)).release().perform();
    }

    /**
     * Returns the coordinate according to the screen height/width adjusted for the swipe coefficient.
     *
     * @param size             width/height
     * @param swipeCoefficient coefficient relative to the top/bottom of the screen
     * @return coordinate to swipe from/to
     */
    public static int getCoordinate(ScreenSizeType size, double swipeCoefficient) {
        Dimension screenSize = AqualityServices.getApplication().getDriver().manage().window().getSize();
        if (size == ScreenSizeType.HEIGHT) {
            return (int) (screenSize.getHeight() * swipeCoefficient);
        }
        return (int) (screenSize.getWidth() * swipeCoefficient);
    }

    /**
     * Scrolls until element is not displayed using the fixed number of retries.
     *
     * @param element   element to swipe to.
     * @param direction direction to swipe.
     */
    public static void scrollToElement(IElement element, SwipeDirection direction) {
        int numberOfRetires = AqualityServices.get(ISwipeConfiguration.class).getRetries();
        while (numberOfRetires >= 0) {
            if (!element.state().isDisplayed()) {
                switch (direction) {
                    case DOWN:
                        swipe(
                                getCoordinate(ScreenSizeType.WIDTH, 0.5),
                                getCoordinate(ScreenSizeType.HEIGHT, 0.8),
                                getCoordinate(ScreenSizeType.WIDTH, 0.5),
                                getCoordinate(ScreenSizeType.HEIGHT, 0.2));
                        break;
                    case UP:
                        swipe(
                                getCoordinate(ScreenSizeType.WIDTH, 0.5),
                                getCoordinate(ScreenSizeType.HEIGHT, 0.2),
                                getCoordinate(ScreenSizeType.WIDTH, 0.5),
                                getCoordinate(ScreenSizeType.HEIGHT, 0.8));
                        break;
                    default:
                        throw new IllegalArgumentException(
                                String.format("'%s' direction does not exist", direction.toString()));
                }
            }
            numberOfRetires--;
        }
    }

}
