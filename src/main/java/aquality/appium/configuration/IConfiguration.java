package aquality.appium.configuration;

import aquality.selenium.configuration.ILoggerConfiguration;
import aquality.selenium.configuration.IRetryConfiguration;

public interface IConfiguration {

    IApplicationProfile getApplicationProfile();

    ITimeoutConfiguration getTimeoutConfiguration();

    IRetryConfiguration getRetryConfiguration();

    ILoggerConfiguration getLoggerConfiguration();
}