package samples.android.nativeapp.apidemos;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.screens.Screen;
import io.appium.java_client.android.Activity;
import org.openqa.selenium.By;
import samples.android.nativeapp.apidemos.screens.AlertsMenuScreen;
import samples.android.nativeapp.apidemos.screens.AndroidScreen;
import samples.android.nativeapp.apidemos.screens.InvokeSearchScreen;
import samples.android.nativeapp.apidemos.screens.ViewControlsScreen;

import java.lang.reflect.InvocationTargetException;

public enum ApplicationActivity {

    SEARCH(".app.SearchInvoke", InvokeSearchScreen.class),
    ALERT_DIALOGS(".app.AlertDialogSamples", AlertsMenuScreen.class),
    VIEW_CONTROLS(".view.Controls1", ViewControlsScreen.class);

    private static final String PACKAGE = "io.appium.android.apis";

    private final String activity;
    private final Class<? extends Screen> screen;

    ApplicationActivity(String activity, Class<? extends AndroidScreen> screen) {
        this.activity = activity;
        this.screen = screen;
    }

    public <T extends AndroidScreen> T open() {
        new ActivityScreen(new Activity(PACKAGE, activity).setStopApp(false)).open();
        return getScreen();
    }

    @SuppressWarnings("unchecked")
    public <T extends AndroidScreen> T getScreen() {
        try {
            return (T) screen.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            AqualityServices.getLogger().debug(e.getMessage());
            throw new IllegalArgumentException("Something went wrong during screen getting");
        }
    }

    private static class ActivityScreen extends AndroidScreen {
        private final Activity activity;

        ActivityScreen(Activity activity) {
            super(By.name(activity.getAppActivity()), activity.getAppActivity());
            this.activity = activity;
        }

        void open() {
            startActivity(activity);
        }
    }
}
