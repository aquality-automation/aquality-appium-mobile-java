package samples.android.web;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.elements.interfaces.IRadioButton;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import samples.android.ITestRadioButton;

public class WebRadioButtonTest extends AndroidWebTest implements ITestRadioButton {
    @Override
    public void openRadioButtonsScreen() {
        AqualityServices.getApplication().getDriver().get("http://www.echoecho.com/htmlforms10.htm");
    }

    @Override
    public IRadioButton getRadioButton(int number) {
        return AqualityServices.getElementFactory().getRadioButton(
                By.xpath(String.format("(//input[@type='radio'])[%d]", number)),
                "#" + number);
    }

    @Test(groups = "web")
    @Override
    public void testRadioButton() {
        ITestRadioButton.super.testRadioButton();
    }
}
