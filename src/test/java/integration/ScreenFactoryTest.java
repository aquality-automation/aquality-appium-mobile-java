package integration;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.MobileModule;
import integration.screens.AndroidLoginScreen;
import integration.screens.FakeLoginScreen;
import integration.screens.IOSLoginScreen;
import integration.screens.LoginScreen;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class ScreenFactoryTest {

    private static final String PLATFORM_NAME_VARIABLE_NAME = "platformName";
    private static final String SCREENS_LOCATION_VARIABLE_NAME = "screensLocation";

    @Test
    public void shouldBePossibleToGetScreenViaFactory() {
        Assert.assertNotNull(AqualityServices.getScreenFactory().getScreen(LoginScreen.class));
    }

    @Test
    public void shouldBePossibleToGetAndroidScreenViaFactory() {
        System.setProperty(PLATFORM_NAME_VARIABLE_NAME, "android");
        AqualityServices.initInjector(new MobileModule(AqualityServices::getApplication));
        Assert.assertTrue(AqualityServices.getScreenFactory().getScreen(LoginScreen.class) instanceof AndroidLoginScreen);
    }

    @Test
    public void shouldBePossibleToGetIOSScreenViaFactory() {
        System.setProperty(PLATFORM_NAME_VARIABLE_NAME, "ios");
        AqualityServices.initInjector(new MobileModule(AqualityServices::getApplication));
        Assert.assertTrue(AqualityServices.getScreenFactory().getScreen(LoginScreen.class) instanceof IOSLoginScreen);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionOnNotImplementedScreenInterface() {
        Assert.assertThrows(IllegalArgumentException.class, () -> AqualityServices.getScreenFactory().getScreen(FakeLoginScreen.class));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionOnNotValidScreensLocationValue()
    {
        System.setProperty(SCREENS_LOCATION_VARIABLE_NAME, "fake.package");
        AqualityServices.initInjector(new MobileModule(AqualityServices::getApplication));
        Assert.assertThrows(IllegalArgumentException.class, () -> AqualityServices.getScreenFactory().getScreen(LoginScreen.class));
    }

    @AfterClass
    public void afterAll() {
        System.clearProperty(PLATFORM_NAME_VARIABLE_NAME);
        System.clearProperty(SCREENS_LOCATION_VARIABLE_NAME);
    }
}


