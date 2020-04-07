package aquality.appium.mobile.configuration;

import aquality.appium.mobile.actions.ITouchActions;
import aquality.appium.mobile.actions.TouchActions;

/**
 * Describes implementations of configurations to be registered in DI container.
 */
public interface IConfigurationsModule extends aquality.selenium.core.configurations.IConfigurationsModule {

    /**
     * @return class which implements {@link IApplicationProfile}
     */
    default Class<? extends IApplicationProfile> getApplicationProfileImplementation() {
        return ApplicationProfile.class;
    }

    /**
     * @return class which implements {@link ILocalServiceSettings}
     */
    default Class<? extends ILocalServiceSettings> getLocalServiceSettingsImplementation() {
        return LocalServiceSettings.class;
    }

    /**
     * @return class which implements {@link IConfiguration}
     */
    default Class<? extends IConfiguration> getConfigurationImplementation() {
        return Configuration.class;
    }

    /**
     * @return class which implements {@link ISwipeConfiguration}
     */
    default Class<? extends ISwipeConfiguration> getSwipeConfigurationImplementation() {
        return SwipeConfiguration.class;
    }

    /**
     * @return class which implements {@link ITouchActions}
     */
    default Class<? extends ITouchActions> getTouchActionsImplementation() {
        return TouchActions.class;
    }
}
