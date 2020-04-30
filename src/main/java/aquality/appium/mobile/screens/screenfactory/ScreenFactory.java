package aquality.appium.mobile.screens.screenfactory;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.configuration.IApplicationProfile;
import aquality.appium.mobile.screens.IScreen;
import aquality.appium.mobile.screens.Screen;
import org.reflections.Reflections;
import org.reflections.ReflectionsException;

import java.util.Set;

public abstract class ScreenFactory<TPlatformScreen extends Screen> implements IScreenFactory {

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IScreen> T getScreen(Class<T> clazz) {
        Class<? extends TPlatformScreen> tClass = getPlatformClasses().stream()
                .filter(clazz::isAssignableFrom)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Implementation for Screen %s was not found in package %s",
                                clazz.getName(), getPackageWithScreens())));

        try {
            return (T) tClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("Something went wrong during screen casting", e);
        }
    }

    private Set<Class<? extends TPlatformScreen>> getPlatformClasses() {
        Reflections reflections = new Reflections(getPackageWithScreens());
        try {
            return reflections.getSubTypesOf(getPlatformClass());
        } catch (ReflectionsException e) {
            throw new IllegalArgumentException(String.format("Could not find package \"%s\" with Screens. " +
                    "Please specify value \"screensLocation\" in settings file.", getPackageWithScreens()), e);
        }
    }

    private String getPackageWithScreens() {
        return AqualityServices.get(IApplicationProfile.class).getScreensLocation();
    }

    protected abstract Class<TPlatformScreen> getPlatformClass();
}
