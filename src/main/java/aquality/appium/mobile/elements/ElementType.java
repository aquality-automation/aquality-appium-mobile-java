package aquality.appium.mobile.elements;

import aquality.appium.mobile.elements.interfaces.*;

public enum ElementType {
    BUTTON(IButton.class),
    CHECKBOX(ICheckBox.class),
    COMBOBOX(IComboBox.class),
    LABEL(ILabel.class),
    LINK(ILink.class),
    RADIOBUTTON(IRadioButton.class),
    TEXTBOX(ITextBox.class);

    private final Class<? extends IElement> clazz;

    <T extends IElement> ElementType(Class<T> clazz){
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    public <T extends IElement> Class<T> getClazz() {
        return (Class<T>) clazz;
    }
}
