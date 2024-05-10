package com.br.apicerquilhotodos.infra.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500", "https://localhost:5500", "https://pji-univesp-grp13.github.io",
                        "http://pji-univesp-grp13.github.io")
                .allowedMethods("GET", "POST", "DELETE", "PUT");
    }
}
