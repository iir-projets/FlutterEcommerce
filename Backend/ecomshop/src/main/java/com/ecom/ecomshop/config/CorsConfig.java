package com.ecom.ecomshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")  // Allows all origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allows essential methods plus OPTIONS
                .allowedHeaders("*")
                .allowCredentials(false);  // Avoid allowing credentials for all origins
    }
}

