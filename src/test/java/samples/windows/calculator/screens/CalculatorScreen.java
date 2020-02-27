package samples.windows.calculator.screens;

import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.WindowsScreen;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class CalculatorScreen extends WindowsScreen {

    private final ILabel lblResults = getElementFactory().getLabel(MobileBy.AccessibilityId("CalculatorResults"), "Results");

    public CalculatorScreen() {
        super(By.id("TitleBar"), "Calculator");
    }

    public void clickButton(Button button){
        getElementFactory().getButton(button.getLocator(), button.name()).click();
    }

    public String getResults() {
        return lblResults.getText();
    }

    public enum Button{
        ONE("num1"),
        TWO("num2"),
        PLUS("plus"),
        EQUALS("equal");

        private String id;

        Button(String id){
            this.id = id;
        }

        By getLocator(){
            return MobileBy.AccessibilityId(id + "Button");
        }
    }
}
