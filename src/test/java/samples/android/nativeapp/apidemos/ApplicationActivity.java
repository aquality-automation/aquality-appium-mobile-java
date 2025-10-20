package samples.android.nativeapp.apidemos;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.Screen;
import aquality.selenium.core.configurations.ITimeoutConfiguration;
import io.appium.java_client.android.Activity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import samples.android.nativeapp.apidemos.screens.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

public enum ApplicationActivity {

    SEARCH(".app.SearchInvoke", InvokeSearchScreen.class),
    ALERT_DIALOGS(".app.AlertDialogSamples", AlertsMenuScreen.class),
    VIEW_CONTROLS(".view.Controls1", ViewControlsScreen.class),
    VIEW_TABS_SCROLLABLE(".view.Tabs5", ViewTabsScrollableScreen.class);

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
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            AqualityServices.getLogger().debug(e.getMessage());
            throw new IllegalArgumentException("Something went wrong during screen getting");
        }
    }

    private static class ActivityScreen extends AndroidScreen {
        private final Activity activity;
        private final IButton btnWait = getElementFactory().getButton(By.id("android:id/aerr_wait"), "Wait");
        private final IButton btnCloseApp = getElementFactory().getButton(By.id("android:id/aerr_close"), "Close app");

        ActivityScreen(Activity activity) {
            super(By.name(activity.getAppActivity()), activity.getAppActivity());
            this.activity = activity;
        }

        void open() {
            startActivity(activity);
            // workaround to handle System UI isn't responding dialog
            ITimeoutConfiguration timeoutConfiguration = AqualityServices.getConfiguration().getTimeoutConfiguration();
            boolean result = AqualityServices.getConditionalWait().waitFor(() ->
                    {
                        if (!btnWait.state().waitForDisplayed()) {
                            return true;
                        }
                        btnWait.click();
                        return btnWait.state().waitForNotDisplayed();
                    }, timeoutConfiguration.getCommand(),
                    timeoutConfiguration.getCondition(),
                    Collections.singletonList(WebDriverException.class));
            if (!result) {
                btnCloseApp.click();
            }
        }
    }
}
