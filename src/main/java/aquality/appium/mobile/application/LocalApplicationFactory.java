package aquality.appium.mobile.application;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class LocalApplicationFactory extends ApplicationFactory {

    @Override
    public Application getApplication() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withArgument(() -> "--allow-insecure", " chromedriver_autodownload");
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
        service.start();
        AppiumDriver driver = getDriver(service.getUrl());
        logApplicationIsReady();
        return new Application(driver, service);
    }
}
