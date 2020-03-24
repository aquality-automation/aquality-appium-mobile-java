package aquality.appium.mobile.configuration;

import io.appium.java_client.service.local.flags.ServerArgument;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

/**
 * Describes AppiumDriverLocalService settings.
 */
public interface ILocalServiceSettings {
    /**
     * Gets appium driver local service arguments.
     * @return arguments map.
     */
    Map<ServerArgument, String> getArguments();


    /**
     * Gets appium driver local service desired capabilities.
     * @return initialized {@link DesiredCapabilities}.
     */
    DesiredCapabilities getCapabilities();
}
