package com.example.Ecomm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer config()
    {
        return new WebMvcConfigurer(){

            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:5500","http://127.0.0.1:5500","http://127.0.0.1:5501","http://localhost:5501")
                        .allowedMethods("GET","POST","PUT","DELETE","OPTIONS","PATCH")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
