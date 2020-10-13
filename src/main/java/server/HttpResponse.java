package server;

import java.util.List;

public class HttpResponse {

    private final byte[] body;
    private List<HttpHeader> headers;

    public byte[] getBody() {
        return body;
    }

    public HttpStatus getStatus() {
        return status;
    }

    private final HttpStatus status;

    public HttpResponse(byte[] body, List<HttpHeader> headers, HttpStatus status) {
        this.headers = headers;
        this.body = body;
        this.status = status;
    }

    public List<HttpHeader> getHeaders() {
        return headers;
    }
}
