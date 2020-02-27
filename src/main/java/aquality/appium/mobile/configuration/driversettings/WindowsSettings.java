package aquality.appium.mobile.configuration.driversettings;

import aquality.appium.mobile.application.PlatformName;
import aquality.selenium.core.utilities.ISettingsFile;

public class WindowsSettings extends DriverSettings{

    private final ISettingsFile settingsFile;

    public WindowsSettings(ISettingsFile settingsFile){
        this.settingsFile = settingsFile;
    }

    @Override
    public PlatformName getPlatformName() {
        return PlatformName.WINDOWS;
    }

    @Override
    protected ISettingsFile getSettingsFile() {
        return settingsFile;
    }
}
