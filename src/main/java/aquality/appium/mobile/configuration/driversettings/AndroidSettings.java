package aquality.appium.mobile.configuration.driversettings;

import aquality.appium.mobile.application.PlatformName;
import aquality.selenium.core.utilities.ISettingsFile;

public class AndroidSettings extends DriverSettings{

    private final ISettingsFile settingsFile;

    public AndroidSettings(ISettingsFile settingsFile){
        this.settingsFile = settingsFile;
    }

    @Override
    public PlatformName getPlatformName() {
        return PlatformName.ANDROID;
    }

    @Override
    protected ISettingsFile getSettingsFile() {
        return settingsFile;
    }
}
