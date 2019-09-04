package aquality.appium.configuration;

public interface ITimeoutConfiguration {

    long getImplicit();

    long getCondition();

    long getPollingInterval();

    long getCommand();
}
