package samples.android.web;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.elements.Attributes;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IComboBox;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import samples.android.web.theinternet.DropdownOption;

import java.util.Arrays;
import java.util.stream.Collectors;

public class WebComboboxTest extends AndroidWebTest {
    private IComboBox comboBox;

    @BeforeMethod
    public void openDropdownPage() {
        AqualityServices.getApplication().getDriver().get("http://the-internet.herokuapp.com/dropdown");
        comboBox = AqualityServices.getElementFactory().getComboBox(By.id("dropdown"), "dropdown");
        comboBox.state().waitForClickable();
    }

    @Test(groups = "web")
    public void testComboBoxGetsTexts(){
        Assert.assertTrue(comboBox.getTexts().containsAll(
                Arrays.stream(DropdownOption.values()).map(DropdownOption::getText).collect(Collectors.toList())),
                "Texts should match to expected");
    }

    @Test(groups = "web")
    public void testComboBoxGetsValues(){
        Assert.assertTrue(comboBox.getValues().containsAll(
                Arrays.stream(DropdownOption.values()).map(DropdownOption::getValue).collect(Collectors.toList())),
                "Values should match to expected");
    }

    @Test(groups = "web")
    public void testComboBoxSelectionMethods() {
        Assert.assertEquals(DropdownOption.DEFAULT.getText(), comboBox.getSelectedText(), "Option's text mismatch");
        comboBox.selectByValue(DropdownOption.FIRST.getValue());
        Assert.assertEquals(DropdownOption.FIRST.getValue(), comboBox.getSelectedValue(), "Option's value mismatch");
        comboBox.selectByText(DropdownOption.SECOND.getText());
        Assert.assertEquals(DropdownOption.SECOND.getText(), comboBox.getSelectedText(), "Option's text mismatch");
        comboBox.clickAndSelectByText(DropdownOption.FIRST.getText());
        Assert.assertEquals(DropdownOption.FIRST.getText(), comboBox.getSelectedText(), "Option's text mismatch");
        comboBox.clickAndSelectByValue(DropdownOption.SECOND.getValue());
        Assert.assertEquals(DropdownOption.SECOND.getValue(), comboBox.getSelectedValue(), "Option's value mismatch");
        comboBox.selectByContainingText(DropdownOption.FIRST.getValue());
        Assert.assertEquals(DropdownOption.FIRST.getText(), comboBox.getSelectedText(), "Option's text mismatch");
        comboBox.selectByContainingValue(DropdownOption.SECOND.getValue());
        Assert.assertEquals(DropdownOption.SECOND.getValue(), comboBox.getSelectedValue(), "Option's value mismatch");
        comboBox.selectByIndex(DropdownOption.FIRST.getIndex());
        Assert.assertEquals(DropdownOption.FIRST.getText(), comboBox.getSelectedText(), "Option's text mismatch");
    }

    private By getChildLocator(String textPart) {
        return By.xpath(String.format(".//*[contains(., '%s')]", textPart));
    }

    @Test(groups = "web")
    public void testFindChildElementWithName() {
        Assert.assertEquals(DropdownOption.SECOND.getValue(),
                comboBox.findChildElement(getChildLocator(DropdownOption.SECOND.getValue()), "2", ElementType.LABEL)
                        .getAttribute(Attributes.VALUE),
                "Child option's value mismatch");
    }

    @Test(groups = "web")
    public void testFindChildElementWithoutName() {
        Assert.assertEquals(DropdownOption.FIRST.getText(),
                comboBox.findChildElement(getChildLocator(DropdownOption.FIRST.getText()), ElementType.LABEL).getText(),
                "Child option's text mismatch");
    }
}
