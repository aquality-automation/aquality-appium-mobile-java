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
                settingsFile, "horizontalSwipeTopPointXCoefficient");
        this.horizontalSwipeBottomPointXCoefficient = getSwipeCoefficient(
                settingsFile, "horizontalSwipeBottomPointXCoefficient");
        this.horizontalSwipeTopPointYCoefficient = getSwipeCoefficient(
                settingsFile, "horizontalSwipeTopPointYCoefficient");
        this.horizontalSwipeBottomPointYCoefficient = getSwipeCoefficient(
                settingsFile, "horizontalSwipeBottomPointYCoefficient");
        this.verticalSwipeLeftPointXCoefficient = getSwipeCoefficient(
                settingsFile, "verticalSwipeLeftPointXCoefficient");
        this.verticalSwipeRightPointXCoefficient = getSwipeCoefficient(
                settingsFile, "verticalSwipeRightPointXCoefficient");
        this.verticalSwipeLeftPointYCoefficient = getSwipeCoefficient(
                settingsFile, "verticalSwipeLeftPointYCoefficient");
        this.verticalSwipeRightPointYCoefficient = getSwipeCoefficient(
                settingsFile, "verticalSwipeRightPointYCoefficient");
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

    private double getSwipeCoefficient(ISettingsFile settingsFile, String swipeCoefficientKey) {
        return Double.parseDouble(settingsFile.getValue(String.format("/swipe/%s", swipeCoefficientKey)).toString());
    }
}
