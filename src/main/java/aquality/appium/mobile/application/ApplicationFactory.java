package aquality.appium.mobile.application;

import aquality.selenium.core.configurations.ITimeoutConfiguration;
import aquality.selenium.core.localization.ILocalizationManager;
import aquality.selenium.core.utilities.ElementActionRetrier;
import io.appium.java_client.AppiumClientConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.http.ClientConfig;
import io.appium.java_client.http.HttpClient;
import io.appium.java_client.http.HttpClient.Factory;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.SessionNotCreatedException;

import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public abstract class ApplicationFactory implements IApplicationFactory {

    protected CustomActionRetrier getActionRetrier() {
        return new CustomActionRetrier(Collections.singletonList(SessionNotCreatedException.class));
    }

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
        return getActionRetrier()
                .doWithRetry(() -> createSession(platformName, serviceUrl, httpClientFactory, capabilities));
    }

    protected AppiumDriver createSession(PlatformName platformName, URL serviceUrl, Factory httpClientFactory,
                                       Capabilities capabilities) {
        AqualityServices.getLocalizedLogger().info("loc.application.driver.remote", serviceUrl);
        AppiumDriver driver;
        switch (platformName) {
            case ANDROID:
                driver = new AndroidDriver(serviceUrl, httpClientFactory, capabilities);
                break;
            case IOS:
                driver = new IOSDriver(serviceUrl, httpClientFactory, capabilities);
                break;
            default:
                throw getLoggedWrongPlatformNameException(platformName.name());
        }
        return driver;
    }

    protected static class CustomActionRetrier extends ElementActionRetrier {
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

    protected static class ClientFactory implements Factory {

        private final Factory defaultClientFactory = Factory.createDefault();
        private final Duration timeoutCommand = AqualityServices.get(ITimeoutConfiguration.class).getCommand();

        @Override
        public HttpClient createClient(ClientConfig clientConfig) {
            return defaultClientFactory.createClient(AppiumClientConfig.fromClientConfig(clientConfig).readTimeout(timeoutCommand));
        }
    }

    protected void logApplicationIsReady() {
        AqualityServices.getLocalizedLogger().info("loc.application.ready", AqualityServices.getApplicationProfile().getPlatformName());
    }
}
