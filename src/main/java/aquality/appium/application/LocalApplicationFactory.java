package aquality.appium.application;

import aquality.appium.configuration.IConfiguration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class LocalApplicationFactory extends ApplicationFactory {
    private final IConfiguration configuration;

    public LocalApplicationFactory(IConfiguration configuration){
        this.configuration = configuration;
    }

    @Override
    public Application getApplication() {
        PlatformName platformName = configuration.getApplicationProfile().getPlatformName();
        AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        AppiumDriver driver = getDriver(service.getUrl(), configuration);
        logApplicationIsReady(platformName);
        return new Application(service, driver, configuration);
    }
}
