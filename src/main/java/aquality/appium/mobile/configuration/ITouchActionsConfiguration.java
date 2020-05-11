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
     * Gets the timeout to perform a swipe.
     *
     * @return timeout to perform swipe.
     */
    Duration getSwipeTimeout();

    /**
     * Gets the coefficient to calculate the 'x' coordinate of top point for swipe action.
     *
     * @return coefficient to calculate the 'x' coordinate of top point for swipe action
     */
    double getHorizontalSwipeTopPointXCoefficient();

    /**
     * Gets the coefficient to calculate the 'x' coordinate of bottom point for swipe action.
     *
     * @return coefficient to calculate the 'x' coordinate of bottom point for swipe action
     */
    double getHorizontalSwipeBottomPointXCoefficient();

    /**
     * Gets the coefficient to calculate the 'y' coordinate of top point for swipe action.
     *
     * @return coefficient to calculate the 'y' coordinate of top point for swipe action
     */
    double getHorizontalSwipeTopPointYCoefficient();

    /**
     * Gets the coefficient to calculate the 'y' coordinate of bottom point for swipe action.
     *
     * @return coefficient to calculate the 'y' coordinate of bottom point for swipe action
     */
    double getHorizontalSwipeBottomPointYCoefficient();

    /**
     * Gets the coefficient to calculate the 'x' coordinate of top point for swipe action.
     *
     * @return timeout to perform swipe.
     */
    double getVerticalSwipeLeftPointXCoefficient();

    /**
     * Gets the coefficient to calculate the 'x' coordinate of right-hand point for swipe action.
     *
     * @return coefficient to calculate the 'x' coordinate of right-hand point for swipe action
     */
    double getVerticalSwipeRightPointXCoefficient();

    /**
     * Gets the coefficient to calculate the 'y' coordinate of left-hand point for swipe action.
     *
     * @return coefficient to calculate the 'y' coordinate of left-hand point for swipe action
     */
    double getVerticalSwipeLeftPointYCoefficient();


    /**
     * Gets the coefficient to calculate the 'y' coordinate of right-hand point for swipe action.
     *
     * @return coefficient to calculate the 'y' coordinate of right-hand point for swipe action
     */
    double getVerticalSwipeRightPointYCoefficient();
}
