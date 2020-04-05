package aquality.appium.mobile.actions;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.configuration.ISwipeConfiguration;
import aquality.selenium.core.logging.Logger;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Point;

import static io.appium.java_client.touch.WaitOptions.waitOptions;

public class TouchActions implements ITouchActions {

    @Override
    public void swipe(Point startPoint, Point endPoint) {
        Logger.getInstance().info(String.format(
                "Swiping: from coordinates x:%s y:%s to x:%s y:%s",
                startPoint.getX(),
                startPoint.getY(),
                endPoint.getX(),
                endPoint.getY()));
        getTouchAction().press(PointOption.point(startPoint))
                .waitAction(waitOptions(AqualityServices.get(ISwipeConfiguration.class).getTimeout()))
                .moveTo(PointOption.point(endPoint)).release().perform();
    }

    @Override
    public void swipeWithLongPress(Point startPoint, Point endPoint) {
        Logger.getInstance().info(String.format(
                "Swiping: from coordinates x:%s y:%s to x:%s y:%s",
                startPoint.getX(),
                startPoint.getY(),
                endPoint.getX(),
                endPoint.getY()));
        getTouchAction().longPress(PointOption.point(startPoint))
                .moveTo(PointOption.point(endPoint)).release().perform();
    }

    /**
     * returns new instance of io.appium.java_client.TouchAction.
     */
    private TouchAction<?> getTouchAction() {
        return new TouchAction<>(AqualityServices.getApplication().getDriver());
    }
}
