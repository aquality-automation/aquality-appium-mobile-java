package aquality.appium.mobile.application;

import aquality.appium.mobile.configuration.IApplicationProfile;
import aquality.appium.mobile.configuration.IConfiguration;
import aquality.appium.mobile.configuration.IConfigurationsModule;
import aquality.appium.mobile.elements.IElementsModule;
import aquality.appium.mobile.elements.interfaces.IElementFactory;
import aquality.selenium.core.applications.AqualityModule;
import com.google.inject.Provider;
import com.google.inject.Singleton;

public class MobileModule extends AqualityModule<Application> implements IConfigurationsModule, IElementsModule {
    public MobileModule(Provider<Application> applicationProvider) {
        super(applicationProvider);
    }

    @Override
    protected void configure() {
        super.configure();
        bind(IApplicationProfile.class).to(getBrowserProfileImplementation()).in(Singleton.class);
        bind(IConfiguration.class).to(getConfigurationImplementation());
        bind(IElementFactory.class).to(getElementFactoryImplementation());
    }
}
