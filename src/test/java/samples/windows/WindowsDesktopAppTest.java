package samples.windows;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.windows.WindowsDriver;
import samples.BaseTest;

import java.net.MalformedURLException;

public class WindowsDesktopAppTest extends BaseTest {

    public static WindowsDriver<?> driver;

    @BeforeTest
    public void setup() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformVersion", "10");
        caps.setCapability("platformName", "Windows");
        caps.setCapability("deviceName", "WindowsPC");
        caps.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
        caps.setCapability("newCommandTimeout", 2000);
        driver = new WindowsDriver<>(getServiceUrl(), caps);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
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
