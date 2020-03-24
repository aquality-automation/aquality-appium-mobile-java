package aquality.appium.mobile.configuration;

import aquality.selenium.core.utilities.ISettingsFile;
import com.google.inject.Inject;
import io.appium.java_client.service.local.flags.ServerArgument;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class LocalServiceSettings implements ILocalServiceSettings {
    private static final String SETTINGS_PATH = "/localServiceSettings";
    private final ISettingsFile settingsFile;

    @Inject
    public LocalServiceSettings(ISettingsFile settingsFile) {
        this.settingsFile = settingsFile;
    }

    @Override
    public Map<ServerArgument, String> getArguments() {
        Map<ServerArgument, String> argumentStringMap = new HashMap<>();
        settingsFile.getMap(SETTINGS_PATH + "/arguments")
                .forEach((key, value) -> argumentStringMap.put(() -> key, value.toString()));
        return argumentStringMap;
    }

    @Override
    public DesiredCapabilities getCapabilities() {
        Map<String, Object> capabilitiesFromSettings = settingsFile.getMap(SETTINGS_PATH + "/capabilities");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilitiesFromSettings.forEach(capabilities::setCapability);
        return capabilities;
    }
}
