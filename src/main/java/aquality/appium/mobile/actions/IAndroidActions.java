package aquality.appium.mobile.actions;

import io.appium.java_client.android.Activity;

/**
 * Extensions for Android applications.
 */
public interface IAndroidActions {

    /**
     * Starts application activity.
     * @param activity Activity to start.
     * @param stopApp True if you need to stop currently running application, false otherwise.
     */
    default void startActivity(Activity activity, boolean stopApp) {
        startActivity(activity.getAppPackage(), activity.getAppActivity(), stopApp);
    }

    /**
     * Starts application activity.
     * Currently running application will be stopped.
     * @param appPackage Package of the target application.
     * @param appActivity Target activity.
     */
    default void startActivity(String appPackage, String appActivity) {
        startActivity(appPackage, appActivity, true);
    }

    /**
     * Starts application activity.
     * @param appPackage Package of the target application.
     * @param appActivity Target activity.
     * @param stopApp True if you need to stop currently running application, false otherwise.
     */
    void startActivity(String appPackage, String appActivity, boolean stopApp);
}
