package samples.android.web.theinternet;

import org.apache.commons.lang3.ArrayUtils;

public enum DropdownOption {
    DEFAULT("Please select an option", ""),
    FIRST("Option 1", "1"),
    SECOND("Option 2", "2");

    private final String text;
    private final String value;

    DropdownOption(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }

    public int getIndex() {
        return ArrayUtils.indexOf(values(), this);
    }
}
