package aquality.appium.mobile.screens;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import io.appium.java_client.android.Activity;
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

    @Override
    protected AndroidDriver<AndroidElement> getDriver(){
        ensureApplicationPlatformCorrect(PlatformName.ANDROID);
        return (AndroidDriver<AndroidElement>) AqualityServices.getApplication().getDriver();
    }

    protected void startActivity(Activity activity) {
        AqualityServices.getLogger().info(
                String.format("Starting the '%s' activity of the android app at package '%s'",
                        activity.getAppActivity(),
                        activity.getAppPackage()));
        getDriver().startActivity(activity);
    }
}
