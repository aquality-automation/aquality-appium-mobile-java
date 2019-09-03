package aquality.appium.configuration.driversettings;

import aquality.selenium.localization.LocalizationManager;
import aquality.selenium.logger.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.Map;

abstract class DriverSettings implements IDriverSettings {

    private static final String appPathKey = "applicationPath";
    private static final String appCapabilityKey = "app";

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

    Map<String, Object> getCapabilitiesFromSettings(){
        return getSettingsFile().getMap(getDriverCapabilitiesJsonPath());
    }

    private String getAbsolutePath(String relativePath) {
        try {
            return new File(relativePath).getCanonicalPath();
        } catch (IOException e) {
            String message = String.format(LocalizationManager.getInstance().getValue("loc.file.reading_exception"),
                    relativePath);
            getLogger().fatal(message, e);
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public boolean hasApplicationPath() {
        return getSettingsFile().getMap(getDriverSettingsPath()).containsKey(appPathKey);
    }

    @Override
    public String getApplicationPath() {
        return String.valueOf(getSettingsFile().getValue(getDriverSettingsPath() + "/" + appPathKey));
    }

    private String getDriverCapabilitiesJsonPath(){
        return getDriverSettingsPath() + "/" + "capabilities";
    }

    private String getDriverSettingsPath(){
        return String.format("/driverSettings/%1$s", getPlatformName().toString().toLowerCase());
    }

    private Logger getLogger(){
        return Logger.getInstance();
    }
}
