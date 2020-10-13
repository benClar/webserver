package server;

public class HttpHeader {
    private final String key;
    private final String value;

    @Override
    public String toString() {
        return "HttpHeader{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public HttpHeader(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String convert(){
        return String.join(": ", key, value);
    }
}
