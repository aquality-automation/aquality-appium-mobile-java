package samples.windows;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.MobileModule;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import samples.windows.calculator.screens.CalculatorScreen;
import samples.windows.calculator.screens.CalculatorScreen.Button;

public class WindowsDesktopAppTest {

    private static WindowsDriver<?> driver;

    @BeforeTest
    public void setup() {
        System.setProperty("profile", "windowslocalsession");
        AqualityServices.initInjector(new MobileModule(AqualityServices::getApplication));
        driver = (WindowsDriver<?>) AqualityServices.getApplication().getDriver();
    }

    @AfterTest
    public void tearDown() {
        AqualityServices.getApplication().quit();
        System.clearProperty("profile");
    }

    @Test
    public void test() {
        CalculatorScreen calculatorScreen = new CalculatorScreen();
        calculatorScreen.clickButton(Button.ONE);
        calculatorScreen.clickButton(Button.PLUS);
        calculatorScreen.clickButton(Button.TWO);
        calculatorScreen.clickButton(Button.EQUALS);
        Assert.assertTrue(calculatorScreen.getResults().endsWith("3"), "Display result is not match to expected");
    }
}
