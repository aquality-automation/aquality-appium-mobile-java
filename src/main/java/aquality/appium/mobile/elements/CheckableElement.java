package aquality.appium.mobile.elements;

import aquality.selenium.core.elements.ElementState;
import org.openqa.selenium.By;

public abstract class CheckableElement extends Element {
    /**
     * The main constructor
     *
     * @param loc     By Locator
     * @param nameOf  Output in logs
     * @param stateOf desired ElementState
     */
    protected CheckableElement(By loc, String nameOf, ElementState stateOf) {
        super(loc, nameOf, stateOf);
    }

    public boolean isChecked() {
        return doWithRetry(() -> {
            String checked = getElement().getAttribute(Attributes.CHECKED.toString());
            if (checked == null || checked.equals("")) {
                return getElement().isSelected();
            }
            return checked.equals("true");
        });
    }
}
