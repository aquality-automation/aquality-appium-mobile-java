package aquality.appium.application;

import aquality.appium.configuration.IApplicationProfile;
import aquality.appium.configuration.IConfiguration;
import aquality.selenium.localization.LocalizationManager;
import aquality.selenium.logger.Logger;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.LocalFileDetector;

public class RemoteApplicationFactory extends ApplicationFactory {

    private final IConfiguration configuration;

    public RemoteApplicationFactory(IConfiguration configuration){
        this.configuration = configuration;
    }

    @Override
    public Application getApplication() {
        PlatformName platformName = configuration.getApplicationProfile().getPlatformName();
        IApplicationProfile applicationProfile = configuration.getApplicationProfile();
        Logger.getInstance().info(LocalizationManager.getInstance().getValue("loc.browser.grid"));
        AppiumDriver driver = getDriver(applicationProfile.getRemoteConnectionUrl(), configuration);
        driver.setFileDetector(new LocalFileDetector());
        logApplicationIsReady(platformName);
        return new Application(driver, configuration);
    }
}
