package server;

import config.ApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        withSocketListener();
    }

    public static void withSocketListener() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        SocketListener socketListener = new SocketListener(9999, context);
        try {
            socketListener.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
