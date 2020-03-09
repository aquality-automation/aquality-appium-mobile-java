package aquality.appium.mobile.configuration;

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
     * Provides a path to the application.
     * @return path to application.
     */
    String getApplicationPath();
}
