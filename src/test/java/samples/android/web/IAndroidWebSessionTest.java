package samples.android.web;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.MobileModule;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public interface IAndroidWebSessionTest {

    @BeforeClass
    default void setUp() {
        System.setProperty("profile", "androidwebsession");
        AqualityServices.initInjector(new MobileModule(AqualityServices::getApplication));
    }

    @AfterClass
    default void tearDown() {
        AqualityServices.getApplication().quit();
        System.clearProperty("profile");
    }
}
