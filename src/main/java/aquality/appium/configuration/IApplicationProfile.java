package aquality.appium.configuration;

import aquality.appium.application.PlatformName;
import aquality.appium.configuration.driversettings.IDriverSettings;

import java.net.URL;

public interface IApplicationProfile {

    PlatformName getPlatformName();

    boolean isRemote();

    boolean isElementHighlightEnabled();

    IDriverSettings getDriverSettings();

    URL getRemoteConnectionUrl();
}
