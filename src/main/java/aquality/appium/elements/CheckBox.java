package aquality.appium.elements;

import aquality.appium.elements.interfaces.ICheckBox;
import org.openqa.selenium.By;

/**
 * Class describing the checkbox
 */
public class CheckBox extends Element implements ICheckBox {

    protected CheckBox(final By locator, final String name) {
        super(locator, name);
    }

    protected String getElementType() {
        return getLocManager().getValue("loc.checkbox");
    }

    @Override
    public void check() {
        setState(true);
    }

    @Override
    public void uncheck() {
        setState(false);
    }

    @Override
    public boolean isChecked() {
        return getState();
    }

    @Override
    public void toggle() {
        setState(!isChecked());
    }

    /**
     * Set value
     *
     * @param state value (true/false)
     */
    private void setState(boolean state) {
        getLogger().info(String.format("%1$s '%2$s'", getLocManager().getValue("loc.setting.value"), state));
        if (state != getState()) {
            click();
        }
    }

    private boolean getState() {
        info(getLocManager().getValue("loc.checkbox.get.state"));
        return getElement().isSelected();
    }
}
