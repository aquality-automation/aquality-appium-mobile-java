package aquality.appium.mobile.application;

import aquality.selenium.core.applications.IApplication;
import aquality.selenium.core.configurations.ITimeoutConfiguration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.appmanagement.ApplicationState;
import org.openqa.selenium.remote.service.DriverService;

import java.time.Duration;

public interface IMobileApplication extends IApplication {
    /**
     * Provides default timeout for terminate methods.
     * @return default timeout for waiting until the application is terminated.
     */
    static Duration getDefaultTerminateTimeout() {
        return AqualityServices.get(ITimeoutConfiguration.class).getCondition();
    }

    /**
     * Provides AppiumDriver instance for current application session.
     *
     * @return driver instance.
     */
    AppiumDriver getDriver();

    /**
     * Provides current AppiumDriver service instance (would be null if driver is not local)
     *
     * @return driver service instance
     */
    DriverService getDriverService();

    /**
     * Returns name of current platform
     *
     * @return name
     */
    PlatformName getPlatformName();

    /**
     * Executes appium driver quit, then stops the driver service
     */
    void quit();

    /**
     * Gets the bundle identifier (or appId) of the currently running application.
     *
     * @return id of the application in the foreground.
     */
    String getId();

    default InteractsWithApps appManagement() {
        return (InteractsWithApps) getDriver();
    }

    /**
     * Gets the state of the application.
     *
     * @param appId the bundle identifier (or appId) of the application.
     * @return an enumeration of the application state
     */
    ApplicationState getState(String appId);

    /**
     * Installs an application.
     *
     * @param appPath file path or url of the application.
     */
    void install(String appPath);

    /**
     * Installs an application defined in settings.
     * Note that path to the application must be defined as 'app' capability.
     */
    default void install() {
        install(AqualityServices.getApplicationProfile().getDriverSettings().getApplicationPath());
    }

    /**
     * Send the currently active application to the background,
     * and either return after a certain amount of time.
     *
     * @param timeout How long to background the application for.
     */
    void background(Duration timeout);

    /**
     * Send the currently active application to the background,
     * and leave the application deactivated (as "Home" button does).
     */
    default void background() {
        background(Duration.ofSeconds(-1));
    }

    /**
     * Removes an application.
     *
     * @param appId the bundle identifier (or appId) of the application to be removed.
     * @return true if the uninstallation was successful.
     */
    boolean remove(String appId);

    /**
     * Removes currently running application.
     *
     * @return true if the uninstallation was successful.
     */
    default boolean remove() {
        return remove(getId());
    }

    /**
     * Activates the given application by moving to the foreground if it is running in the background
     * or starting it if it is not running yet.
     *
     * @param appId the bundle identifier (or appId) of the application.
     */
    void activate(String appId);

    /**
     * Activates the given application by moving to the foreground if it is running in the background
     * or starting it if it is not running yet.
     *
     * @param appId   the bundle identifier (or appId) of the application.
     * @param timeout command timeout.
     */
    void activate(String appId, Duration timeout);

    /**
     * Terminate the particular application if it is running.
     *
     * @param appId   the bundle identifier (or appId) of the application to be terminated.
     * @param timeout defines for how long to wait until the application is terminated.
     * @return true if the application was running before and has been successfully stopped.
     */
    boolean terminate(String appId, Duration timeout);

    /**
     * Terminate the particular application if it is running.
     *
     * @param appId the bundle identifier (or appId) of the application to be terminated.
     * @return true if the application was running before and has been successfully stopped.
     */
    default boolean terminate(String appId) {
        return terminate(appId, getDefaultTerminateTimeout());
    }

    /**
     * Terminates currently running application.
     *
     * @param timeout defines for how long to wait until the application is terminated.
     * @return true if the application was running before and has been successfully stopped.
     */
    default boolean terminate(Duration timeout) {
        return terminate(getId(), timeout);
    }

    /**
     * Terminates currently running application.
     *
     * @return true if the application was running before and has been successfully stopped.
     */
    default boolean terminate() {
        return terminate(getDefaultTerminateTimeout());
    }
}
