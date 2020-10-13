package server;

import handler.DefaultHandler;
import org.springframework.context.ApplicationContext;
import util.ArrayUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketListener {

    ServerSocket socket;
    ExecutorService executorService;

    private int port;
    private ApplicationContext context;
    private Map<String, Handler> endpointMap = new HashMap<>();

    public SocketListener(int port, ApplicationContext context) {
        this.port = port;
        this.context = context;
        executorService = Executors.newCachedThreadPool();
    }

    public void start() throws IOException {
        socket = new ServerSocket(port);

        for (String endpoint : context.getBeanNamesForType(Handler.class)) {
            endpointMap.put(endpoint, (Handler) context.getBean(endpoint));
        }

        Socket accept;
        while (true) {
            accept = socket.accept();
            BufferedReader inSocket = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            PrintStream outSocket = new PrintStream(accept.getOutputStream());
            executorService.submit(() -> process(inSocket, outSocket));
        }
    }

    private void process(BufferedReader inSocket, PrintStream outSocket) {
        try {
            String requestLine = inSocket.readLine();
            if (requestLine != null) {
                String method = requestLine.split(" ")[0].trim();
                String URI = requestLine.split(" ")[1].trim();
                List<HttpHeader> httpHeaders = readHeaders(inSocket);
                String body = readBody(inSocket);
                HttpMessage request = new HttpMessage(method, URI, httpHeaders, body);
                HttpResponse response = endpointMap
                        .getOrDefault(request.getURI(), new DefaultHandler()).handle(request);
                outSocket.write(HttpMessageConverter.convert(response));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readBody(BufferedReader inSocket) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (inSocket.ready()) {
            sb.append((char) inSocket.read());
        }
        return sb.toString();
    }

    private List<HttpHeader> readHeaders(BufferedReader inSocket) throws IOException {
        List<HttpHeader> headers = new ArrayList<>();
        String line;
        do {
            line = inSocket.readLine();
            String[] split = line.split(":");
            String value = String.join(":", (List<String>) ArrayUtil.slice(1, split.length, split));
            headers.add(new HttpHeader(split[0], value));

        } while (line != null && line.length() > 0);
        return headers;
    }
}
