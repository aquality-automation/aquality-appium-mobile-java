package integration.screens;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class FakeLoginScreen extends Screen {

    protected FakeLoginScreen(By locator, String name) {
        super(locator, name);
    }
}
