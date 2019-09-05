package aquality.appium.screens;

import aquality.appium.application.ApplicationManager;
import aquality.appium.application.PlatformName;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

public class AndroidScreen extends Screen {
    /**
     * Constructor with parameters
     *
     * @param locator locator of specified screen
     * @param name name of specified screen
     */
    protected AndroidScreen(By locator, String name) {
        super(locator, name);
    }

    protected AndroidDriver<AndroidElement> getDriver(){
        ensureApplicationPlatformCorrect(PlatformName.ANDROID);
        return (AndroidDriver<AndroidElement>) ApplicationManager.getApplication().getDriver();
    }
}
