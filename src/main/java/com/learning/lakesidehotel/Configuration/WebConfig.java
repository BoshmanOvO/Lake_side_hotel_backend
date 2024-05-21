package com.learning.lakesidehotel.Configuration;


import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.learning.lakesidehotel.Controller")
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        // This is a simple configuration to allow CORS from http://localhost:5173
        // This is useful when you are running your frontend on a different port than your backend
        // You can change the allowedOrigins to allow requests from different origins
        // You can also change the allowedMethods to allow different HTTP methods
        // You can also change the allowedHeaders to allow different headers
        // You can also change the path pattern to allow CORS for specific paths
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name())
                        .allowedHeaders(HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION);
            }
        };
    }
}
