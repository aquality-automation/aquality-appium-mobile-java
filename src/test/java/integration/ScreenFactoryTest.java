package integration;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.MobileModule;
import aquality.appium.mobile.screens.AndroidScreen;
import aquality.appium.mobile.screens.IOSScreen;
import integration.screens.IFakeLoginScreen;
import integration.screens.ILoginScreen;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class ScreenFactoryTest {

    private static final String PLATFORM_NAME_VARIABLE_NAME = "platformName";
    private static final String SCREENS_LOCATION_VARIABLE_NAME = "screensLocation";

    @Test
    public void shouldBePossibleToGetScreenViaFactory() {
        Assert.assertNotNull(AqualityServices.getScreenFactory().getScreen(ILoginScreen.class));
    }

    @Test
    public void shouldBePossibleToGetAndroidScreenViaFactory() {
        System.setProperty(PLATFORM_NAME_VARIABLE_NAME, "android");
        AqualityServices.initInjector(new MobileModule(AqualityServices::getApplication));
        Assert.assertTrue(AqualityServices.getScreenFactory().getScreen(ILoginScreen.class) instanceof AndroidScreen);
    }

    @Test
    public void shouldBePossibleToGetIOSScreenViaFactory() {
        System.setProperty(PLATFORM_NAME_VARIABLE_NAME, "ios");
        AqualityServices.initInjector(new MobileModule(AqualityServices::getApplication));
        Assert.assertTrue(AqualityServices.getScreenFactory().getScreen(ILoginScreen.class) instanceof IOSScreen);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionOnNotImplementedScreenInterface() {
        Assert.assertThrows(IllegalArgumentException.class, () -> AqualityServices.getScreenFactory().getScreen(IFakeLoginScreen.class));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionOnNotValidScreensLocationValue()
    {
        System.setProperty(SCREENS_LOCATION_VARIABLE_NAME, "fake.package");
        AqualityServices.initInjector(new MobileModule(AqualityServices::getApplication));
        Assert.assertThrows(IllegalArgumentException.class, () -> AqualityServices.getScreenFactory().getScreen(ILoginScreen.class));
    }

    @AfterClass
    public void afterAll() {
        System.clearProperty(PLATFORM_NAME_VARIABLE_NAME);
        System.clearProperty(SCREENS_LOCATION_VARIABLE_NAME);
    }
}


