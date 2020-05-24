package aquality.appium.mobile.configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import com.google.inject.Inject;

import java.time.Duration;

public class TouchActionsConfiguration implements ITouchActionsConfiguration {
    private final Duration swipeDuration;
    private final int swipeRetries;
    private final double swipeVerticalOffset;
    private final double swipeHorizontalOffset;

    @Inject
    public TouchActionsConfiguration(ISettingsFile settingsFile) {
        this.swipeDuration = Duration.ofSeconds(Long.parseLong(settingsFile.getValue("/touchActions/swipe/duration").toString()));
        this.swipeRetries = (int) settingsFile.getValue("/touchActions/swipe/retries");
        this.swipeVerticalOffset = (double) settingsFile.getValue("/touchActions/swipe/verticalOffset");
        this.swipeHorizontalOffset = (double) settingsFile.getValue("/touchActions/swipe/horizontalOffset");
    }

    @Override
    public int getSwipeRetries() {
        return this.swipeRetries;
    }

    @Override
    public Duration getSwipeDuration() {
        return this.swipeDuration;
    }

    @Override
    public double getSwipeVerticalOffset() {
        return this.swipeVerticalOffset;
    }

    @Override
    public double getSwipeHorizontalOffset() {
        return this.swipeHorizontalOffset;
    }
}
