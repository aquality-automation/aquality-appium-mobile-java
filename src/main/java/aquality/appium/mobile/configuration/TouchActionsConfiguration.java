package aquality.appium.mobile.configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import com.google.inject.Inject;

import java.time.Duration;

public class TouchActionsConfiguration implements ITouchActionsConfiguration {
    private final int swipeRetries;
    private final Duration swipeTimeout;
    private final double horizontalSwipeTopPointXCoefficient;
    private final double horizontalSwipeBottomPointXCoefficient;
    private final double horizontalSwipeTopPointYCoefficient;
    private final double horizontalSwipeBottomPointYCoefficient;
    private final double verticalSwipeLeftPointXCoefficient;
    private final double verticalSwipeRightPointXCoefficient;
    private final double verticalSwipeLeftPointYCoefficient;
    private final double verticalSwipeRightPointYCoefficient;

    @Inject
    public TouchActionsConfiguration(ISettingsFile settingsFile) {
        this.swipeRetries = (int) settingsFile.getValue("/touchActions/swipe/retries");
        this.swipeTimeout = Duration.ofSeconds(Long.parseLong(settingsFile.getValue("/touchActions/swipe/timeout").toString()));
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
    public int getSwipeRetries() {
        return this.swipeRetries;
    }

    @Override
    public Duration getSwipeTimeout() {
        return this.swipeTimeout;
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
        return Double.parseDouble(settingsFile.getValue(String.format("/touchActions/swipe/%s", swipeCoefficientKey)).toString());
    }
}
