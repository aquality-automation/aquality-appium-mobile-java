package aquality.appium.mobile.configuration;

import aquality.appium.mobile.application.PlatformName;

import java.net.URL;

public interface IApplicationProfile {

    /**
     * Gets name of current application platform.
     * @return Current platform name.
     */
    PlatformName getPlatformName();

    /**
     * Checks if is remote appium server or not.
     * @return true if remote appium server and false if local.
     */
    boolean isRemote();

    /**
     * Gets appium driver settings for current platform.
     *
     * @return Driver settings.
     */
    IDriverSettings getDriverSettings();

    /**
     * Gets appium server URL.
     *
     * @return address of appium server.
     */
    URL getRemoteConnectionUrl();

    /**
     * Gets name of package with screens for ScreenFactory.
     *
     * @return name of package like *my.package.screens*
     */
    String getScreensLocation();
}
