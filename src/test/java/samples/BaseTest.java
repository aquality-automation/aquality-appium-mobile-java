package samples;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.URL;

public abstract class BaseTest {
    private static AppiumDriverLocalService service;

    @BeforeSuite
    public void globalSetup () {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
    }

    @AfterSuite
    public void globalTearDown () {
        service.stop();
    }

    public URL getServiceUrl () {
        // todo: this is for remote run return new URL(String.format("http://%1$s:%2$s/wd/hub", "127.0.0.1", "4723"));
        return service.getUrl();
    }
}
