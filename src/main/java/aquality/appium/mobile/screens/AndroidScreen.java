package aquality.appium.mobile.screens;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

public abstract class AndroidScreen extends Screen<AndroidDriver<AndroidElement>> {

    /**
     * Constructor with parameters
     *
     * @param locator locator of specified screen
     * @param name name of specified screen
     */
    protected AndroidScreen(By locator, String name) {
        super(locator, name);
    }

    @Override
    protected PlatformName getPlatform() {
        return PlatformName.ANDROID;
    }

    protected void startActivity(Activity activity) {
        AqualityServices.getLocalizedLogger().info("loc.application.android.activity.start",
                        activity.getAppActivity(),
                        activity.getAppPackage());
        getDriver().startActivity(activity);
    }
}
