package aquality.appium.mobile.screens;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.By;

public class WindowsScreen extends Screen {
    /**
     * Constructor with parameters
     *
     * @param locator locator of specified screen
     * @param name name of specified screen
     */
    protected WindowsScreen(By locator, String name) {
        super(locator, name);
    }

    protected WindowsDriver<WindowsElement> getDriver(){
        ensureApplicationPlatformCorrect(PlatformName.WINDOWS);
        return (WindowsDriver<WindowsElement>) AqualityServices.getApplication().getDriver();
    }
}
