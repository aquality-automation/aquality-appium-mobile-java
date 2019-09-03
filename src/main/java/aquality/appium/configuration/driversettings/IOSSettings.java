package aquality.appium.configuration.driversettings;

import aquality.appium.application.PlatformName;
import aquality.selenium.utils.JsonFile;

public class IOSSettings extends DriverSettings{

    private final JsonFile jsonFile;

    public IOSSettings(JsonFile jsonFile){
        this.jsonFile = jsonFile;
    }

    @Override
    public PlatformName getPlatformName() {
        return PlatformName.IOS;
    }

    @Override
    public JsonFile getSettingsFile() {
        return jsonFile;
    }
}
