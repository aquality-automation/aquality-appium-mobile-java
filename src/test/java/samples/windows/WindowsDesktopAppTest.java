package samples.windows;
import aquality.appium.application.ApplicationManager;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WindowsDesktopAppTest {

    public static WindowsDriver<?> driver;

    @BeforeTest
    public void setup() {
        System.setProperty("profile", "windowslocalsession");
        driver = (WindowsDriver<?>) ApplicationManager.getApplication().getDriver();
    }

    @AfterTest
    public void tearDown() {
        ApplicationManager.getApplication().quit();
        System.clearProperty("profile");
    }

    @Test
    public void test() {
        driver.findElementByXPath("//*[@AutomationId='num1Button']").click();
        driver.findElementByAccessibilityId("plusButton").click();
        driver.findElementByAccessibilityId("num2Button").click();
        driver.findElementByAccessibilityId("equalButton").click();
        Assert.assertTrue(driver.findElementByAccessibilityId("CalculatorResults").getText().endsWith("3"), "Display result is not match to expected");
    }
}
