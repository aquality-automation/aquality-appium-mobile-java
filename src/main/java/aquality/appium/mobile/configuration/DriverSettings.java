package aquality.appium.mobile.configuration;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.selenium.core.localization.ILocalizationManager;
import aquality.selenium.core.utilities.ISettingsFile;
import io.appium.java_client.remote.options.BaseOptions;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class DriverSettings implements IDriverSettings {

    private static final String APPLICATION_PATH_KEY = "applicationPath";
    private static final String APP_CAPABILITY_KEY = "app";
    private static final String DEVICE_KEY_KEY = "deviceKey";
    public static final String CAPABILITIES = "capabilities";

    private final ISettingsFile settingsFile;
    private final PlatformName platformName;

    public DriverSettings(ISettingsFile settingsFile, PlatformName platformName) {
        this.settingsFile = settingsFile;
        this.platformName = platformName;
    }

    @Override
    public BaseOptions<?> getCapabilities() {
        Map<String, Object> capabilitiesFromSettings = getCapabilitiesFromSettings();
        BaseOptions<?> capabilities = new BaseOptions<>();
        capabilitiesFromSettings.forEach((key, value) -> {
            if (key.toLowerCase().endsWith("options")) {
                value = settingsFile.getMap(getDriverSettingsPath(CAPABILITIES, key));
            }
            capabilities.setCapability(key, value);
        });
        if (hasApplicationPath()) {
            capabilities.setCapability(APP_CAPABILITY_KEY, getApplicationPath());
        }
        return capabilities.merge(getDeviceCapabilities());
    }

    private Map<String, Object> getCapabilitiesFromSettings() {
        return settingsFile.getMap(getDriverCapabilitiesJsonPath());
    }

    private String getDriverCapabilitiesJsonPath() {
        return getDriverSettingsPath(CAPABILITIES);
    }

    private String getAbsolutePath(String relativePath) {
        try {
            return new File(relativePath).getCanonicalPath();
        } catch (IOException e) {
            String message = String.format(
                    AqualityServices.get(ILocalizationManager.class).getLocalizedMessage("loc.file.reading_exception"),
                    relativePath);
            AqualityServices.getLogger().fatal(message, e);
            throw new IllegalArgumentException(message);
        }
    }

    private boolean hasApplicationPath() {
        return settingsFile.getMap(getDriverSettingsPath()).containsKey(APPLICATION_PATH_KEY) || getDeviceCapabilities().is(APP_CAPABILITY_KEY);
    }

    private BaseOptions<?> getDeviceCapabilities() {
        String deviceKey = (String) settingsFile.getValueOrDefault(getDriverSettingsPath(DEVICE_KEY_KEY), null);
        IDeviceSettings deviceSettings = new DeviceSettings(deviceKey);
        return deviceSettings.getCapabilities();
    }

    @Override
    public String getApplicationPath() {
        return getAbsolutePath(String.valueOf(settingsFile.getValueOrDefault(getDriverSettingsPath(APPLICATION_PATH_KEY),
                getDeviceCapabilities().getCapability(APP_CAPABILITY_KEY))));
    }

    @Override
    public String getBundleId() {
        final String BUNDLE_ID_CAPABILITY_KEY = platformName == PlatformName.ANDROID ? "appPackage" : "bundleId";
        String pathToCapability = getDriverSettingsPath(CAPABILITIES, BUNDLE_ID_CAPABILITY_KEY);
        Object capabilityForDevice = getDeviceCapabilities().getCapability(BUNDLE_ID_CAPABILITY_KEY);
        return (capabilityForDevice == null ? settingsFile.getValue(pathToCapability) : capabilityForDevice).toString();
    }

    private String getDriverSettingsPath(final String... paths) {
        String pathToDriverSettings = String.format("/driverSettings/%1$s", platformName.toString().toLowerCase());
        return pathToDriverSettings.concat(Arrays.stream(paths).map("/"::concat).collect(Collectors.joining()));
    }
}
