package samples.android.nativeapp.apidemos.screens;

import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;

public class ViewTabsScrollableScreen extends AndroidScreen {

    private static final String TAB_TEXT = "Content for tab with tag Tab %s";

    public IButton getTab(int tabNumber) {
        return getElementFactory().getButton(
                By.xpath(String.format("//*[@text='TAB %s' and @resource-id = 'android:id/title']", tabNumber)),
                String.valueOf(tabNumber));
    }

    public ILabel getTabContent(int tabNumber) {
        return getElementFactory().getLabel(
                By.xpath(String.format("//*[@text='%s']", generateTabText(tabNumber))),
                String.valueOf(tabNumber));
    }

    public ViewTabsScrollableScreen() {
        super(By.id("android:id/content"), "View/Tabs/Scrollable");
    }

    public void swipeTab(int startTabNumber, int endTabNumber) {
        Point endTabPoint = getTab(endTabNumber).getCenter();
        getTab(startTabNumber).getTouchActions().swipe(endTabPoint);
    }

    public void swipeTabWithLongPress(int startTabNumber, int endTabNumber) {
        Point endTabPoint = getTab(endTabNumber).getCenter();
        getTab(startTabNumber).getTouchActions().swipeWithLongPress(endTabPoint);
    }

    public String getTabContentText(int tabNumber) {
        return getTabContent(tabNumber).getText();
    }

    public void selectTab(int tabNumber) {
        getTab(tabNumber).click();
    }

    private String generateTabText(int tabNumber) {
        return String.format(TAB_TEXT, tabNumber);
    }
}
