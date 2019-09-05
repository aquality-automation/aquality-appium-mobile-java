package aquality.appium.elements;

import aquality.appium.configuration.Configuration;
import aquality.appium.configuration.ITimeoutConfiguration;
import aquality.appium.elements.interfaces.*;
import aquality.appium.waitings.ConditionalWait;
import aquality.selenium.elements.ElementsCount;
import aquality.selenium.localization.LocalizationManager;
import aquality.selenium.logger.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ElementFactory implements IElementFactory {
    private static final int XPATH_SUBSTRING_BEGIN_INDEX = 10;

    @Override
    public IButton getButton(final By locator, final String name){
        return new Button(locator, name);
    }

    @Override
    public ICheckBox getCheckBox(final By locator, final String name) {
        return new CheckBox(locator, name);
    }

    @Override
    public IComboBox getComboBox(final By locator, final String name) {
        return new ComboBox(locator, name);
    }

    @Override
    public ILabel getLabel(final By locator, final String name) {
        return new Label(locator, name);
    }

    @Override
    public ILink getLink(final By locator, final String name) {
        return new Link(locator, name);
    }

    @Override
    public IRadioButton getRadioButton(final By locator, final String name) {
        return new RadioButton(locator, name);
    }

    @Override
    public ITextBox getTextBox(final By locator, final String name) {
        return new TextBox(locator, name);
    }

    @Override
    public <T extends IElement> T getCustomElement(IElementSupplier<T> supplier, By locator, String name) {
        return supplier.get(locator, name);
    }

    @Override
    public <T extends IElement> T findChildElement(IElement parentElement, By childLoc,
                                                   Class<? extends IElement> clazz) {
        return findChildElementCore(parentElement, childLoc, getDefaultElementSupplier(clazz));
    }

    @Override
    public <T extends IElement> T findChildElement(IElement parentElement, By childLoc,
                                                   IElementSupplier<T> supplier) {
        return findChildElementCore(parentElement, childLoc, supplier);
    }

    @Override
    public <T extends IElement> T findChildElement(IElement parentElement, By childLoc, ElementType type) {
        return findChildElement(parentElement, childLoc, type.getClazz());
    }

    @Override
    public <T extends IElement> List<T> findElements(By locator, IElementSupplier<T> supplier, ElementsCount count) {
        return findElementsCore(locator, supplier, count);
    }

    @Override
    public <T extends IElement> List<T> findElements(By locator, Class<? extends IElement> clazz, ElementsCount count) {
        return findElementsCore(locator, getDefaultElementSupplier(clazz), count);
    }

    @Override
    public <T extends IElement> List<T> findElements(By locator, ElementType type, ElementsCount count) {
        return findElements(locator, type.getClazz(), count);
    }

    private  <T extends IElement> List<T> findElementsCore(By locator, IElementSupplier<T> supplier, ElementsCount count) {
        if(!locator.getClass().equals(ByXPath.class)) {
            throw new IllegalArgumentException("non-XPath locators are not supported yet");
        }
        List<T> elements = new ArrayList<>();
        switch (count) {
            case ZERO:
                ConditionalWait.waitFor(driver -> driver.findElements(locator).isEmpty(),
                        String.format(LocalizationManager.getInstance().getValue("loc.elements.found.but.should.not"),
                                locator.toString()));
                break;
            case MORE_THEN_ZERO:
                ConditionalWait.waitFor(driver -> !driver.findElements(locator).isEmpty(),
                        String.format(LocalizationManager.getInstance().getValue("loc.no.elements.found.by.locator"),
                                locator.toString()));
                break;
            default:
                throw new IllegalArgumentException("No such expected value:".concat(count.toString()));
        }

        List<WebElement> webElements = ElementFinder.getInstance().findElements(locator, getTimeoutConfig().getCondition());
        int index = 1;
        for (WebElement webElement : webElements) {
            try {
                String xPath = String.format("(%s)[%d]", locator.toString().substring(XPATH_SUBSTRING_BEGIN_INDEX), index);
                T element = supplier.get(By.xpath(xPath), "element " + index);
                elements.add(element);
                ++index;
            } catch (IllegalArgumentException e) {
                Logger.getInstance().debug(e.getMessage());
            }
        }

        return elements;
    }

    private  <T extends IElement> T findChildElementCore(IElement parentElement, By childLoc, IElementSupplier<T> supplier) {
        return supplier.get(new ByChained(parentElement.getLocator(), childLoc), "Child element of ".concat(parentElement.getName()));
    }

    private <T extends IElement> IElementSupplier<T> getDefaultElementSupplier(Class<? extends IElement> clazz) {
        Type type = convertElementClassToType(clazz);

        return (locator, name) -> {
            Constructor ctor;
            try {
                ctor = ((Class) type).getDeclaredConstructor(By.class, String.class);
                return (T) ctor.newInstance(locator, name);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                Logger.getInstance().debug(e.getMessage());
                throw new IllegalArgumentException("Something went wrong during element casting");
            }
        };
    }

    private Type convertElementClassToType(Class<? extends IElement> clazz) {
        return clazz.isInterface() ? ElementType.getTypeByClass(clazz).getClazz() : clazz;
    }

    private ITimeoutConfiguration getTimeoutConfig(){
        return Configuration.getInstance().getTimeoutConfiguration();
    }
}

