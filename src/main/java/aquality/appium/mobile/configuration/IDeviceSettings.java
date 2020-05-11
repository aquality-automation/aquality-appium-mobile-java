package aquality.appium.mobile.configuration;

import org.openqa.selenium.Capabilities;

/**
 * Describes desired device settings.
 */
public interface IDeviceSettings {

    /**
     * Capabilities related to desired device.
     * @return initialized {@link Capabilities}.
     */
    Capabilities getCapabilities();
}
