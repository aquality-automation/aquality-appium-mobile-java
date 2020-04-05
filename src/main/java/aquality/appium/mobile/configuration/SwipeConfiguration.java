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
        this.retries = Integer.parseInt(settingsFile.getValue("/swipe/retries").toString());
        this.timeout = Duration.ofSeconds(Long.parseLong(settingsFile.getValue("/swipe/timeout").toString()));
        this.horizontalSwipeTopPointXCoefficient = Double.parseDouble(
                settingsFile.getValue("/swipe/horizontalSwipeTopPointXCoefficient").toString());
        this.horizontalSwipeBottomPointXCoefficient = Double.parseDouble(
                settingsFile.getValue("/swipe/horizontalSwipeBottomPointXCoefficient").toString());
        this.horizontalSwipeTopPointYCoefficient = Double.parseDouble(
                settingsFile.getValue("/swipe/horizontalSwipeTopPointYCoefficient").toString());
        this.horizontalSwipeBottomPointYCoefficient = Double.parseDouble(
                settingsFile.getValue("/swipe/horizontalSwipeBottomPointYCoefficient").toString());
        this.verticalSwipeLeftPointXCoefficient = Double.parseDouble(
                settingsFile.getValue("/swipe/verticalSwipeLeftPointXCoefficient").toString());
        this.verticalSwipeRightPointXCoefficient = Double.parseDouble(
                settingsFile.getValue("/swipe/verticalSwipeRightPointXCoefficient").toString());
        this.verticalSwipeLeftPointYCoefficient = Double.parseDouble(
                settingsFile.getValue("/swipe/verticalSwipeLeftPointYCoefficient").toString());
        this.verticalSwipeRightPointYCoefficient = Double.parseDouble(
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
}
