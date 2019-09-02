package aquality.appium.configuration.driversettings;

import aquality.appium.application.PlatformName;
import aquality.appium.utils.JsonFile;
import org.openqa.selenium.Capabilities;

public interface IDriverSettings {

    Capabilities getCapabilities();

    PlatformName getPlatformName();

    boolean hasApplicationPath();

    String getApplicationPath();

    JsonFile getSettingsFile();

}
