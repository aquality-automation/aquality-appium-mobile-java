package aquality.appium.elements;

import aquality.appium.application.Application;
import aquality.appium.application.ApplicationManager;
import aquality.appium.configuration.Configuration;
import aquality.appium.configuration.ITimeoutConfiguration;
import aquality.appium.elements.interfaces.IElementFinder;
import aquality.appium.waitings.ConditionalWait;
import aquality.selenium.localization.LocalizationManager;
import aquality.selenium.logger.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class ElementFinder implements IElementFinder {
    private static final ThreadLocal<ElementFinder> instanceHolder = new ThreadLocal<>();

    static ElementFinder getInstance() {
        if(instanceHolder.get() == null){
            instanceHolder.set(new ElementFinder());
        }
        return instanceHolder.get();
    }

    private ElementFinder(){
    }

    @Override
    public long getDefaultTimeout() {
        return getTimeoutConfiguration().getCondition();
    }

    @Override
    public WebElement findElement(By locator, long timeout) {
        List<WebElement> elements = findElements(locator, timeout);
        if(!elements.isEmpty()){
            return elements.get(0);
        }else {
            String message = String.format(
                    "element was not found in %d seconds by locator %s", timeout, locator);
            throw new NoSuchElementException(message);
        }
    }

    @Override
    public List<WebElement> findElements(By locator, long timeout) {
        return findElements(locator, timeout,
                new DesiredState(Objects::nonNull,
                        String.format(
                        getLocManager().getValue("loc.no.elements.found.in.state"), locator, "EXIST", timeout))
                        .withCatchingTimeoutException());
    }

    List<WebElement> findElements(By locator, long timeout, DesiredState desiredState)
    {
        List<WebElement> foundElements = new ArrayList<>();
        List<WebElement> resultElements = new ArrayList<>();
        long zeroTimeout = 0L;
        getApplication().setImplicitWaitTimeout(zeroTimeout);
        try{

            ConditionalWait.waitFor(driver ->
            {
                List<WebElement> allFoundElements = driver.findElements(locator);
                foundElements.addAll(allFoundElements);
                List<WebElement> filteredElements = filterByState(allFoundElements, desiredState.getDesiredStatePredicate());
                resultElements.addAll(filteredElements);
                return !filteredElements.isEmpty();
            }, timeout, getTimeoutConfiguration().getPollingInterval(),
                    desiredState.getMessage(), Collections.emptyList());
        }catch (TimeoutException e){
            applyResult(locator, desiredState, foundElements);
        }
        getApplication().setImplicitWaitTimeout(getTimeoutConfiguration().getImplicit());
        return resultElements;
    }

    /**
     * depends on configuration of DesiredState object it can be required to throw or not NoSuchElementException
     * @param locator locator that is using to find elements
     * @param desiredState DesiredState object
     * @param foundElements list of all found elements by locator.
     */
    private void applyResult(By locator, DesiredState desiredState, List<WebElement> foundElements){
        if (desiredState.isCatchingInTimeoutException()){
            if(foundElements.isEmpty()){
                String message = String.format(getLocManager().getValue("loc.no.elements.found.by.locator"), locator);
                if(desiredState.isThrowingNoSuchElementException()){
                    throw new NoSuchElementException(message);
                }
                getLogger().debug(message);
            }else {
                getLogger().debug(String.format(getLocManager().getValue("loc.elements.were.found.but.not.in.state"), locator, desiredState.getMessage()));
            }
        }else {
            throw new TimeoutException(desiredState.getMessage());
        }
    }

    private List<WebElement> filterByState(List<WebElement> foundElements, Predicate<WebElement> desiredElementState){
        List<WebElement> filteredElements = new ArrayList<>();
        if(!foundElements.isEmpty()){
            filteredElements.addAll(foundElements.stream().filter(desiredElementState).collect(Collectors.toList()));
        }
        return filteredElements;
    }

    private Application getApplication() {
        return ApplicationManager.getApplication();
    }

    private ITimeoutConfiguration getTimeoutConfiguration() {
        return Configuration.getInstance().getTimeoutConfiguration();
    }

    private Logger getLogger(){
        return Logger.getInstance();
    }

    private LocalizationManager getLocManager(){
        return LocalizationManager.getInstance();
    }
}
