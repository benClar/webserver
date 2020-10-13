package server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.StringJoiner;

public class HttpMessageConverter {

    public static byte[] convert(HttpResponse response) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            outputStream.write("\r\n".getBytes());
            outputStream.write(("HTTP/1.1" + response.getStatus().statusCode() + response.getStatus().toString()).getBytes());
            outputStream.write("\r\n".getBytes());

            for (HttpHeader header : response.getHeaders()) {
                outputStream.write(header.convert().getBytes());
                outputStream.write("\r\n".getBytes());
            }

            outputStream.write(("Content-Length: " + response.getBody().length).getBytes());
            outputStream.write("\r\n".getBytes());
            outputStream.write("\r\n".getBytes());

            outputStream.write(response.getBody());
            outputStream.write("\r\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
