package com.sang.sangschoolback.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer{

    @Value("${client.url.local}")
    private String CLIENT_LOCAL;

    @Value("${client.url.dev}")
    private String CLIENT_DEV;

    @Value("${client.url.prod}")
    private String CLIENT_PROD;
    @Bean
    public WebMvcConfigurer corsConfigure(){

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(CLIENT_DEV,CLIENT_LOCAL,CLIENT_PROD)
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .exposedHeaders("Authorization")
                        .allowCredentials(true);
            }
        };
    }
}
