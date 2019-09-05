package aquality.appium.elements.interfaces;

public interface ICheckBox extends IElement {
    /**
     * set true
     */
    void check();

    /**
     * Get the value of the checkbox (true / false)
     * @return true if is checked, false otherwise
     */
    boolean isChecked();

    /**
     * reverse state
     */
    void toggle();

    /**
     * Set the checkbox to false
     */
    void uncheck();
}
