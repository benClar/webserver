package server.constant;

import server.HttpHeader;

public enum Header {

    CONTENT_TYPE_IMAGE(Constant.CONTENT_TYPE, ContentType.IMAGE.getValue()),
    CONTENT_TYPE_TEXT(Constant.CONTENT_TYPE, ContentType.TEXT.getValue());

    private final HttpHeader header;

    Header(String contentType, String value) {
        header = new HttpHeader(contentType, value);
    }

    public HttpHeader getHeader() {
        return header;
    }
}
