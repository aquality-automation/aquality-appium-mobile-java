package aquality.appium.mobile.screens.screenfactory;

import aquality.appium.mobile.screens.AndroidScreen;

public class AndroidScreenFactory extends ScreenFactory<AndroidScreen> {

    @Override
    protected Class<AndroidScreen> getPlatformClass() {
        return AndroidScreen.class;
    }
}
