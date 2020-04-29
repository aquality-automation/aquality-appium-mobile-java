package aquality.appium.mobile.screens.screenfactory;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.configuration.IApplicationProfile;
import com.google.inject.Inject;

public class ScreenFactoryProvider implements IScreenFactoryProvider {

    private final IApplicationProfile applicationProfile;

    @Inject
    public ScreenFactoryProvider(IApplicationProfile applicationProfile) {
        this.applicationProfile = applicationProfile;
    }

    @Override
    public IScreenFactory getScreenFactory() {
        return applicationProfile.getPlatformName() == PlatformName.ANDROID
                ? new AndroidScreenFactory()
                : new IOSScreenFactory();
    }
}
