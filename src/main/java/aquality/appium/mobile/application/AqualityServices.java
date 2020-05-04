package aquality.appium.mobile.application;

import aquality.appium.mobile.configuration.IApplicationProfile;
import aquality.appium.mobile.configuration.IConfiguration;
import aquality.appium.mobile.configuration.ILocalServiceSettings;
import aquality.appium.mobile.elements.interfaces.IElementFactory;
import aquality.appium.mobile.screens.screenfactory.IScreenFactory;
import aquality.appium.mobile.screens.screenfactory.IScreenFactoryProvider;
import aquality.selenium.core.localization.ILocalizedLogger;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.waitings.IConditionalWait;
import com.google.inject.Injector;

public class AqualityServices extends aquality.selenium.core.applications.AqualityServices<Application> {

    private static final ThreadLocal<AqualityServices> INSTANCE_CONTAINER = ThreadLocal.withInitial(AqualityServices::new);

    private IApplicationFactory applicationFactory;

    private AqualityServices() {
        super(AqualityServices::getApplication, () -> new MobileModule(AqualityServices::getApplication));
    }

    private AqualityServices(MobileModule module) {
        super(AqualityServices::getApplication, () -> module);
    }

    private static AqualityServices getInstance() {
        return INSTANCE_CONTAINER.get();
    }

    /**
     * Gets instance of application.
     *
     * @return Instance of application.
     */
    public static Application getApplication() {
        return getInstance().getApp(injector -> AqualityServices.startApplication());
    }

    /**
     * Check if application already started or not.
     *
     * @return true if application started and false otherwise.
     */
    public static boolean isApplicationStarted() {
        return getInstance().isAppStarted();
    }

    /**
     * Resolves required service from DI container.
     * Note that the service should be binded in {@link MobileModule}.
     *
     * @param type class of required service.
     * @param <T>  type of required service.
     * @return required service.
     */
    public static <T> T get(Class<T> type) {
        return getServiceProvider().getInstance(type);
    }

    private static Injector getServiceProvider() {
        return getInstance().getInjector();
    }

    /**
     * Sets default(local {@link LocalApplicationFactory} or remote {@link RemoteApplicationFactory}) application factory.
     */
    public static void setDefaultApplicationFactory() {
        IApplicationFactory applicationFactory = getApplicationProfile().isRemote()
                ? new RemoteApplicationFactory()
                : new LocalApplicationFactory();
        setApplicationFactory(applicationFactory);
    }

    /**
     * Gets factory for application creation.
     *
     * @return current instance of application factory.
     */
    public static IApplicationFactory getApplicationFactory() {
        if (getInstance().applicationFactory == null) {
            setDefaultApplicationFactory();
        }

        return getInstance().applicationFactory;
    }

    /**
     * Sets custom application factory.
     *
     * @param applicationFactory Custom implementation of {@link IApplicationFactory}
     */
    public static void setApplicationFactory(IApplicationFactory applicationFactory) {
        getInstance().applicationFactory = applicationFactory;
    }

    private static Application startApplication() {
        return getApplicationFactory().getApplication();
    }

    /**
     * Sets instance of application.
     *
     * @param application Instance of desired application.
     */
    public static void setApplication(Application application) {
        getInstance().setApp(application);
    }

    /**
     * Reinitializes the dependency injector with custom {@link MobileModule}.
     *
     * @param module {@link MobileModule} object with custom or overriden services.
     */
    public static void initInjector(MobileModule module) {
        if (INSTANCE_CONTAINER.get() != null) {
            INSTANCE_CONTAINER.remove();
        }
        INSTANCE_CONTAINER.set(new AqualityServices(module));
    }

    /**
     * Gets logger.
     *
     * @return instance of logger.
     */
    public static Logger getLogger() {
        return get(Logger.class);
    }

    /**
     * Gets logger which logs messages in current language.
     *
     * @return instance of localized logger.
     */
    public static ILocalizedLogger getLocalizedLogger() {
        return get(ILocalizedLogger.class);
    }

    /**
     * Provides the utility used to wait for some condition.
     *
     * @return instance of conditional wait.
     */
    public static IConditionalWait getConditionalWait() {
        return get(IConditionalWait.class);
    }

    /**
     * Gets factory to create elements.
     *
     * @return Factory of elements.
     */
    public static IElementFactory getElementFactory() {
        return get(IElementFactory.class);
    }

    /**
     * Gets factory to create screens.
     *
     * @return Factory of screens.
     */
    public static IScreenFactory getScreenFactory() {
        return get(IScreenFactoryProvider.class).getScreenFactory();
    }

    /**
     * Gets current profile of application settings.
     *
     * @return current application profile.
     */
    public static IApplicationProfile getApplicationProfile() {
        return get(IApplicationProfile.class);
    }

    /**
     * Gets AppiumDriverLocalService settings.
     *
     * @return AppiumDriverLocalService settings.
     */
    public static ILocalServiceSettings getLocalServiceSettings() {
        return get(ILocalServiceSettings.class);
    }

    /**
     * Provides interface to access all described configurations.
     *
     * @return configuration.
     */
    public static IConfiguration getConfiguration() {
        return get(IConfiguration.class);
    }
}
