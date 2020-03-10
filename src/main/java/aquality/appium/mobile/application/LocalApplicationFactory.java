package aquality.appium.mobile.application;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class LocalApplicationFactory extends ApplicationFactory {

    @Override
    public Application getApplication() {
        AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        AppiumDriver driver = getDriver(service.getUrl());
        logApplicationIsReady();
        return new Application(driver, service);
    }
}
