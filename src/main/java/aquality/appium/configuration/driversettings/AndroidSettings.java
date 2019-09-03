package aquality.appium.configuration.driversettings;

import aquality.appium.application.PlatformName;
import aquality.appium.utils.JsonFile;

public class AndroidSettings extends DriverSettings{

    private final JsonFile jsonFile;

    public AndroidSettings(JsonFile jsonFile){
        this.jsonFile = jsonFile;
    }

    @Override
    public PlatformName getPlatformName() {
        return PlatformName.ANDROID;
    }

    @Override
    public JsonFile getSettingsFile() {
        return jsonFile;
    }
}
