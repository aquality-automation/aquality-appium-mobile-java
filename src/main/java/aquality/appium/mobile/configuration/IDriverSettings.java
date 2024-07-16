package aquality.appium.mobile.configuration;

import io.appium.java_client.remote.options.BaseOptions;

/**
 * Describes AppiumDriver settings.
 */
public interface IDriverSettings {

    /**
     * Gets appium driver capabilities.
     * @return initialized {@link BaseOptions}.
     */
    BaseOptions<?> getCapabilities();

    /**
     * Provides a path to the application.
     * @return path to application.
     */
    String getApplicationPath();

    /**
     * Provides the bundleId/appPackage of the application.
     *
     * @return application id.
     */
    String getBundleId();
}
