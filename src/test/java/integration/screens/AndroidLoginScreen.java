package integration.screens;

import aquality.appium.mobile.screens.AndroidScreen;
import org.openqa.selenium.By;

public class AndroidLoginScreen extends AndroidScreen implements ILoginScreen {

    /**
     * Constructor with parameters
     */
    public AndroidLoginScreen() {
        super(By.id("id"), "name");
    }
}
