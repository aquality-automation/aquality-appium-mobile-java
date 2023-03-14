package aquality.appium.mobile.screens;

import aquality.selenium.core.elements.interfaces.IElementStateProvider;
import aquality.selenium.core.forms.IForm;
import aquality.selenium.core.visualization.IDumpManager;
import org.openqa.selenium.By;

import java.awt.*;

/**
 * Defines interface for any UI form.
 */
public interface IScreen extends IForm {
    /**
     * Locator for specified screen
     */
    By getLocator();

    /**
     * Name of specified screen
     */
    String getName();

    /**
     * Size of the element described by screen locator.
     */
    Dimension getSize();

    /**
     * Provides ability to define of element's state (whether it is displayed, exists or not) and respective waiting functions
     *
     * @return provider to define element's state
     */
    IElementStateProvider state();


    /**
     * Gets dump manager for the current form that could be used for visualization purposes,
     * such as saving and comparing dumps.
     *
     * @return form's dump manager.
     */
    IDumpManager dump();
}
