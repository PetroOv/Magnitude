package utils.extensions;

public enum Utils {
    HTTP("http://"),
    DOT("."),
    SLASH("/"),
    ALLHOTELS("all-hotels/"),
    WWW("www.");
    private final String value;

    Utils(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
