package server.constant;

public enum ContentType {
    IMAGE("image/x-icon"),
    TEXT("text/html");

    private String value;

    ContentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
