package aquality.appium.application;

import aquality.appium.configuration.IConfiguration;
import aquality.selenium.configuration.ITimeoutConfiguration;
import aquality.selenium.localization.LocalizationManager;
import aquality.selenium.logger.Logger;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.service.DriverService;

public class Application {

    private final Logger logger = Logger.getInstance();
    private final IConfiguration configuration;
    private final ITimeoutConfiguration timeouts;
    private Long timeoutImpl;
    private final AppiumDriver appiumDriver;
    private final DriverService driverService;

    public Application(AppiumDriver appiumDriver, IConfiguration configuration) {
        this.driverService = null;
        this.appiumDriver = appiumDriver;
        this.configuration = configuration;
        this.timeouts = configuration.getTimeoutConfiguration();
        this.timeoutImpl = timeouts.getImplicit();
        /*
        todo: determine if all of these timeouts are necessary to be set this way
        getDriver().manage().timeouts().implicitlyWait(timeoutImpl, TimeUnit.SECONDS);
        setPageLoadTimeout(timeouts.getPageLoad());
        setScriptTimeout(timeouts.getScript());
         */
    }

    public Application(DriverService driverService, AppiumDriver appiumDriver, IConfiguration configuration) {
        this.driverService = driverService;
        this.appiumDriver = appiumDriver;
        this.configuration = configuration;
        this.timeouts = configuration.getTimeoutConfiguration();
        this.timeoutImpl = timeouts.getImplicit();
    }

    /**
     * Provides AppiumDriver instance for current application session
     * @return driver instance
     */
    public AppiumDriver getDriver() {
        return appiumDriver;
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
        return configuration.getApplicationProfile().getPlatformName();
    }

    public void quit() {
        logger.info(LocalizationManager.getInstance().getValue("loc.browser.driver.quit"));
        if (getDriver() != null) {
            getDriver().quit();
        }

        if (getDriverService() != null) {
            getDriverService().stop();
        }
    }
}
