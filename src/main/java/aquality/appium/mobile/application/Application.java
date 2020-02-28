package aquality.appium.mobile.application;

import aquality.appium.mobile.configuration.IApplicationProfile;
import aquality.selenium.core.applications.IApplication;
import aquality.selenium.core.configurations.ITimeoutConfiguration;
import aquality.selenium.core.localization.ILocalizedLogger;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.service.DriverService;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Application implements IApplication {

    private final ILocalizedLogger localizedLogger;
    private final IApplicationProfile applicationProfile;
    private final ITimeoutConfiguration timeouts;
    private Duration timeoutImpl;
    private final AppiumDriver appiumDriver;
    private final DriverService driverService;

    public Application(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.driverService = null;
        localizedLogger = AqualityServices.getLocalizedLogger();
        applicationProfile = AqualityServices.getApplicationProfile();
        this.timeouts = AqualityServices.get(ITimeoutConfiguration.class);
        this.timeoutImpl = timeouts.getImplicit();
        setImplicitlyWaitToDriver(timeoutImpl.getSeconds());
    }

    public Application(DriverService driverService, AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.driverService = driverService;
        localizedLogger = AqualityServices.getLocalizedLogger();
        applicationProfile = AqualityServices.getApplicationProfile();
        this.timeouts = AqualityServices.get(ITimeoutConfiguration.class);
        this.timeoutImpl = timeouts.getImplicit();
        setImplicitlyWaitToDriver(timeoutImpl.getSeconds());
    }

    private void setImplicitlyWaitToDriver(long seconds) {
        getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
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
            setImplicitlyWaitToDriver(timeoutImpl.getSeconds());
            timeoutImpl = timeout;
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
