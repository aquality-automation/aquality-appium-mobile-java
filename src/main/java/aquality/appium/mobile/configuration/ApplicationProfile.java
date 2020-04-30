package aquality.appium.mobile.configuration;

import aquality.appium.mobile.application.PlatformName;
import aquality.selenium.core.utilities.ISettingsFile;
import com.google.inject.Inject;
import org.openqa.selenium.InvalidArgumentException;

import java.net.MalformedURLException;
import java.net.URL;

public class ApplicationProfile implements IApplicationProfile {

    private final ISettingsFile settingsFile;

    @Inject
    public ApplicationProfile(ISettingsFile settingsFile) {
        this.settingsFile = settingsFile;
    }

    @Override
    public PlatformName getPlatformName() {
        return PlatformName.valueOf(String.valueOf(settingsFile.getValue("/platformName")).toUpperCase());
    }

    @Override
    public boolean isRemote() {
        return (Boolean) settingsFile.getValue("/isRemote");
    }

    @Override
    public IDriverSettings getDriverSettings() {
        return new DriverSettings(settingsFile, getPlatformName());
    }

    @Override
    public URL getRemoteConnectionUrl() {
        final String key = "remoteConnectionUrl";
        String address = String.valueOf(settingsFile.getValue("/" + key));
        try {
            return new URL(address);
        } catch (MalformedURLException e) {
            throw new InvalidArgumentException(String.format("Address %1$s provided in configuration by key %2$s is not valid", address, key));
        }
    }

    @Override
    public String getScreensLocation() {
        return (String) settingsFile.getValue("/screensLocation");
    }
}
