package aquality.appium.mobile.screens;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;

public class IOSScreen extends Screen {
    /**
     * Constructor with parameters
     *
     * @param locator locator of specified screen
     * @param name name of specified screen
     */
    protected IOSScreen(By locator, String name) {
        super(locator, name);
    }

    protected IOSDriver<IOSElement> getDriver(){
        ensureApplicationPlatformCorrect(PlatformName.IOS);
        return (IOSDriver<IOSElement>) AqualityServices.getApplication().getDriver();
    }
}
