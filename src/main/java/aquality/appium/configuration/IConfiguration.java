package aquality.appium.configuration;

import aquality.selenium.configuration.ILoggerConfiguration;
import aquality.selenium.configuration.IRetryConfiguration;
import aquality.selenium.configuration.ITimeoutConfiguration;

public interface IConfiguration {

    IApplicationProfile getApplicationProfile();

    ITimeoutConfiguration getTimeoutConfiguration();

    IRetryConfiguration getRetryConfiguration();

    ILoggerConfiguration getLoggerConfiguration();
}