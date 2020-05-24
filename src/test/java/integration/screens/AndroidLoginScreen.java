package integration.screens;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidLoginScreen extends LoginScreen {

    /**
     * Constructor with parameters
     */
    public AndroidLoginScreen() {
        super(By.id("id"), "name");
    }
}
