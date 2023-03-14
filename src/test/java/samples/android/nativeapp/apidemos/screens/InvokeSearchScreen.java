package samples.android.nativeapp.apidemos.screens;

import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import org.openqa.selenium.By;

public class InvokeSearchScreen extends AndroidScreen {

    private final ITextBox txbSearch = getElementFactory().getTextBox(By.id("txt_query_prefill"), "Search");
    private final IButton btnStartSearch = getElementFactory().getButton(By.id("btn_start_search"), "Start search");
    private final ILabel lblSearchResult = getElementFactory().getLabel(By.id("android:id/search_src_text"), "Search results");

    public InvokeSearchScreen() {
        super(By.xpath("//android.widget.TextView[@text='App/Search/Invoke Search']"), "Invoke Search");
    }

    public void submitSearch(String query) {
        typeQuery(query);
        btnStartSearch.click();
    }

    public void typeQuery(String query) {
        txbSearch.clearAndType(query);
    }

    public String getSearchResult() {
        return lblSearchResult.getText();
    }
}
