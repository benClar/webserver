package server;

public enum HttpStatus {
    OK(200);

    public int statusCode() {
        return code;
    }

    private int code;
    HttpStatus(int code) {
        this.code = code;
    }
}
