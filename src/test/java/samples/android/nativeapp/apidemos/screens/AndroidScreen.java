package samples.android.nativeapp.apidemos.screens;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.screens.Screen;
import io.appium.java_client.android.Activity;
import org.openqa.selenium.By;

public abstract class AndroidScreen extends Screen {

    protected AndroidScreen(By locator, String name) {
        super(locator, name);
    }

    protected void startActivity(Activity activity) {
        AqualityServices.getAndroidActions().startActivity(activity.getAppPackage(), activity.getAppActivity(), false);
    }
}
