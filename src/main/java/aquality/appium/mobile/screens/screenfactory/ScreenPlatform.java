package aquality.appium.mobile.screens.screenfactory;

import aquality.appium.mobile.application.PlatformName;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ScreenPlatform {
    PlatformName platform();
}
