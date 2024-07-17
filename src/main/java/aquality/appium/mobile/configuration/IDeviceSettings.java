package aquality.appium.mobile.configuration;

import io.appium.java_client.remote.options.BaseOptions;

/**
 * Describes desired device settings.
 */
public interface IDeviceSettings {

    /**
     * Capabilities related to desired device.
     * @return initialized {@link BaseOptions}.
     */
    BaseOptions<?> getCapabilities();
}
