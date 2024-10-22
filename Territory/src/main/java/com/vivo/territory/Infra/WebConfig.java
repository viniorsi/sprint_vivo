package com.vivo.territory.Infra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${frontend_url_cors}")
    String url_cors;


    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite CORS para todas as rotas
                .allowedOrigins(url_cors) // Permite apenas a origem do seu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*") // Cabeçalhos permitidos
                .allowCredentials(true); // Permitir credenciais (se necessário)
        System.out.println(url_cors);
    }
}
