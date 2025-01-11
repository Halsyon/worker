package com.project.customer.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:8080")  // Укажите конкретный источник (например, для вашего фронтенда)
            .allowedMethods("GET", "POST", "PUT", "DELETE") // Разрешаем методы
            .allowedHeaders("*") // Разрешаем все заголовки
            .allowCredentials(false)  // Отключаем куки и авторизацию
            .maxAge(3600)
    }
}
