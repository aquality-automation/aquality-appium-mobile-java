package aquality.appium.mobile.application;

import aquality.selenium.core.configurations.ITimeoutConfiguration;
import aquality.selenium.core.localization.ILocalizationManager;
import aquality.selenium.core.utilities.ElementActionRetrier;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.http.HttpClient.Builder;
import org.openqa.selenium.remote.http.HttpClient.Factory;

import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public abstract class ApplicationFactory implements IApplicationFactory {

    protected IllegalArgumentException getLoggedWrongPlatformNameException(String actualPlatform) {
        String message = AqualityServices.get(ILocalizationManager.class)
                .getLocalizedMessage("loc.platform.name.wrong", actualPlatform);
        IllegalArgumentException exception = new IllegalArgumentException(message);
        AqualityServices.getLogger().fatal(message, exception);
        return exception;
    }

    protected AppiumDriver getDriver(URL serviceUrl) {
        PlatformName platformName = AqualityServices.getApplicationProfile().getPlatformName();
        Capabilities capabilities = AqualityServices.getApplicationProfile().getDriverSettings().getCapabilities();
        Factory httpClientFactory = new ClientFactory();
        return new CustomActionRetrier(Collections.singletonList(SessionNotCreatedException.class))
                .doWithRetry(() -> createSession(platformName, serviceUrl, httpClientFactory, capabilities));
    }

    protected AppiumDriver createSession(PlatformName platformName, URL serviceUrl, Factory httpClientFactory,
                                       Capabilities capabilities) {
        AqualityServices.getLocalizedLogger().info("loc.application.driver.remote", serviceUrl);
        AppiumDriver driver;
        switch (platformName) {
            case ANDROID:
                driver = new AndroidDriver<AndroidElement>(serviceUrl, httpClientFactory, capabilities);
                break;
            case IOS:
                driver = new IOSDriver<IOSElement>(serviceUrl, httpClientFactory, capabilities);
                break;
            default:
                throw getLoggedWrongPlatformNameException(platformName.name());
        }
        return driver;
    }

    protected class CustomActionRetrier extends ElementActionRetrier {
        private final List<Class<? extends Throwable>> handledExceptions;

        CustomActionRetrier(List<Class<? extends Throwable>> handledExceptions) {
            super(AqualityServices.getConfiguration().getRetryConfiguration());
            this.handledExceptions = handledExceptions;
        }

        @Override
        public List<Class<? extends Throwable>> getHandledExceptions() {
            return handledExceptions;
        }
    }

    protected class ClientFactory implements Factory {

        private final Factory defaultClientFactory = Factory.createDefault();
        private final Duration timeoutCommand = AqualityServices.get(ITimeoutConfiguration.class).getCommand();

        @Override
        public Builder builder() {
            return defaultClientFactory.builder().readTimeout(timeoutCommand);
        }

        @Override
        public HttpClient createClient(URL url) {
            return this.builder().createClient(url);
        }

        @Override
        public void cleanupIdleClients() {
            defaultClientFactory.cleanupIdleClients();
        }
    }

    protected void logApplicationIsReady() {
        AqualityServices.getLocalizedLogger().info("loc.application.ready", AqualityServices.getApplicationProfile().getPlatformName());
    }
}
