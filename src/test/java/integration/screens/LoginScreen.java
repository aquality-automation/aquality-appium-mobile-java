package integration.screens;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class LoginScreen extends Screen {

    protected LoginScreen(By locator, String name) {
        super(locator, name);
    }
}
