package aquality.appium.mobile.configuration;

import aquality.selenium.core.configurations.IElementCacheConfiguration;
import aquality.selenium.core.configurations.ILoggerConfiguration;
import aquality.selenium.core.configurations.IRetryConfiguration;
import aquality.selenium.core.configurations.ITimeoutConfiguration;

public interface IConfiguration {

    /**
     * Gets desired application profile.
     *
     * @return Profile of application.
     */
    IApplicationProfile getApplicationProfile();

    /**
     * Gets configuration of timeouts.
     *
     * @return Configuration of timeouts.
     */
    ITimeoutConfiguration getTimeoutConfiguration();

    /**
     * Gets configuration of retries.
     *
     * @return Configuration of retries.
     */
    IRetryConfiguration getRetryConfiguration();

    /**
     * Gets configuration of logger.
     *
     * @return Configuration of logger.
     */
    ILoggerConfiguration getLoggerConfiguration();

    /**
     * Gets configuration of element caching.
     *
     * @return Configuration of element caching.
     */
    IElementCacheConfiguration getElementCacheConfiguration();
}