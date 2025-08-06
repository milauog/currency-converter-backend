package com.currencyconverter.currency.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Apply CORS only on API paths
                        .allowedOrigins(
                                "http://localhost:4200",                     // Angular local dev server
                                "https://currency-converter-41c0c.web.app/",              // Replace with your deployed frontend URL (Firebase hosting URL)
                                "https://currency-converter-41c0c.firebaseapp.com"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true); // If your frontend sends cookies/auth info
            }
        };
    }
}

