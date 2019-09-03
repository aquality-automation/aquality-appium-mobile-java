package aquality.appium.configuration;

import aquality.appium.application.PlatformName;
import aquality.appium.configuration.driversettings.AndroidSettings;
import aquality.appium.configuration.driversettings.IDriverSettings;
import aquality.appium.configuration.driversettings.IOSSettings;
import aquality.appium.configuration.driversettings.WindowsSettings;
import aquality.selenium.utils.JsonFile;
import org.openqa.selenium.InvalidArgumentException;

import java.net.MalformedURLException;
import java.net.URL;

public class ApplicationProfile implements IApplicationProfile {

   private final JsonFile settingsFile;

    public ApplicationProfile(JsonFile settingsFile) {
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
    public boolean isElementHighlightEnabled() {
        // todo: define is it compatible/needed
        return (Boolean) (settingsFile.getValue("/isElementHighlightEnabled"));
    }

    @Override
    public IDriverSettings getDriverSettings() {
        IDriverSettings driverSettings;
        switch (getPlatformName()){
            case ANDROID:
                driverSettings = new AndroidSettings(settingsFile);
                break;
            case IOS:
                driverSettings = new IOSSettings(settingsFile);
                break;
            case WINDOWS:
                driverSettings = new WindowsSettings(settingsFile);
                break;
            default:
                throw new IllegalArgumentException("There are no assigned behaviour for retrieving driver driversettings for platform " + getPlatformName());
        }
        return driverSettings;
    }

    @Override
    public URL getRemoteConnectionUrl() {
        String key = "remoteConnectionUrl";
        try {
            return new URL(String.valueOf(settingsFile.getValue("/" + key)));
        } catch (MalformedURLException e) {
            throw new InvalidArgumentException(String.format("Key %1$s was not found in file %2$s", key, settingsFile.getFileCanonicalPath()));
        }
    }
}
