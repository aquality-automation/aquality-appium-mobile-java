package aquality.appium.application;

import aquality.appium.configuration.IConfiguration;
import aquality.appium.configuration.ITimeoutConfiguration;
import aquality.appium.logger.Logger;
import io.appium.java_client.AppiumDriver;

public class Application {

    private final Logger logger = Logger.getInstance();
    private final IConfiguration configuration;
    private final ITimeoutConfiguration timeouts;
    private Long timeoutImpl;
    private final AppiumDriver appiumDriver;

    public Application(AppiumDriver appiumDriver, IConfiguration configuration) {
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

    /**
     * Provides AppiumDriver instance for current application session
     * @return web driver instance
     */
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    /**
     * Returns name of current platform
     * @return name
     */
    public final PlatformName getPlatformName() {
        return configuration.getApplicationProfile().getPlatformName();
    }
}
