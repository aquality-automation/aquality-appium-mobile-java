package aquality.appium.mobile.configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import io.appium.java_client.remote.options.BaseOptions;

import java.util.HashMap;
import java.util.Map;

public class DeviceSettings implements IDeviceSettings {

    private final ISettingsFile settingsFile;
    private final String deviceKey;

    public DeviceSettings(String deviceKey) {
        this.settingsFile = getDevicesSettingsFile();
        this.deviceKey = deviceKey;
    }

    private ISettingsFile getDevicesSettingsFile() {
        String devicesProfileName = System.getProperty("devicesProfile");
        String devicesProfile = devicesProfileName == null
                ? "devices.json"
                : "devices.".concat(devicesProfileName).concat(".json");
        return new JsonSettingsFile(devicesProfile);
    }

    @Override
    public BaseOptions<?> getCapabilities() {
        Map<String, Object> deviceCapabilities = getCapabilitiesFromSettings();
        BaseOptions<?> capabilities = new BaseOptions<>();
        deviceCapabilities.forEach(capabilities::setCapability);
        return capabilities;
    }

    private Map<String, Object> getCapabilitiesFromSettings() {
        Map<String, Object> deviceCapabilities = new HashMap<>();
        if (deviceKey != null) {
            String path = "/".concat(deviceKey).concat("/capabilities");
            deviceCapabilities = settingsFile.getMap(path);
        }
        return deviceCapabilities;
    }
}
