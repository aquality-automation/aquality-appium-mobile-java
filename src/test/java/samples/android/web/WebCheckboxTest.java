package samples.android.web;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.ICheckBox;
import aquality.appium.mobile.elements.interfaces.ILabel;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import samples.android.ITestCheckBox;
import testreport.ScreenshotListener;

@Listeners(ScreenshotListener.class)
public class WebCheckboxTest implements IAndroidWebSessionTest, ITestCheckBox {
    private ILabel form = AqualityServices.getElementFactory().getLabel(By.id("checkboxes"), "Checkboxes form");

    @Override
    public void openCheckBoxesScreen() {
        AqualityServices.getApplication().getDriver().get("http://the-internet.herokuapp.com/checkboxes");
    }

    @Override
    public ICheckBox getCheckBox(int number) {
        return AqualityServices.getElementFactory().findChildElement(form, By.xpath(String.format(".//input[%d]", number)),
                "#" + number, ElementType.CHECKBOX);
    }

    @Test
    public void testCheckBox() {
        ITestCheckBox.super.testCheckBox();
    }
}
