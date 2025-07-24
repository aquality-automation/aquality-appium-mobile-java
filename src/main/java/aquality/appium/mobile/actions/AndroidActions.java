package aquality.appium.mobile.actions;

import aquality.appium.mobile.application.AqualityServices;
import io.appium.java_client.android.AndroidDriver;

import java.util.HashMap;
import java.util.Map;

public class AndroidActions implements IAndroidActions {
    @Override
    public void startActivity(String appPackage, String appActivity, boolean stopApp) {
        AqualityServices.getLocalizedLogger().info("loc.application.android.activity.start", appPackage, appActivity);
        Map<String, Object> args = new HashMap<>();
        args.put("intent", String.format("%s/%s", appPackage, appActivity));
        args.put("package", appPackage);
        args.put("stop", stopApp);
        AqualityServices.getApplication().executeScript("mobile: startActivity", args);
        AqualityServices.getConditionalWait().waitFor(
                driver -> appActivity.equals(((AndroidDriver)driver).currentActivity()),
                String.format("Activity %s was not started", appActivity));
    }
}
