package integration.screens;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;

@ScreenType(platform = PlatformName.IOS)
public class IOSLoginScreen extends LoginScreen {

    /**
     * Constructor with parameters
     */
    public IOSLoginScreen() {
        super(By.id("id"), "name");
    }
}
