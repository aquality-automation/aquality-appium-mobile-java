package aquality.appium.mobile.configuration.driversettings;

import aquality.appium.mobile.application.PlatformName;
import aquality.selenium.core.utilities.ISettingsFile;

public class IOSSettings extends DriverSettings{

    public IOSSettings(ISettingsFile settingsFile){
        super(settingsFile);
    }

    @Override
    public PlatformName getPlatformName() {
        return PlatformName.IOS;
    }
}
