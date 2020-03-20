package samples.android.web;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.MobileModule;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Listeners;
import testreport.ScreenshotListener;

@Listeners(ScreenshotListener.class)
public abstract class AndroidWebTest {

    @BeforeGroups("web")
    void setUp() {
        System.setProperty("profile", "androidwebsession");
        AqualityServices.initInjector(new MobileModule(AqualityServices::getApplication));
    }

    @AfterGroups("web")
    void tearDown() {
        AqualityServices.getApplication().quit();
        System.clearProperty("profile");
    }
}
