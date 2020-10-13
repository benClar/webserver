package handler;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import server.*;
import server.constant.Header;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourceHandler implements Handler {
    private String path;
    private Header contentType;

    public ResourceHandler(String path, Header contentType) {
        this.path = path;
        this.contentType = contentType;
    }

    public HttpResponse handle(HttpMessage request) {
        ClassPathResource classPathResource = new ClassPathResource(path);
        try {
            byte[] body = FileUtils.readFileToByteArray(classPathResource.getFile());
            List<HttpHeader> headers = new ArrayList<>();
            headers.add(contentType.getHeader());
            return new HttpResponse(body,headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
