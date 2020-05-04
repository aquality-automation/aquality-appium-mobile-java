package integration.screens;

import aquality.appium.mobile.screens.IOSScreen;
import org.openqa.selenium.By;

public class IOSLoginScreen extends IOSScreen implements ILoginScreen {

    /**
     * Constructor with parameters
     */
    public IOSLoginScreen() {
        super(By.id("id"), "name");
    }
}
