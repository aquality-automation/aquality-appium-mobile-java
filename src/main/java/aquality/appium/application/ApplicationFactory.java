package aquality.appium.application;

import aquality.appium.configuration.IConfiguration;
import aquality.appium.localization.LocalizationManager;
import aquality.appium.logger.Logger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Capabilities;

import java.net.URL;

abstract class ApplicationFactory implements IApplicationFactory {

    IllegalArgumentException getLoggedWrongPlatformNameException() {
        // todo: String message = getLocManager().getValue("loc.browser.name.wrong");
        String message = "Platform name is not supported";
        IllegalArgumentException exception = new IllegalArgumentException(message);
        Logger.getInstance().fatal(message, exception);
        return exception;
    }

    AppiumDriver getDriver(URL serviceUrl, IConfiguration configuration) {
        PlatformName platformName = configuration.getApplicationProfile().getPlatformName();
        Capabilities capabilities = configuration.getApplicationProfile().getDriverSettings().getCapabilities();
        AppiumDriver driver;
        switch (platformName) {
            case ANDROID:
                driver = new AndroidDriver<>(serviceUrl, capabilities);
                break;
            case IOS:
                driver = new IOSDriver<>(serviceUrl, capabilities);
                break;
            case WINDOWS:
                driver = new WindowsDriver<>(serviceUrl, capabilities);
                break;
            default:
                throw getLoggedWrongPlatformNameException();
        }
        return driver;
    }

    void logApplicationIsReady(PlatformName platformName) {
        Logger.getInstance().info(getLocManager().getValue("loc.browser.ready"), platformName.toString());
    }

    private LocalizationManager getLocManager(){
        return LocalizationManager.getInstance();
    }
}
