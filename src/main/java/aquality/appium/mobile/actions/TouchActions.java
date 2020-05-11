package aquality.appium.mobile.actions;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.configuration.ITouchActionsConfiguration;
import aquality.selenium.core.utilities.IElementActionRetrier;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Point;
import java.util.function.UnaryOperator;
import static io.appium.java_client.touch.WaitOptions.waitOptions;

public class TouchActions implements ITouchActions {

    @Override
    public void swipe(Point startPoint, Point endPoint) {
        AqualityServices.getLocalizedLogger().info(
                "loc.action.swipe",
                startPoint.getX(),
                startPoint.getY(),
                endPoint.getX(),
                endPoint.getY());
        performTouchAction(touchAction -> touchAction
                .press(PointOption.point(startPoint))
                .waitAction(waitOptions(AqualityServices.get(ITouchActionsConfiguration.class).getSwipeTimeout())),
                endPoint);
    }

    @Override
    public void swipeWithLongPress(Point startPoint, Point endPoint) {
        AqualityServices.getLocalizedLogger().info(
                "loc.action.swipeLongPress",
                startPoint.getX(),
                startPoint.getY(),
                endPoint.getX(),
                endPoint.getY());
        performTouchAction(touchAction -> touchAction.longPress(PointOption.point(startPoint)), endPoint);
    }

    protected void performTouchAction(UnaryOperator<TouchAction<?>> function, Point endPoint) {
        TouchAction<?> touchAction = new TouchAction<>(AqualityServices.getApplication().getDriver());
        AqualityServices.get(IElementActionRetrier.class).doWithRetry(() ->
                function.apply(touchAction).moveTo(PointOption.point(endPoint)).release().perform());
    }
}
