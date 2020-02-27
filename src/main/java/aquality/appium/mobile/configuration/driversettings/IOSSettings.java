package aquality.appium.mobile.configuration.driversettings;

import aquality.appium.mobile.application.PlatformName;
import aquality.selenium.core.utilities.ISettingsFile;

public class IOSSettings extends DriverSettings{

    private final ISettingsFile settingsFile;

    public IOSSettings(ISettingsFile settingsFile){
        this.settingsFile = settingsFile;
    }

    @Override
    public PlatformName getPlatformName() {
        return PlatformName.IOS;
    }

    @Override
    protected ISettingsFile getSettingsFile() {
        return settingsFile;
    }
}
