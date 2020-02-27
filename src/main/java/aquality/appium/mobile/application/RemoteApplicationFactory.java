package aquality.appium.mobile.application;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.LocalFileDetector;

public class RemoteApplicationFactory extends ApplicationFactory {

    @Override
    public Application getApplication() {
        AqualityServices.getLocalizedLogger().info("loc.browser.grid"); //todo: change key
        AppiumDriver driver = getDriver(AqualityServices.getApplicationProfile().getRemoteConnectionUrl());
        driver.setFileDetector(new LocalFileDetector());
        logApplicationIsReady();
        return new Application(driver);
    }
}
