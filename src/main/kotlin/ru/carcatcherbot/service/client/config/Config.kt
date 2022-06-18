package ru.carcatcherbot.service.client.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.web.client.RestTemplate

@Configuration
class Config {
    @Bean
    @Scope("prototype")
    fun getRestTemplate(): RestTemplate {
        return RestTemplate()
    }
}
