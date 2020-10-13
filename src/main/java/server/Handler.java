package server;

public interface Handler {
    HttpResponse handle(HttpMessage request);
}
