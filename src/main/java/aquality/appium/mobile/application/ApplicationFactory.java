package aquality.appium.mobile.application;

import aquality.selenium.core.configurations.ITimeoutConfiguration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.http.HttpClient.Builder;
import org.openqa.selenium.remote.http.HttpClient.Factory;

import java.net.URL;
import java.time.Duration;

abstract class ApplicationFactory implements IApplicationFactory {

    private IllegalArgumentException getLoggedWrongPlatformNameException() {
        // todo: String message = getLocalizationManager().getValue("loc.browser.name.wrong");
        String message = "Platform name is not supported";
        IllegalArgumentException exception = new IllegalArgumentException(message);
        AqualityServices.getLogger().fatal(message, exception);
        return exception;
    }

    AppiumDriver getDriver(URL serviceUrl) {
        PlatformName platformName = AqualityServices.getApplicationProfile().getPlatformName();
        Capabilities capabilities = AqualityServices.getApplicationProfile().getDriverSettings().getCapabilities();
        Factory httpClientFactory = new ClientFactory();
        AppiumDriver driver;
        switch (platformName) {
            case ANDROID:
                driver = new AndroidDriver<AndroidElement>(serviceUrl, httpClientFactory, capabilities);
                break;
            case IOS:
                driver = new IOSDriver<IOSElement>(serviceUrl, httpClientFactory, capabilities);
                break;
            case WINDOWS:
                driver = new WindowsDriver<WindowsElement>(serviceUrl, httpClientFactory, capabilities);
                break;
            default:
                throw getLoggedWrongPlatformNameException();
        }
        return driver;
    }

    class ClientFactory implements Factory{

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

    void logApplicationIsReady() {
        // todo: change key
        AqualityServices.getLocalizedLogger().info("loc.browser.ready", AqualityServices.getApplicationProfile().getPlatformName().toString());
    }
}
