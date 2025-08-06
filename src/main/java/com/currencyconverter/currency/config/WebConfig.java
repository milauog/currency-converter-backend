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
                registry.addMapping("/api/**")
                        .allowedOrigins(
                                // Development URLs
                                "http://localhost:4200",  // Angular default port
                                "http://localhost:3000",  // Common React port
                                "http://127.0.0.1:4200", // Alternative localhost

                                // Production Firebase URLs (no trailing slashes)
                                "https://currency-converter-41c0c.web.app",
                                "https://currency-converter-41c0c.firebaseapp.com",

                                // Render backend URL (if making requests from backend to backend)
                                "https://currency-converter-backend-2.onrender.com"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                        .allowedHeaders("*")
                        .exposedHeaders(
                                "Authorization",
                                "Content-Type",
                                "Content-Disposition",
                                "Access-Control-Allow-Origin"
                        )
                        .allowCredentials(true)
                        .maxAge(3600); // 1 hour cache for preflight responses
            }
        };
    }
}