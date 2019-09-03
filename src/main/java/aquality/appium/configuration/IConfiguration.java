package aquality.appium.configuration;

public interface IConfiguration {

    IApplicationProfile getApplicationProfile();

    ITimeoutConfiguration getTimeoutConfiguration();

    IRetryConfiguration getRetryConfiguration();

    ILoggerConfiguration getLoggerConfiguration();
}