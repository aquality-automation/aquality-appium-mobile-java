package aquality.appium.elements;

import aquality.appium.elements.interfaces.IElement;
import aquality.appium.elements.interfaces.IElementSupplier;
import aquality.selenium.browser.Browser;
import aquality.selenium.browser.BrowserManager;
import aquality.selenium.configuration.Configuration;
import aquality.selenium.elements.interfaces.IElementStateProvider;
import aquality.selenium.localization.LocalizationManager;
import aquality.selenium.logger.Logger;
import aquality.selenium.utils.ElementActionRetrier;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebElement;

/**
 * Abstract class, describing wrapper of Appium element.
 */
public abstract class Element implements IElement {
    private static final String LOG_DELIMITER = "::";
    private static final String LOG_CLICKING = "loc.clicking";

    /**
     * Name of element
     */
    private final String name;

    /**
     * Element locator
     */
    private final By locator;

    /**
     * Element state provider
     */
    private final ElementStateProvider elementStateProvider;

    /**
     * The main constructor
     *
     * @param loc    By Locator
     * @param nameOf Output in logs
     */
    protected Element(final By loc, final String nameOf) {
        locator = loc;
        name = nameOf;
        elementStateProvider = new ElementStateProvider(locator);
    }

    @Override
    public RemoteWebElement getElement() {
        return getElement(getDefaultTimeout());
    }

    @Override
    public RemoteWebElement getElement(Long timeout) {
        try {
            return (RemoteWebElement) ElementFinder.getInstance().findElement(locator, timeout);
        } catch (NoSuchElementException e) {
            getLogger().error(e.getMessage());
            getLogger().debug("Page Source:\r\n" + getBrowser().getDriver().getPageSource());
            throw e;
        }
    }

    @Override
    public By getLocator() {
        return locator;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * The method returns the element type (used for logging)
     *
     * @return Type of element
     */
    protected abstract String getElementType();

    @Override
    public void sendKeys(Keys key) {
        ElementActionRetrier.doWithRetry(() -> getElement().sendKeys(key));
    }

    @Override
    public void click() {
        info(getLocManager().getValue(LOG_CLICKING));
        ElementActionRetrier.doWithRetry(() -> getElement().click());
    }

    @Override
    public String getText() {
        info(getLocManager().getValue("loc.get.text"));
        return ElementActionRetrier.doWithRetry(() -> getElement().getText());
    }

    @Override
    public IElementStateProvider state() {
        return elementStateProvider;
    }

    @Override
    public String getAttribute(final String attr) {
        info(String.format(getLocManager().getValue("loc.el.getattr"), attr));
        return ElementActionRetrier.doWithRetry(() -> getElement().getAttribute(attr));
    }

    private Browser getBrowser(){
        return BrowserManager.getBrowser();
    }

    /**
     * Format message for logging of current element
     *
     * @param message Message to display in the log
     * @return Formatted message (containing the name and type of item)
     */
    private String formatLogMsg(final String message) {
        return String.format("%1$s '%2$s' %3$s %4$s", getElementType(), getName(), LOG_DELIMITER, message);
    }

    protected void info(final String message) {
        getLogger().info(formatLogMsg(message));
    }

    @Override
    public <T extends IElement> T findChildElement(By childLoc, ElementType type) {
        return new ElementFactory().findChildElement(this, childLoc, type);
    }

    @Override
    public <T extends IElement> T findChildElement(By childLoc, Class<? extends IElement> clazz) {
        return new ElementFactory().findChildElement(this, childLoc, clazz);
    }

    @Override
    public <T extends IElement> T findChildElement(By childLoc, IElementSupplier<T> supplier) {
        return new ElementFactory().findChildElement(this, childLoc, supplier);
    }

    protected Logger getLogger(){
        return Logger.getInstance();
    }

    protected LocalizationManager getLocManager(){
        return LocalizationManager.getInstance();
    }

    long getDefaultTimeout(){
        return Configuration.getInstance().getTimeoutConfiguration().getCondition();
    }
}