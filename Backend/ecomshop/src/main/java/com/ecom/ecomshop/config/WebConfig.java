package com.ecom.ecomshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Ensuring that resources in /static/images are mapped to /images/**
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
    }
}

