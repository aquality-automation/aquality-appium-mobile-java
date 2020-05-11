package aquality.appium.mobile.screens.screenfactory;

import aquality.appium.mobile.screens.IOSScreen;

public class IOSScreenFactory extends ScreenFactory<IOSScreen> {

    @Override
    protected Class<IOSScreen> getPlatformClass() {
        return IOSScreen.class;
    }
}
