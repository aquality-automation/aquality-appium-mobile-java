package aquality.appium.mobile.screens;

import aquality.appium.mobile.application.PlatformName;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;

public abstract class IOSScreen extends Screen<IOSDriver<IOSElement>> {

    /**
     * Constructor with parameters
     *
     * @param locator locator of specified screen
     * @param name name of specified screen
     */
    protected IOSScreen(By locator, String name) {
        super(locator, name);
    }

    @Override
    protected PlatformName getPlatform() {
        return PlatformName.IOS;
    }
}
