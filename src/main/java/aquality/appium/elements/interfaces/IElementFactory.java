package aquality.appium.elements.interfaces;

import aquality.appium.elements.ElementState;
import org.openqa.selenium.By;

public interface IElementFactory {
    default ILabel getLabel(By locator, String name) {
        return getLabel(locator, name, ElementState.DISPLAYED);
    }

    ILabel getLabel(By locator, String name, ElementState state);
}
