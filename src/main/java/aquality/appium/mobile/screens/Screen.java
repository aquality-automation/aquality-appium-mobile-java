package aquality.appium.mobile.screens;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.appium.mobile.elements.interfaces.IElementFactory;
import aquality.selenium.core.configurations.IVisualizationConfiguration;
import aquality.selenium.core.elements.interfaces.IElementStateProvider;
import aquality.selenium.core.forms.Form;
import aquality.selenium.core.localization.ILocalizedLogger;
import org.openqa.selenium.By;

import java.awt.*;

/**
 * Defines base class for any UI form.
 */
public abstract class Screen extends Form<IElement> implements IScreen {
    /**
     * Locator for specified form
     */
    private final By locator;
    /**
     * Name of specified form
     */
    private final String name;
    /**
     * Screen element defined by its locator and name.
     */
    private final IElement screenElement;

    /**
     * Constructor with parameters
     */
    protected Screen(By locator, String name) {
        super(IElement.class);
        this.locator = locator;
        this.name = name;
        this.screenElement = getElementFactory().getLabel(locator, name);
    }

    @Override
    public By getLocator() {
        return locator;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Dimension getSize() {
       return screenElement.visual().getSize();
    }

    @Override
    public IElementStateProvider state() {
        return screenElement.state();
    }

    /**
     * Gets form element defined by its locator and name.
     * Could be used to find child elements relative to form element.
     *
     * @return form element.
     */
    protected IElement getScreenElement() {
        return screenElement;
    }

    protected IElementFactory getElementFactory(){
        return AqualityServices.getElementFactory();
    }

    @Override
    protected IVisualizationConfiguration getVisualizationConfiguration() {
        return AqualityServices.getConfiguration().getVisualizationConfiguration();
    }

    @Override
    protected ILocalizedLogger getLocalizedLogger() {
        return AqualityServices.getLocalizedLogger();
    }
}
