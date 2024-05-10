package aquality.appium.mobile.application;

import aquality.appium.mobile.configuration.IApplicationProfile;
import aquality.selenium.core.configurations.ITimeoutConfiguration;
import aquality.selenium.core.localization.ILocalizedLogger;
import aquality.selenium.core.utilities.IActionRetrier;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.CommandExecutionHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.appmanagement.ApplicationState;
import io.appium.java_client.appmanagement.BaseActivateApplicationOptions;
import io.appium.java_client.appmanagement.BaseTerminateApplicationOptions;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.service.DriverService;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class Application implements IMobileApplication {

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

    @Override
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    @Override
    public boolean isStarted() {
        return appiumDriver.getSessionId() != null;
    }

    @Override
    public DriverService getDriverService() {
        return driverService;
    }

    @Override
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

    @Override
    public void quit() {
        localizedLogger.info("loc.application.quit");
        if (getDriver() != null) {
            getDriver().quit();
        }

        if (getDriverService() != null) {
            getDriverService().stop();
        }
    }

    /**
     * Retries application actions.
     * @param function result supplier.
     * @return result of the function executed.
     * @param <T> type of the result.
     */
    protected <T> T doWithRetry(Supplier<T> function) {
        return AqualityServices.get(IActionRetrier.class)
                .doWithRetry(function, Collections.singletonList(WebDriverException.class));
    }

    /**
     * Retries application actions.
     * @param function runnable function.
     */
    protected void doWithRetry(Runnable function) {
        AqualityServices.get(IActionRetrier.class)
                .doWithRetry(function, Collections.singletonList(WebDriverException.class));
    }

    @Override
    public String getId() {
        final String iosExtName = "mobile: activeAppInfo";
        return doWithRetry(() -> {
            if (PlatformName.ANDROID == getPlatformName()) {
                return ((AndroidDriver) getDriver()).getCurrentPackage();
            }
            Map<String, Object> result = CommandExecutionHelper.executeScript(getDriver().assertExtensionExists(iosExtName), iosExtName);
            return String.valueOf(Objects.requireNonNull(result).get("bundleId"));
        });
    }

    @Override
    public ApplicationState getState(String appId) {
        localizedLogger.info("loc.application.get.state", appId);
        ApplicationState state = doWithRetry(() -> appManagement().queryAppState(appId));
        localizedLogger.info("loc.application.state", state);
        return state;
    }

    @Override
    public void install(String appPath) {
        localizedLogger.info("loc.application.install", appPath);
        doWithRetry(() -> appManagement().installApp(appPath));
    }

    @Override
    public void background(Duration timeout) {
        localizedLogger.info("loc.application.background");
        doWithRetry(() -> appManagement().runAppInBackground(timeout));
    }

    @Override
    public boolean remove(String appId) {
        localizedLogger.info("loc.application.remove", appId);
        return doWithRetry(() -> appManagement().removeApp(appId));
    }

    @Override
    public void activate(String appId) {
        localizedLogger.info("loc.application.activate", appId);
        doWithRetry(() -> appManagement().activateApp(appId));
    }

    @Override
    public void activate(String appId, Duration timeout) {
        localizedLogger.info("loc.application.activate", appId);
        class Options extends BaseActivateApplicationOptions<Options> {
            @Override
            public Map<String, Object> build() {
                return Collections.singletonMap("timeout", timeout.toMillis());
            }
        }
        doWithRetry(() -> appManagement().activateApp(appId, new Options()));
    }

    @Override
    public boolean terminate(String appId, Duration timeout) {
        localizedLogger.info("loc.application.terminate", appId);
        class Options extends BaseTerminateApplicationOptions<Options> {
            @Override
            public Map<String, Object> build() {
                return Collections.singletonMap("timeout", timeout.toMillis());
            }
        }
        return doWithRetry(() -> appManagement().terminateApp(appId, new Options()));
    }
}
