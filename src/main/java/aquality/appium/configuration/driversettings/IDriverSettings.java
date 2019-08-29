package aquality.appium.configuration.driversettings;

import aquality.appium.application.ApplicationPlatform;
import aquality.appium.utils.JsonFile;
import org.openqa.selenium.Capabilities;

public interface IDriverSettings {

    Capabilities getCapabilities();

    String getWebDriverVersion();

    String getSystemArchitecture();

    String getDownloadDir();

    String getDownloadDirCapabilityKey();

    ApplicationPlatform getApplicationPlatform();

    JsonFile getSettingsFile();

}
