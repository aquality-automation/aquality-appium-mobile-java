package aquality.appium.mobile.configuration.driversettings;

import aquality.appium.mobile.application.AqualityServices;
import aquality.selenium.core.localization.ILocalizationManager;
import aquality.selenium.core.utilities.ISettingsFile;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.Map;

abstract class DriverSettings implements IDriverSettings {

    private static final String appPathKey = "applicationPath";
    private static final String appCapabilityKey = "app";
    private final ISettingsFile settingsFile;

    DriverSettings(ISettingsFile settingsFile) {
        this.settingsFile = settingsFile;
    }

    @Override
    public Capabilities getCapabilities() {
        Map<String, Object> capabilitiesFromSettings = getCapabilitiesFromSettings();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilitiesFromSettings.forEach(capabilities::setCapability);
        if(hasApplicationPath()) {
            capabilities.setCapability(appCapabilityKey, getAbsolutePath(getApplicationPath()));
        }
        return capabilities;
    }

    private Map<String, Object> getCapabilitiesFromSettings(){
        return settingsFile.getMap(getDriverCapabilitiesJsonPath());
    }

    private String getAbsolutePath(String relativePath) {
        try {
            return new File(relativePath).getCanonicalPath();
        } catch (IOException e) {
            String message = String.format(AqualityServices.get(ILocalizationManager.class).getLocalizedMessage("loc.file.reading_exception"),
                    relativePath);
            AqualityServices.getLogger().fatal(message, e);
            throw new IllegalArgumentException(message);
        }
    }

    private boolean hasApplicationPath() {
        return settingsFile.getMap(getDriverSettingsPath()).containsKey(appPathKey);
    }

    @Override
    public String getApplicationPath() {
        return String.valueOf(settingsFile.getValue(getDriverSettingsPath() + "/" + appPathKey));
    }

    private String getDriverCapabilitiesJsonPath(){
        return getDriverSettingsPath() + "/capabilities";
    }

    private String getDriverSettingsPath(){
        return String.format("/driverSettings/%1$s", getPlatformName().toString().toLowerCase());
    }
}
