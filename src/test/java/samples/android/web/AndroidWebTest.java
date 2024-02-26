package samples.android.web;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.MobileModule;
import org.testng.annotations.*;
import testreport.ScreenshotListener;

@Listeners(ScreenshotListener.class)
public abstract class AndroidWebTest {

    @BeforeClass
    void setUp() {
        System.setProperty("profile", "androidwebsession");
        AqualityServices.initInjector(new MobileModule(AqualityServices::getApplication));
    }

    @AfterClass
    void tearDown() {
        if (AqualityServices.isApplicationStarted()) {
            AqualityServices.getApplication().quit();
        }
        System.clearProperty("profile");
    }
}
