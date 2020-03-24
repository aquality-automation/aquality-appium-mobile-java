package aquality.appium.mobile.configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import com.google.inject.Inject;

import java.time.Duration;

public class SwipeConfiguration implements ISwipeConfiguration {
    private final int retries;
    private final Duration timeout;

    @Inject
    public SwipeConfiguration(ISettingsFile settingsFile) {
        this.retries = Integer.parseInt(settingsFile.getValue("/swipe/retries").toString());
        this.timeout = Duration.ofSeconds(Long.parseLong(settingsFile.getValue("/swipe/timeout").toString()));
    }

    @Override
    public int getRetries() {
        return this.retries;
    }

    @Override
    public Duration getTimeout() {
        return this.timeout;
    }
}
