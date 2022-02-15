package aquality.appium.mobile.application;

import aquality.appium.mobile.configuration.IApplicationProfile;
import aquality.selenium.core.applications.IApplication;
import aquality.selenium.core.configurations.ITimeoutConfiguration;
import aquality.selenium.core.localization.ILocalizedLogger;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.service.DriverService;

import java.time.Duration;

public class Application implements IApplication {

    private final ILocalizedLogger localizedLogger;
    private final IApplicationProfile applicationProfile;
    private Duration timeoutImpl;
    private final AppiumDriver appiumDriver;
    private final DriverService driverService;

    public Application(AppiumDriver appiumDriver) {
        this(appiumDriver, null);
    }

    public Application(AppiumDriver appiumDriver, DriverService driverService) {
        this.appiumDriver = appiumDriver;
        this.driverService = driverService;
        localizedLogger = AqualityServices.getLocalizedLogger();
        applicationProfile = AqualityServices.getApplicationProfile();
        Duration implicitTimeout = AqualityServices.get(ITimeoutConfiguration.class).getImplicit();
        setImplicitlyWaitToDriver(implicitTimeout);
    }

    private void setImplicitlyWaitToDriver(Duration duration) {
        getDriver().manage().timeouts().implicitlyWait(duration);
        this.timeoutImpl = duration;
    }

    /**
     * Provides AppiumDriver instance for current application session.
     * @return driver instance.
     */
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    @Override
    public boolean isStarted() {
        return appiumDriver.getSessionId() != null;
    }

    /**
     * Provides current AppiumDriver service instance (would be null if driver is not local)
     * @return driver service instance
     */
    public DriverService getDriverService() {
        return driverService;
    }

    /**
     * Returns name of current platform
     * @return name
     */
    public final PlatformName getPlatformName() {
        return applicationProfile.getPlatformName();
    }

    @Override
    public void setImplicitWaitTimeout(Duration timeout) {
        if (!timeout.equals(timeoutImpl)) {
            localizedLogger.debug("loc.application.implicit.timeout", timeout.getSeconds());
            setImplicitlyWaitToDriver(timeout);
        }
    }

    /**
     * Executes appium driver quit, then stops the driver service
     */
    public void quit() {
        localizedLogger.info("loc.application.quit");
        if (getDriver() != null) {
            getDriver().quit();
        }

        if (getDriverService() != null) {
            getDriverService().stop();
        }
    }
}
