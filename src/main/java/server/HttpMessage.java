package server;

import java.util.ArrayList;
import java.util.List;

public class HttpMessage {
    private final String method;

    public String getMethod() {
        return method;
    }

    public String getURI() {
        return URI;
    }

    public List<HttpHeader> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    private String URI;
    private final List<HttpHeader> headers;
    private final String body;

    @Override
    public String toString() {
        return "HttpMessage{" +
                "method='" + method + '\'' +
                ", URI='" + URI + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }

    public HttpMessage(String body) {
        this.body = body;
        this.headers = new ArrayList<>();
        method = null;
    }


    public HttpMessage(String method, String URI, List<HttpHeader> headers, String body) {
        this.method = method;
        this.URI = URI;
        this.headers = headers;
        this.body = body;
    }

    public static class Builder {
        private final List<HttpHeader> headers = new ArrayList<>();
        private String method;
        private String URI;
        private String body;

        public Builder header(HttpHeader header) {
            headers.add(header);
            return this;
        }

        public Builder uri(String URI) {
            this.URI = URI;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public HttpMessage builder() {
            return new HttpMessage(method, URI, headers, body);
        }
    }
}
