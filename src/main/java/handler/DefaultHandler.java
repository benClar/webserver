package handler;

import server.*;
import server.constant.ContentType;
import server.constant.Header;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DefaultHandler  implements Handler {

    public HttpResponse handle(HttpMessage request) {
        System.out.println(request);
        String OUTPUT = "okay " + LocalDateTime.now().toString();
        List<HttpHeader> headers = new ArrayList<>();
        headers.add(Header.CONTENT_TYPE_TEXT.getHeader());
        return new HttpResponse(OUTPUT.getBytes(), headers, HttpStatus.OK);
    }
}
