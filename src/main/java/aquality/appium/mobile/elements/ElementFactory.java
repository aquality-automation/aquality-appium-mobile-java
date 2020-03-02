package aquality.appium.mobile.elements;

import aquality.appium.mobile.elements.interfaces.*;
import aquality.selenium.core.elements.interfaces.IElement;
import aquality.selenium.core.elements.interfaces.IElementFinder;
import aquality.selenium.core.localization.ILocalizationManager;
import aquality.selenium.core.waitings.IConditionalWait;
import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;

public class ElementFactory extends aquality.selenium.core.elements.ElementFactory implements IElementFactory {

    @Inject
    public ElementFactory(IConditionalWait conditionalWait, IElementFinder elementFinder,
                          ILocalizationManager localizationManager) {
        super(conditionalWait, elementFinder, localizationManager);
    }

    @Override
    protected Map<Class<? extends IElement>, Class<? extends IElement>> getElementTypesMap() {
        Map<Class<? extends IElement>, Class<? extends IElement>> typesMap = new HashMap<>();
        typesMap.put(IButton.class, Button.class);
        typesMap.put(ICheckBox.class, CheckBox.class);
        typesMap.put(IComboBox.class, ComboBox.class);
        typesMap.put(ILabel.class, Label.class);
        typesMap.put(ILink.class, Link.class);
        typesMap.put(IRadioButton.class, RadioButton.class);
        typesMap.put(ITextBox.class, TextBox.class);
        return typesMap;
    }
}
