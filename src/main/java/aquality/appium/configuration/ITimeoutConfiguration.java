package aquality.appium.configuration;

public interface ITimeoutConfiguration {

    long getImplicit();

    long getCondition();

    long getScript();

    long getPageLoad();

    long getPollingInterval();

}
