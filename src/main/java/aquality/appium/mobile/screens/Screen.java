package aquality.appium.mobile.screens;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.elements.interfaces.IElementFactory;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.selenium.core.elements.interfaces.IElementStateProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

public abstract class Screen implements IScreen {

    private final By locator;
    private final String name;
    private final ILabel screenLabel;

    /**
     * Constructor with parameters
     */
    protected Screen(By locator, String name) {
        this.locator = locator;
        this.name = name;
        this.screenLabel = getElementFactory().getLabel(locator, name);
    }

    public By getLocator() {
        return locator;
    }

    public String getName() {
        return name;
    }

    public Dimension getSize() {
       return screenLabel.getElement().getSize();
    }

    public IElementStateProvider state() {
        return screenLabel.state();
    }

    protected IElementFactory getElementFactory(){
        return AqualityServices.getElementFactory();
    }
}
