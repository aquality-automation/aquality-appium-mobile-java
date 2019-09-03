package aquality.appium.configuration.driversettings;

import aquality.appium.application.PlatformName;
import aquality.appium.utils.JsonFile;

public class WindowsSettings extends DriverSettings{

    private final JsonFile jsonFile;

    public WindowsSettings(JsonFile jsonFile){
        this.jsonFile = jsonFile;
    }

    @Override
    public PlatformName getPlatformName() {
        return PlatformName.WINDOWS;
    }

    @Override
    public JsonFile getSettingsFile() {
        return jsonFile;
    }
}
