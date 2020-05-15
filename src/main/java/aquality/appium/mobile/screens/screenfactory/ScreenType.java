package aquality.appium.mobile.screens.screenfactory;

import aquality.appium.mobile.application.PlatformName;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Attribute that identifies platform of screen.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ScreenType {

    /**
     * Name of platform that screen relates to.
     */
    PlatformName platform();
}
