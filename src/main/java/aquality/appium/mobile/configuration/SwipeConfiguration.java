package aquality.appium.mobile.configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import com.google.inject.Inject;

import java.time.Duration;

public class SwipeConfiguration implements ISwipeConfiguration {
    private final int retries;
    private final Duration timeout;
    private final double horizontalSwipeTopPointXCoefficient;
    private final double horizontalSwipeBottomPointXCoefficient;
    private final double horizontalSwipeTopPointYCoefficient;
    private final double horizontalSwipeBottomPointYCoefficient;
    private final double verticalSwipeLeftPointXCoefficient;
    private final double verticalSwipeRightPointXCoefficient;
    private final double verticalSwipeLeftPointYCoefficient;
    private final double verticalSwipeRightPointYCoefficient;

    @Inject
    public SwipeConfiguration(ISettingsFile settingsFile) {
        this.retries = (int) settingsFile.getValue("/swipe/retries");
        this.timeout = Duration.ofSeconds(Long.parseLong(settingsFile.getValue("/swipe/timeout").toString()));
        this.horizontalSwipeTopPointXCoefficient = getSwipeCoefficient(
                settingsFile.getValue("/swipe/horizontalSwipeTopPointXCoefficient").toString());
        this.horizontalSwipeBottomPointXCoefficient = getSwipeCoefficient(
                settingsFile.getValue("/swipe/horizontalSwipeBottomPointXCoefficient").toString());
        this.horizontalSwipeTopPointYCoefficient = getSwipeCoefficient(
                settingsFile.getValue("/swipe/horizontalSwipeTopPointYCoefficient").toString());
        this.horizontalSwipeBottomPointYCoefficient = getSwipeCoefficient(
                settingsFile.getValue("/swipe/horizontalSwipeBottomPointYCoefficient").toString());
        this.verticalSwipeLeftPointXCoefficient = getSwipeCoefficient(
                settingsFile.getValue("/swipe/verticalSwipeLeftPointXCoefficient").toString());
        this.verticalSwipeRightPointXCoefficient = getSwipeCoefficient(
                settingsFile.getValue("/swipe/verticalSwipeRightPointXCoefficient").toString());
        this.verticalSwipeLeftPointYCoefficient = getSwipeCoefficient(
                settingsFile.getValue("/swipe/verticalSwipeLeftPointYCoefficient").toString());
        this.verticalSwipeRightPointYCoefficient = getSwipeCoefficient(
                settingsFile.getValue("/swipe/verticalSwipeRightPointYCoefficient").toString());
    }

    @Override
    public int getRetries() {
        return this.retries;
    }

    @Override
    public Duration getTimeout() {
        return this.timeout;
    }

    @Override
    public double getHorizontalSwipeTopPointXCoefficient() {
        return this.horizontalSwipeTopPointXCoefficient;
    }

    @Override
    public double getHorizontalSwipeBottomPointXCoefficient() {
        return this.horizontalSwipeBottomPointXCoefficient;
    }

    @Override
    public double getHorizontalSwipeTopPointYCoefficient() {
        return this.horizontalSwipeTopPointYCoefficient;
    }

    @Override
    public double getHorizontalSwipeBottomPointYCoefficient() {
        return this.horizontalSwipeBottomPointYCoefficient;
    }

    @Override
    public double getVerticalSwipeLeftPointXCoefficient() {
        return this.verticalSwipeLeftPointXCoefficient;
    }

    @Override
    public double getVerticalSwipeRightPointXCoefficient() {
        return this.verticalSwipeRightPointXCoefficient;
    }

    @Override
    public double getVerticalSwipeLeftPointYCoefficient() {
        return this.verticalSwipeLeftPointYCoefficient;
    }

    @Override
    public double getVerticalSwipeRightPointYCoefficient() {
        return this.verticalSwipeRightPointYCoefficient;
    }

    private double getSwipeCoefficient(String name) {
        return Double.parseDouble(name);
    }
}
