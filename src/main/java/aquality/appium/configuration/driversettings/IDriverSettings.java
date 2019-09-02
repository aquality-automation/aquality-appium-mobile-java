package aquality.appium.configuration.driversettings;

import aquality.appium.application.PlatformName;
import aquality.appium.utils.JsonFile;
import org.openqa.selenium.Capabilities;

public interface IDriverSettings {

    Capabilities getCapabilities();

    String getWebDriverVersion();

    String getSystemArchitecture();

    String getDownloadDir();

    String getDownloadDirCapabilityKey();

    PlatformName getPlatformName();

    JsonFile getSettingsFile();

}
