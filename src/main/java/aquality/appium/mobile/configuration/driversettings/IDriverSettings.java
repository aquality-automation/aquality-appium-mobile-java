package aquality.appium.mobile.configuration.driversettings;

import aquality.appium.mobile.application.PlatformName;
import org.openqa.selenium.Capabilities;

/**
 * Describes AppiumDriver settings.
 */
public interface IDriverSettings {

    /**
     * Gets appium driver capabilities.
     * @return initialized {@link Capabilities}.
     */
    Capabilities getCapabilities();

    /**
     * Get desired platform name.
     * @return Platform name.
     */
    PlatformName getPlatformName();

    /**
     * Provides a path to the application.
     * @return path to application.
     */
    String getApplicationPath();
}
