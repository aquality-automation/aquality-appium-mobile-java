package samples.android.nativeapp.apidemos.screens;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.screens.Screen;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public abstract class AndroidScreen extends Screen {

    protected AndroidScreen(By locator, String name) {
        super(locator, name);
    }

    protected void startActivity(Activity activity) {
        AqualityServices.getLocalizedLogger().info("loc.application.android.activity.start",
                activity.getAppActivity(),
                activity.getAppPackage());
        AndroidDriver driver = (AndroidDriver) AqualityServices.getApplication().getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("intent", activity.getAppPackage() + "/" + activity.getAppActivity());
        params.put("package", activity.getAppPackage());
        driver.executeScript("mobile: startActivity", params);
    }
}
