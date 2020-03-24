package aquality.appium.mobile.application;

import aquality.appium.mobile.configuration.ILocalServiceSettings;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class LocalApplicationFactory extends ApplicationFactory {

    @Override
    public Application getApplication() {
        ILocalServiceSettings settings = AqualityServices.getLocalServiceSettings();
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withCapabilities(settings.getCapabilities());
        settings.getArguments().forEach(builder::withArgument);
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
        service.start();
        AppiumDriver driver = getDriver(service.getUrl());
        logApplicationIsReady();
        return new Application(driver, service);
    }
}
