package integration;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.configuration.DeviceSettings;
import aquality.appium.mobile.configuration.IApplicationProfile;
import aquality.appium.mobile.configuration.IDeviceSettings;
import org.openqa.selenium.Capabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class DeviceSettingsTest {

    private static final String PLATFORM_NAME_PROPERTY_KEY = "platformName";
    private static final String DEVICES_PROFILE_PROPERTY_KEY = "devicesProfile";
    private static final String DEVICE_KEY_PROPERTY_KEY = "driverSettings.android.deviceKey";

    @Test
    public void testShouldBePossibleToGetDeviceCapabilities() {
        IDeviceSettings deviceSettings = new DeviceSettings("iPhone_11");
        Capabilities capabilities = deviceSettings.getCapabilities();
        Assert.assertNotNull(capabilities);
        Assert.assertFalse(capabilities.getCapabilityNames().isEmpty());
    }

    @Test
    public void testShouldBePossibleToGetEmptyCapabilitiesWhenDeviceKeyIsNull() {
        IDeviceSettings deviceSettings = new DeviceSettings(null);
        Capabilities capabilities = deviceSettings.getCapabilities();
        Assert.assertNotNull(capabilities);
        Assert.assertTrue(capabilities.getCapabilityNames().isEmpty());
    }

    @Test
    public void testShouldBePossibleToUseDifferentDevicesProfiles() {
        System.setProperty(DEVICES_PROFILE_PROPERTY_KEY, "test");
        IDeviceSettings testDeviceSettings = new DeviceSettings("iPhone_11");
        Capabilities testCapabilities = testDeviceSettings.getCapabilities();
        Assert.assertEquals("iPhone 11 test", testCapabilities.getCapability("deviceName"));

        System.clearProperty(DEVICES_PROFILE_PROPERTY_KEY);
        IDeviceSettings deviceSettings = new DeviceSettings("iPhone_11");
        Capabilities capabilities = deviceSettings.getCapabilities();
        Assert.assertEquals("iPhone 11", capabilities.getCapability("deviceName"));
    }

    @Test
    public void testShouldBePossibleToGetDefaultDeviceSettingsForIosPlatform() {
        System.setProperty(PLATFORM_NAME_PROPERTY_KEY, "ios");
        System.setProperty(DEVICE_KEY_PROPERTY_KEY, "iPhone_11");
        Capabilities capabilities = AqualityServices.get(IApplicationProfile.class).getDriverSettings().getCapabilities();
        Assert.assertEquals("iPhone 11", capabilities.getCapability("deviceName"));
    }

    @Test
    public void testShouldBePossibleToGetDefaultDeviceSettingsForAndroidPlatform() {
        System.setProperty(PLATFORM_NAME_PROPERTY_KEY, "android");
        System.setProperty(DEVICE_KEY_PROPERTY_KEY, "Nexus");
        Capabilities capabilities = AqualityServices.get(IApplicationProfile.class).getDriverSettings().getCapabilities();
        Assert.assertEquals("Nexus", capabilities.getCapability("deviceName"));
    }

    @Test
    public void testShouldBePossibleToOverrideDefaultDevice() {
        System.setProperty(PLATFORM_NAME_PROPERTY_KEY, "android");
        System.setProperty(DEVICE_KEY_PROPERTY_KEY, "Samsung_Galaxy");
        Capabilities capabilities = AqualityServices.get(IApplicationProfile.class).getDriverSettings().getCapabilities();
        Assert.assertEquals("Samsung Galaxy", capabilities.getCapability("deviceName"));
    }

    @AfterClass
    public void afterAll() {
        System.clearProperty(DEVICES_PROFILE_PROPERTY_KEY);
        System.clearProperty(DEVICE_KEY_PROPERTY_KEY);
        System.clearProperty(PLATFORM_NAME_PROPERTY_KEY);
    }
}
