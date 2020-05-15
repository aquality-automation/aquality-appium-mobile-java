package aquality.appium.mobile.screens.screenfactory;

/**
 * Describes implementations of screens services to be registered in DI container.
 */
public interface IScreensModule {

    /**
     * @return class which implements {@link IScreenFactory}
     */
    default Class<? extends IScreenFactory> getScreenFactoryImplementation() {
        return ScreenFactory.class;
    }
}
