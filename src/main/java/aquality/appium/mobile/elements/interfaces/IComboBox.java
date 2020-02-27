package aquality.appium.mobile.elements.interfaces;

import java.util.List;

public interface IComboBox extends IElement {
    /**
     * Select by index
     *
     * @param index number of selected option
     */
    void selectByIndex(int index);

    /**
     * Select by visible text
     *
     * @param text text to be selected
     */
    void selectByText(String text);

    /**
     * Open Dropdown and select by visible text
     *
     * @param value text to be selected
     */
    void clickAndSelectByText(String value);

    /**
     * Select by containing visible text
     *
     * @param text visible text
     */
    void selectByContainingText(String text);

    /**
     * Select by containing value
     *
     * @param value partial option's value
     */
    void selectByContainingValue(String value);

    /**
     * Select by value
     *
     * @param value argument value
     */
    void selectByValue(String value);

    /**
     * Open Dropdown and select by value
     *
     * @param value argument value
     */
    void clickAndSelectByValue(String value);

    /**
     * Gets value of selected option
     *
     * @return selected value
     */
    String getSelectedValue();

    /**
     * Gets text of selected option
     *
     * @return selected text
     */
    String getSelectedText();


    /**
     * Gets values list
     * @return list of values
     */
    List<String> getValues();

    /**
     * Gets options' texts list
     * @return list of options' texts
     */
    List<String> getTexts();
}
