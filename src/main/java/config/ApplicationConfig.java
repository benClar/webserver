package config;

import handler.ResourceHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import server.constant.Header;

@Configuration
public class ApplicationConfig {

    @Bean("/favicon.ico")
    public ResourceHandler faviconHandler() {
        return new ResourceHandler("image/default_favicon.jpg", Header.CONTENT_TYPE_IMAGE);
    }

    @Bean("/")
    public ResourceHandler homeHandler() {
        return new ResourceHandler("html/index.html", Header.CONTENT_TYPE_TEXT);
    }
}
