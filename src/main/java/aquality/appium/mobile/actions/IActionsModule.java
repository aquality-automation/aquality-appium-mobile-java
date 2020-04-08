package aquality.appium.mobile.actions;

/**
 * Describes implementations of actions services to be registered in DI container.
 */
public interface IActionsModule extends aquality.selenium.core.configurations.IConfigurationsModule {

    /**
     * @return class which implements {@link ITouchActions}
     */
    default Class<? extends ITouchActions> getTouchActionsImplementation() {
        return TouchActions.class;
    }
}
