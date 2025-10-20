package aquality.appium.mobile.screens.screenfactory;

import aquality.appium.mobile.screens.IScreen;

/**
 * Interface of abstract screen factory.
 */
public interface IScreenFactory {

    /**
     * Returns an implementation of a particular app screen.
     *
     * @param <T>   Type of desired application screen.
     * @param clazz Class of desired application screen.
     * @return Instance of desired application screen.
     */
    <T extends IScreen> T getScreen(Class<T> clazz);
}
