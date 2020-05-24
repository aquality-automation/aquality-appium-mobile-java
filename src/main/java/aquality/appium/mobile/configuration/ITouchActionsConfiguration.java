package aquality.appium.mobile.configuration;

import java.time.Duration;

/**
 * Describes Touch Actions settings.
 */
public interface ITouchActionsConfiguration {
    /**
     * Gets number of retries to perform swipe.
     *
     * @return arguments map.
     */
    int getSwipeRetries();

    /**
     * Gets the number of seconds required to perform a swipe action.
     *
     * @return number of seconds required to perform a swipe action.
     */
    Duration getSwipeDuration();

    /**
     * Gets the offset coefficient to adjust the start/end point for swipe action relatively to the parallel screen edge.
     * Example for swipe down action:
     * The offset coefficient is used to calculate start/end point's Y coordinate.
     * If offset coefficient == 0.2, then the start point's Y coordinate == screen's length * (1 - 0.2).
     * If offset coefficient == 0.2, then the end point's Y coordinate == screen's length * 0.2.
     * The vice versa for swipe up action.
     * Example for swipe left action:
     * The offset coefficient is used to calculate start/end point's X coordinate.
     * If offset coefficient == 0.2, then the start point's X coordinate == screen's width * (1 - 0.2).
     * If offset coefficient == 0.2, then the end point's X coordinate == screen's width * 0.2.
     * The vice versa for swipe right action.
     *
     * @return offset coefficient to adjust the start/end point for swipe action relatively to the parallel screen edge
     */
    double getSwipeVerticalOffset();

    /**
     * Gets the offset coefficient to adjust the start/end point for swipe action relatively to the perpendicular screen edge.
     * Example for swipe down action:
     * The offset coefficient is used to calculate start/end point's X coordinate.
     * If offset coefficient == 0.5, then the start point's X coordinate == screen's width * (1 - 0.5).
     * If offset coefficient == 0.5, then the end point's X coordinate == screen's width * 0.5.
     * The vice versa for swipe up action.
     * Example for swipe left action:
     * The offset coefficient is used to calculate start/end point's X coordinate.
     * If offset coefficient == 0.5, then the start point's Y coordinate == screen's length * (1 - 0.5).
     * If offset coefficient == 0.5, then the end point's Y coordinate == screen's length * 0.5.
     * The vice versa for swipe right action.
     *
     * @return offset coefficient to adjust the start/end point for swipe action relatively to the perpendicular screen edge
     */
    double getSwipeHorizontalOffset();
}
