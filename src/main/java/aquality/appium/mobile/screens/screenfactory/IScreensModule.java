package aquality.appium.mobile.screens.screenfactory;

/**
 * Describes implementations of screens services to be registered in DI container.
 */
public interface IScreensModule {

    /**
     * @return class which implements {@link IScreenFactoryProvider}
     */
    default Class<? extends IScreenFactoryProvider> getScreenFactoryProviderImplementation() {
        return ScreenFactoryProvider.class;
    }
}
