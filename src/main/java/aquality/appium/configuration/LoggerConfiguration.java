package aquality.appium.configuration;

import aquality.appium.localization.SupportedLanguage;
import aquality.appium.utils.JsonFile;

public class LoggerConfiguration implements ILoggerConfiguration{

    private final JsonFile settingsFile;

    public LoggerConfiguration(JsonFile settingsFile){
        this.settingsFile = settingsFile;
    }

    @Override
    public SupportedLanguage getLanguage() {
        return SupportedLanguage.valueOf(settingsFile.getValue("/logger/language").toString().toUpperCase());
    }
}
