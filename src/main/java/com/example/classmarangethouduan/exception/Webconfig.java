package com.example.classmarangethouduan.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Slf4j
@Configuration
public class Webconfig  {
     @Bean
    public WebMvcConfigurer corsConfig(){
         return new WebMvcConfigurer() {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("WebConfig已启动.......");
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
};
}
}