package aquality.appium.configuration;

import aquality.appium.application.ApplicationPlatform;
import aquality.appium.configuration.driversettings.IDriverSettings;

import java.net.URL;

public interface IApplicationProfile {

    ApplicationPlatform getApplicationName();

    boolean isRemote();

    boolean isElementHighlightEnabled();

    IDriverSettings getDriverSettings();

    URL getRemoteConnectionUrl();
}
