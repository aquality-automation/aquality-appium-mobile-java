package aquality.appium.mobile.application;

import aquality.appium.mobile.actions.IActionsModule;
import aquality.appium.mobile.actions.ITouchActions;
import aquality.appium.mobile.configuration.IApplicationProfile;
import aquality.appium.mobile.configuration.IConfiguration;
import aquality.appium.mobile.configuration.IConfigurationsModule;
import aquality.appium.mobile.configuration.ILocalServiceSettings;
import aquality.appium.mobile.configuration.ITouchActionsConfiguration;
import aquality.appium.mobile.elements.IElementsModule;
import aquality.appium.mobile.elements.interfaces.IElementFactory;
import aquality.appium.mobile.screens.screenfactory.IScreenFactoryProvider;
import aquality.appium.mobile.screens.screenfactory.IScreensModule;
import aquality.selenium.core.applications.AqualityModule;
import com.google.inject.Provider;
import com.google.inject.Singleton;

public class MobileModule extends AqualityModule<Application> implements IConfigurationsModule, IElementsModule, IScreensModule, IActionsModule {

    public MobileModule(Provider<Application> applicationProvider) {
        super(applicationProvider);
    }

    @Override
    protected void configure() {
        super.configure();
        bind(IApplicationProfile.class).to(getApplicationProfileImplementation()).in(Singleton.class);
        bind(ILocalServiceSettings.class).to(getLocalServiceSettingsImplementation()).in(Singleton.class);
        bind(IConfiguration.class).to(getConfigurationImplementation());
        bind(IElementFactory.class).to(getElementFactoryImplementation());
        bind(IScreenFactoryProvider.class).to(getScreenFactoryProviderImplementation());
        bind(ITouchActionsConfiguration.class).to(getTouchActionsConfigurationImplementation()).in(Singleton.class);
        bind(ITouchActions.class).to(getTouchActionsImplementation()).in(Singleton.class);
    }
}
