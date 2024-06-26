package com.ecom.ecomshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/categories/**")
                .addResourceLocations("classpath:/static/images/categories/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS)); // Adjust cache settings as needed
    }
}
