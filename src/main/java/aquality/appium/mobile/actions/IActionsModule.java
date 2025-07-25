package aquality.appium.mobile.actions;

import aquality.appium.mobile.configuration.IConfigurationsModule;

/**
 * Describes implementations of actions services to be registered in DI container.
 */
public interface IActionsModule extends IConfigurationsModule {

    /**
     * @return class which implements {@link ITouchActions}
     */
    default Class<? extends ITouchActions> getTouchActionsImplementation() {
        return TouchActions.class;
    }

    /**
     * @return class which implements {@link IAndroidActions}
     */
    default Class<? extends IAndroidActions> getAndroidActionsImplementation() {
        return AndroidActions.class;
    }
}
