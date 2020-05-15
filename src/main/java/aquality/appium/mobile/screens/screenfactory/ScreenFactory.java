package aquality.appium.mobile.screens.screenfactory;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.configuration.IApplicationProfile;
import aquality.appium.mobile.screens.IScreen;
import org.reflections.Reflections;
import org.reflections.ReflectionsException;

import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;

public class ScreenFactory implements IScreenFactory {

    private final IApplicationProfile applicationProfile;

    @Inject
    public ScreenFactory(IApplicationProfile applicationProfile) {
        this.applicationProfile = applicationProfile;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IScreen> T getScreen(Class<T> clazz) {
        Class<?> tClass = getPlatformClasses().stream()
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

    private Set<Class<?>> getPlatformClasses() {
        Reflections reflections = new Reflections(getPackageWithScreens());
        try {
            return reflections.getTypesAnnotatedWith(ScreenType.class).stream()
                    .filter(clazz -> clazz.getAnnotation(ScreenType.class).platform() == applicationProfile.getPlatformName())
                    .collect(Collectors.toSet());
        } catch (ReflectionsException e) {
            throw new IllegalArgumentException(String.format("Could not find package \"%s\" with Screens. " +
                    "Please specify value \"screensLocation\" in settings file.", getPackageWithScreens()), e);
        }
    }

    private String getPackageWithScreens() {
        return AqualityServices.get(IApplicationProfile.class).getScreensLocation();
    }
}
