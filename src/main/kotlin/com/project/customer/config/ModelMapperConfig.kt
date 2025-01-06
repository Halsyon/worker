package com.project.customer.config

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ModelMapperConfig {

    @Bean
    fun mapper(): ModelMapper {
        return ModelMapper()
    }
}