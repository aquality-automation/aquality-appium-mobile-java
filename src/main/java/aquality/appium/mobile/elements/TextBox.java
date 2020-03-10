package aquality.appium.mobile.elements;

import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.selenium.core.elements.ElementState;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

/**
 * The class that describes an input field
 */
public class TextBox extends Element implements ITextBox {

    private static final String LOG_TYPING = "loc.text.typing";
    private static final String LOG_CLEARING = "loc.text.clearing";
    private static final String LOG_FOCUSING = "loc.text.focusing";
    private static final String LOG_UNFOCUSING = "loc.text.unfocusing";
    private final String logMaskedValue = getLocalizationManager().getLocalizedMessage("loc.text.masked_value");

    protected TextBox(final By locator, final String name, final ElementState state) {
        super(locator, name, state);
    }

    protected String getElementType() {
        return getLocalizationManager().getLocalizedMessage("loc.text.field");
    }

    @Override
    public void type(final String value) {
        type(value, false);
    }

    @Override
    public void typeSecret(final String value) {
        type(value, true);
    }

    @Override
    public void clear() {
        logElementAction(LOG_CLEARING);
        doWithRetry(() -> getElement().clear());
    }

    @Override
    public void clearAndType(final String value) {
        clearAndType(value, false);
    }

    @Override
    public void clearAndTypeSecret(final String value) {
        clearAndType(value, true);
    }

    @Override
    public String getValue() {
        return getAttribute(Attributes.VALUE.toString());
    }

    @Override
    public void focus() {
        logElementAction(LOG_FOCUSING);
        doWithRetry(() -> getElement().sendKeys(""));
    }

    @Override
    public void unfocus() {
        logElementAction(LOG_UNFOCUSING);
        doWithRetry(() -> getElement().sendKeys(Keys.TAB));
    }

    private void type(final String value, final boolean maskValueInLog) {
        logElementAction(LOG_TYPING, maskValueInLog ? logMaskedValue : value);
        doWithRetry(() -> getElement().sendKeys(value));
    }

    private void clearAndType(final String value, final boolean maskValueInLog) {
        logElementAction(LOG_CLEARING);
        logElementAction(LOG_TYPING, maskValueInLog ? logMaskedValue : value);
        doWithRetry(() -> {
            getElement().clear();
            getElement().sendKeys(value);
        });
    }
}
