package aquality.appium.mobile.actions;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.configuration.ITouchActionsConfiguration;
import aquality.selenium.core.utilities.IElementActionRetrier;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

public class TouchActions implements ITouchActions {

    private Duration getSwipeDuration() {
        return AqualityServices.get(ITouchActionsConfiguration.class).getSwipeDuration();
    }

    private PointerInput getFinger() {
        return new PointerInput(PointerInput.Kind.TOUCH, "finger");
    }

    private Sequence getPressSequence(PointerInput finger, Point startPoint) {
        Sequence swipeDown = new Sequence(finger, 0);
        return swipeDown
                .addAction(finger.createPointerMove(
                        Duration.ZERO, PointerInput.Origin.viewport(), startPoint.getX(), startPoint.getY()))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    }

    @Override
    public void swipe(Point startPoint, Point endPoint) {
        AqualityServices.getLocalizedLogger().info(
                "loc.action.swipe",
                startPoint.getX(),
                startPoint.getY(),
                endPoint.getX(),
                endPoint.getY());
        PointerInput finger = getFinger();
        performTouchAction(finger, getPressSequence(finger, startPoint), endPoint);
    }

    @Override
    public void swipeWithLongPress(Point startPoint, Point endPoint) {
        AqualityServices.getLocalizedLogger().info(
                "loc.action.swipeLongPress",
                startPoint.getX(),
                startPoint.getY(),
                endPoint.getX(),
                endPoint.getY());
        PointerInput finger = getFinger();
        Sequence sequence = getPressSequence(finger, startPoint)
                .addAction(finger.createPointerMove(
                        getSwipeDuration(), PointerInput.Origin.viewport(), startPoint.getX(), startPoint.getY()));
        performTouchAction(finger, sequence, endPoint);
    }

    protected void performTouchAction(PointerInput finger, Sequence actionsSequence, Point endPoint) {
        actionsSequence
                .addAction(finger.createPointerMove(getSwipeDuration(),
                        PointerInput.Origin.viewport(), endPoint.getX(), endPoint.getY()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        AqualityServices.get(IElementActionRetrier.class).doWithRetry(() ->
                AqualityServices.getApplication().getDriver().perform(Collections.singletonList(actionsSequence)));
    }
}
